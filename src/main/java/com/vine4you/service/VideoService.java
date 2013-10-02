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
import com.vine4you.enums.CacheType;

import java.util.ArrayList;
import java.util.Collection;
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
    public static final int MostLikedListSize = 100;
    private static final Logger log = Logger.getLogger(VideoService.class.getName());

    /**
     * Gets a single videoElement entity from the database
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
     * Gets a single videoElement entity from the database or from cache
     *
     * @param id the id
     * @return the videoElement
     * @throws EntityNotFoundException
     */
    public static VideoEntity getVideoEntityCached(long id) throws EntityNotFoundException {
        //Check cache
        VideoEntity videoEntity = VideoCacheService.getVideoEntity(id);
        if (videoEntity != null) return videoEntity;

        //Add to cache
        videoEntity = getVideoEntity(id);
        VideoCacheService.addVideoEntity(videoEntity);
        return videoEntity;
    }

    public static Collection<VideoEntity> getAllPublishedVideoEntityForSitemap() {
        Query query = new Query(VideoEntity.kind);
        query.setFilter(new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true));
        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.DESCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(50000);

        List<Entity> entities = DatastoreServiceFactory.getDatastoreService().prepare(query).asList(fetchOptions);

        List<VideoEntity> videoEntities = new ArrayList<>(entities.size());

        for (Entity entity : entities) {
            VideoEntity videoEntity = new VideoEntity(entity);
            videoEntity.setTitle(videoEntity.getTitle().replaceAll("&", "and"));
            videoEntities.add(videoEntity);
        }

        return videoEntities;
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

        //Check cache
        VideoEntity videoEntity = VideoCacheService.getVideoEntity(CacheType.FIRST_VIDEO);
        if (videoEntity != null) return videoEntity;

        Query query = new Query(VideoEntity.kind);
        query.setFilter(new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true));
        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.DESCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);

        //Add to cache
        videoEntity = new VideoEntity(DatastoreServiceFactory.getDatastoreService().prepare(query).asList(fetchOptions).get(0));
        VideoCacheService.addVideoEntity(CacheType.FIRST_VIDEO, videoEntity);
        return videoEntity;
    }

    /**
     * Returns the first videoElement to show to the user
     *
     * @return the videoElement
     */
    public static VideoEntity getFirstMostLikedVideo() {

        //Check cache
        VideoEntity videoEntity = VideoCacheService.getVideoEntity(CacheType.FIRST_MOSTLIKED_VIDEO);
        if (videoEntity != null) return videoEntity;

        Query query = new Query(VideoEntity.kind);
        query.addSort(VideoEntity.LIKEORDER);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);

        //Add to cache
        videoEntity = new VideoEntity(DatastoreServiceFactory.getDatastoreService().prepare(query).asList(fetchOptions).get(0));
        VideoCacheService.addVideoEntity(CacheType.FIRST_MOSTLIKED_VIDEO, videoEntity);
        return videoEntity;
    }

    /**
     * Returns the featured videoElement list from a given videoElement
     *
     * @param from the videoElement
     * @return the list
     */
    public static List<VideoEntity> getFeaturedVideos(VideoEntity from) {
        //Check cache
        List<VideoEntity> videoEntities = VideoCacheService.getFeaturedList(CacheType.FEATURED_LIST, from);
        if (videoEntities != null) return videoEntities;

        Query query = new Query(VideoEntity.kind);

        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.DESCENDING);

        Query.FilterPredicate publishedFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true);
        Query.FilterPredicate publishedDateFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED_DATE, Query.FilterOperator.LESS_THAN, from.getPublishedDate());

        query.setFilter(Query.CompositeFilterOperator.and(publishedFilter, publishedDateFilter));

        videoEntities = getFeaturedVideoEntitiesFromQuery(from, query);
        //Add to cache
        VideoCacheService.addFeaturedList(CacheType.FEATURED_LIST, from, videoEntities);
        return videoEntities;
    }

    /**
     * Returns the most liked videoElement list from a given videoElement
     *
     * @param from the videoElement
     * @return the list
     */
    public static List<VideoEntity> getMostLikedVideos(VideoEntity from) {
        //Check cache
        List<VideoEntity> videoEntities = VideoCacheService.getFeaturedList(CacheType.MOSTLIKED_FEATURED_LIST, from);
        if (videoEntities != null) return videoEntities;

        Query query = new Query(VideoEntity.kind);

        query.addSort(VideoEntity.LIKEORDER);
        Query.FilterPredicate likesFilter = new Query.FilterPredicate(VideoEntity.LIKEORDER, Query.FilterOperator.GREATER_THAN, from.getLikeOrder());
        Query.FilterPredicate sizeFilter = new Query.FilterPredicate(VideoEntity.LIKEORDER, Query.FilterOperator.LESS_THAN, MostLikedListSize);
        query.setFilter(Query.CompositeFilterOperator.and(likesFilter, sizeFilter));

        videoEntities = getFeaturedVideoEntitiesFromQuery(from, query);
        //Add to cache
        VideoCacheService.addFeaturedList(CacheType.MOSTLIKED_FEATURED_LIST, from, videoEntities);
        return videoEntities;
    }

    /**
     * Prepares the VideoEntities from a query
     */
    private static List<VideoEntity> getFeaturedVideoEntitiesFromQuery(VideoEntity from, Query query) {

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

        return videoEntities;
    }

    public static VideoEntity getPreviousVideo(VideoEntity from) {
        Query query = new Query(VideoEntity.kind);

        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.ASCENDING);

        Query.FilterPredicate publishedFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true);
        Query.FilterPredicate publishedDateFilter = new Query.FilterPredicate(VideoEntity.PUBLISHED_DATE, Query.FilterOperator.GREATER_THAN, from.getPublishedDate());

        query.setFilter(Query.CompositeFilterOperator.and(publishedFilter, publishedDateFilter));
        return getVideoEntityFromQuery(query);
    }

    public static VideoEntity getPreviousMostLikedVideo(VideoEntity from) {
        Query query = new Query(VideoEntity.kind);

        query.addSort(VideoEntity.LIKEORDER, Query.SortDirection.DESCENDING);
        Query.FilterPredicate likesFilter = new Query.FilterPredicate(VideoEntity.LIKEORDER, Query.FilterOperator.LESS_THAN, from.getLikeOrder());
        query.setFilter(likesFilter);

        return getVideoEntityFromQuery(query);
    }

    private static VideoEntity getVideoEntityFromQuery(Query query) {
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
