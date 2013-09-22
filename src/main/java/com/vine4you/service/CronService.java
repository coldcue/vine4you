/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import com.google.appengine.api.datastore.*;
import com.vine4you.entity.VideoEntity;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/17/13
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CronService {
    private static final Logger log = Logger.getLogger(CronService.class.getName());
    private static DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public static void publishVideo() {
        Query query = new Query(VideoEntity.kind);
        query.setFilter(new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, false));
        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.ASCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);

        List<Entity> entities = datastoreService.prepare(query).asList(fetchOptions);

        if (entities.size() != 1) {
            log.severe("There are no more unpublished video!!!!");
        } else {
            Entity entity = entities.get(0);

            entity.setProperty(VideoEntity.PUBLISHED, true);
            entity.setProperty(VideoEntity.PUBLISHED_DATE, Calendar.getInstance().getTime());

            Key key = datastoreService.put(entity);

            /*Publish on facebook*/
            try {
                FacebookService.publishVideo(key.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Clear cache
            CacheService.clearCache();

            log.info("A new video is published! ID: " + entity.getKey().getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
        }
    }

    public static void refreshLikes() {
        Query query = new Query(VideoEntity.kind);

        Iterable<Entity> entities = datastoreService.prepare(query).asIterable();

        for (Entity entity : entities) {
            try {
                long likes = FacebookService.getLikes(entity.getKey().getId());

                if (likes != entity.getProperty(VideoEntity.LIKES)) {
                    entity.setProperty(VideoEntity.LIKES, likes);
                    datastoreService.put(entity);
                    log.info("Likes refreshed (" + likes + ") on video ID: " + entity.getKey().getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
                }
            } catch (Exception ignored) {

            }
        }

        refreshLikeOrder();
    }

    public static void refreshLikeOrder() {
        //Get all likeOrdered videos
        List<Entity> toRemove = getLikeOrderedEntities();
        //Get the most liked videos
        List<Entity> mostLiked = getMostLikedEntities();

        //Delete all entities from toRemove which is in mostLiked
        toRemove.removeAll(mostLiked);
        try {

            long likeOrder = 0;
            for (Entity entity : mostLiked) {
                try {
                    //Update entity if its likeOrder is changed
                    if (!entity.hasProperty(VideoEntity.LIKEORDER)) {
                        entity.setProperty(VideoEntity.LIKEORDER, likeOrder);
                        datastoreService.put(entity);
                    } else if (entity.getProperty(VideoEntity.LIKEORDER) != likeOrder) {
                        entity.setProperty(VideoEntity.LIKEORDER, likeOrder);
                        datastoreService.put(entity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.severe("Can't update likeorder: " + e.getMessage());
                }
                likeOrder++;
            }

            //Set likeOrder to max for every entity in toRemove
            for (Entity entity : toRemove) {
                try {
                    entity.removeProperty(VideoEntity.LIKEORDER);
                    datastoreService.put(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.severe("Can't remove property: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static List<Entity> getMostLikedEntities() {
        Query query = new Query(VideoEntity.kind);
        query.addSort(VideoEntity.LIKES, Query.SortDirection.DESCENDING);
        Query.FilterPredicate publishedFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true);
        query.setFilter(publishedFilter);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(VideoService.MostLikedListSize);

        return datastoreService.prepare(query).asList(fetchOptions);
    }

    private static List<Entity> getLikeOrderedEntities() {
        Query query = new Query(VideoEntity.kind);
        query.addSort(VideoEntity.LIKEORDER);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(500);

        return datastoreService.prepare(query).asList(fetchOptions);
    }
}
