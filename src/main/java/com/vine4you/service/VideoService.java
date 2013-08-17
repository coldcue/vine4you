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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Andrew
 * Date: 8/14/13
 * Time: 1:42 PM
 */
public class VideoService {

    public static final int FeaturedListSize = 12;
    private static final Logger log = Logger.getLogger(VideoService.class.getName());

    /**
     * Gets a single videoElement entity from the database or from cache
     *
     * @param id the id
     * @return the videoElement
     * @throws EntityNotFoundException
     */
    public static VideoEntity getVideoEntity(long id) throws EntityNotFoundException {
        Entity video = DatastoreServiceFactory.getDatastoreService().get(KeyFactory.createKey(VideoEntity.kind, id));
        return new VideoEntity(video);
    }

    /**
     * Inserts or updates an entity
     *
     * @param videoEntity the entity
     * @return the key
     * @throws Exception
     */
    public static Key addVideoEntity(VideoEntity videoEntity, boolean asNew) throws Exception {

        //Vine URL check
        Query query = new Query(VideoEntity.kind);
        Query.Filter filter = new Query.FilterPredicate("vineURL", Query.FilterOperator.EQUAL, videoEntity.getVineURL());
        query.setFilter(filter);
        PreparedQuery preparedQuery = DatastoreServiceFactory.getDatastoreService().prepare(query);

        List<Entity> entities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
        //If there`s more then one entity
        if (entities.size() > 1) {
            String msg = "Multiple vine videos! ID:" + videoEntity.getVineURL();
            log.severe(msg);
            throw new Exception(msg);
            //if there`s only one entity
        } else if (entities.size() == 1) {
            Entity entity = entities.get(0);
            boolean published = (boolean) entity.getProperty(VideoEntity.PUBLISHED);
            Date publishedDate = (Date) entity.getProperty(VideoEntity.PUBLISHED_DATE);

            entity.setPropertiesFrom(videoEntity.generateEntity());

            //If import as new
            if (asNew) {
                entity.setProperty(VideoEntity.PUBLISHED, false);
            }
            //If it was published before
            else if (published) {
                entity.setProperty(VideoEntity.PUBLISHED, true);
                entity.setProperty(VideoEntity.PUBLISHED_DATE, publishedDate);
            } else {
                entity.setProperty(VideoEntity.PUBLISHED, false);
            }


            DatastoreServiceFactory.getDatastoreService().put(entity);
            log.info("A video is refreshed! ID: " + entity.getKey().getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
            return entity.getKey();
            //otherwise
        } else {
            Entity entity = videoEntity.generateEntity();
            if (asNew) {
                entity.setProperty(VideoEntity.PUBLISHED, false);
            }
            Key key = DatastoreServiceFactory.getDatastoreService().put(entity);
            log.info("A new video is added! ID: " + key.getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
            return key;
        }
    }

    /**
     * Returns the first videoElement to show to the user
     *
     * @return the videoElement
     */
    public static VideoEntity getFirstVideo() {
        //TODO save into the memory
        Query query = new Query(VideoEntity.kind);
        query.setFilter(new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true));
        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.DESCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);

        return new VideoEntity(DatastoreServiceFactory.getDatastoreService().prepare(query).asList(fetchOptions).get(0));
    }

    /**
     * Returns the featured videoElement list from a given videoElement
     *
     * @param from the videoElement
     * @return the list
     */
    public static List<VideoEntity> getFeaturedVideos(VideoEntity from) {
        Query query = new Query(VideoEntity.kind);

        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.DESCENDING);

        Query.FilterPredicate publishedFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true);
        Query.FilterPredicate publishedDateFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED_DATE, Query.FilterOperator.LESS_THAN, from.getPublishedDate());

        query.setFilter(Query.CompositeFilterOperator.and(publishedFilter, publishedDateFilter));

        //TODO Add projections
        /*query.addProjection(new PropertyProjection(VideoEntity.TITLE, null));
        query.addProjection(new PropertyProjection(VideoEntity.AUTHOR, null));
        query.addProjection(new PropertyProjection(VideoEntity.IMAGE_URL, null));*/

        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(FeaturedListSize);
        PreparedQuery preparedQuery = DatastoreServiceFactory.getDatastoreService().prepare(query);

        List<VideoEntity> videoEntities = new ArrayList<>();

        for (Entity entity : preparedQuery.asIterable(fetchOptions)) {
            if (from.getKey().getId() == entity.getKey().getId()) continue;
            videoEntities.add(new VideoEntity(entity));
        }

         /*If the featured list is smaller than it has to be, then use the defualt list*/
        if (videoEntities.size() < FeaturedListSize && from.getKey().getId() != getFirstVideo().getKey().getId()) {
            return getFeaturedVideos(getFirstVideo());
        }

        return videoEntities;
    }

    public static VideoEntity getPreviousVideo(VideoEntity from) {
        Query query = new Query(VideoEntity.kind);

        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.ASCENDING);

        Query.FilterPredicate publishedFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true);
        Query.FilterPredicate publishedDateFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED_DATE, Query.FilterOperator.GREATER_THAN, from.getPublishedDate());

        query.setFilter(Query.CompositeFilterOperator.and(publishedFilter, publishedDateFilter));

        //TODO projection
        /*query.addProjection(new PropertyProjection(VideoEntity.AUTHOR, String.class));
        query.addProjection(new PropertyProjection(VideoEntity.TITLE, String.class));
        query.addProjection(new PropertyProjection(VideoEntity.IMAGE_URL, String.class)); */

        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);
        PreparedQuery preparedQuery = DatastoreServiceFactory.getDatastoreService().prepare(query);

        List<Entity> entities = preparedQuery.asList(fetchOptions);
        if (entities.size() > 0)
            return new VideoEntity(entities.get(0));
        else return null;
    }
}
