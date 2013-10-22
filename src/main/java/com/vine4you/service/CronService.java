/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com) and                 *
 * Adrian Szell (szelladrian@gmail.com)                                       *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import com.google.appengine.api.datastore.*;
import com.vine4you.entity.VideoEntity;
import com.vine4you.factories.FacebookServiceFactory;
import com.vine4you.factories.VideoCacheServiceFactory;
import com.vine4you.factories.VideoManagerServiceFactory;

import java.io.IOException;
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
    protected static boolean isUnitTesting = false;
    private final Logger log = Logger.getLogger(CronService.class.getName());
    private DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    private VideoCacheService videoCacheService = VideoCacheServiceFactory.getVideoCacheService();
    private VideoManagerService videoManagerService = VideoManagerServiceFactory.getVideoManagerService();
    private FacebookService facebookService = FacebookServiceFactory.getFacebookService();

    /**
     * Publishes a video, a new or an old, but publishes one
     */
    public void publishVideo() {

        Entity entity = videoManagerService.publishNewVideo();
        if (entity == null) {
            log.warning("There are no more unpublished video!!!!");

            //Publish the last video again
            entity = videoManagerService.getLastPublishedVideo();
            videoManagerService.publishVideo(entity);

            try {
                //If Unit testing, then don't post it to facebook
                if (!isUnitTesting)
                    facebookService.publishVideoToPage(entity.getKey().getId());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            log.info("An OLD video is published AGAIN! ID: " + entity.getKey().getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
        } else {
            /*Publish on facebook*/
            try {
                //If Unit testing, then don't post it to facebook
                if (!isUnitTesting)
                    facebookService.publishVideoToPage(entity.getKey().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            log.info("A new video is published! ID: " + entity.getKey().getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
        }

        //Clear cache
        videoCacheService.clearCache();
    }

    /**
     * @see VideoManagerService#publishVideo(com.google.appengine.api.datastore.Entity)
     */
    public void refreshLikes() {
        Query query = new Query(VideoEntity.kind);

        Iterable<Entity> entities = datastoreService.prepare(query).asIterable();

        for (Entity entity : entities) {
            try {
                long likes = facebookService.getLikes(entity.getKey().getId());

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

    public void refreshLikeOrder() {
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
                    if (!entity.hasProperty(VideoEntity.LIKE_ORDER)) {
                        entity.setProperty(VideoEntity.LIKE_ORDER, likeOrder);
                        datastoreService.put(entity);
                    } else if (entity.getProperty(VideoEntity.LIKE_ORDER) != likeOrder) {
                        entity.setProperty(VideoEntity.LIKE_ORDER, likeOrder);
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
                    entity.removeProperty(VideoEntity.LIKE_ORDER);
                    datastoreService.put(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.severe("Can't remove property: " + e.getMessage());
                }
            }

            //Clear cache
            videoCacheService.clearCache();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Entity> getMostLikedEntities() {
        Query query = new Query(VideoEntity.kind);
        query.addSort(VideoEntity.LIKES, Query.SortDirection.DESCENDING);
        Query.FilterPredicate publishedFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true);
        query.setFilter(publishedFilter);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(VideoService.MostLikedListSize);

        return datastoreService.prepare(query).asList(fetchOptions);
    }

    private List<Entity> getLikeOrderedEntities() {
        Query query = new Query(VideoEntity.kind);
        query.addSort(VideoEntity.LIKE_ORDER);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(500);

        return datastoreService.prepare(query).asList(fetchOptions);
    }
}
