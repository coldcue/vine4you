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
import java.util.Collection;
import java.util.List;

/**
 * User: Andrew
 * Date: 8/14/13
 * Time: 1:42 PM
 */
public class VideoService {

    public static final String hashSalt = "hkUwauV4812E0bus71A9UIVl651eW47T";
    private static DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    /**
     * Gets a single video entity from the database or from cache
     *
     * @param id the id
     * @return the video
     * @throws EntityNotFoundException
     */
    public static VideoEntity getVideoEntity(long id) throws EntityNotFoundException {
        Entity video = datastoreService.get(KeyFactory.createKey(VideoEntity.kind, id));
        return new VideoEntity(video);
    }

    public static Key addVideoEntity(VideoEntity videoEntity) throws Exception {

        //Vine URL check
        Query query = new Query(VideoEntity.kind);
        Query.Filter filter = new Query.FilterPredicate("vineURL", Query.FilterOperator.EQUAL, videoEntity.getVineURL());
        query.setFilter(filter);
        PreparedQuery preparedQuery = datastoreService.prepare(query);

        //TODO Better checking
        boolean unique = true;

        for (Entity result : preparedQuery.asIterable()) {
            unique = false;
            break;
        }

        if (!unique) throw new Exception("This vine has already been added");

        return datastoreService.put(videoEntity.generateEntity());
    }

    /**
     * Returns the first video to show to the user
     *
     * @return the video
     */
    public static VideoEntity getFirstVideo() {
        Query query = new Query(VideoEntity.kind);
        query.addSort("date", Query.SortDirection.DESCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);
        PreparedQuery preparedQuery = datastoreService.prepare(query);

        return new VideoEntity(preparedQuery.asSingleEntity());
    }

    /**
     * Returns the featured video list from a given video
     *
     * @param from the video
     * @return the list
     */
    public static Collection<VideoEntity> getFeaturedVideos(VideoEntity from) {
        //TODO Projections
        Query query = new Query(VideoEntity.kind);
        query.addSort("date", Query.SortDirection.DESCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(15);
        PreparedQuery preparedQuery = datastoreService.prepare(query);

        List<VideoEntity> videoEntities = new ArrayList<>();

        for (Entity entity : preparedQuery.asIterable(fetchOptions)) {
            videoEntities.add(new VideoEntity(entity));
        }

        return videoEntities;
    }
}
