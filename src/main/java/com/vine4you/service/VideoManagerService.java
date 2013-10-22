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

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/22/13
 * Time: 10:29 PM
 * <p/>
 * This class is used to manage the video {@link Entity}s on a low level API
 *
 * @see VideoService
 */
public class VideoManagerService {

    private DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    /**
     * Fetches the oldest published video from the database
     *
     * @return the video {@link Entity}
     */
    public Entity getLastPublishedVideo() {
        Query query = new Query(VideoEntity.kind);
        query.setFilter(new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, true));
        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.ASCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);

        return datastoreService.prepare(query).asList(fetchOptions).get(0);
    }

    /**
     * Publishes a new, yet unpublished video
     *
     * @return The published {@link Entity}, or <b>null</b> if there wasn't
     */
    public Entity publishNewVideo() {
        Query query = new Query(VideoEntity.kind);
        query.setFilter(new Query.FilterPredicate(VideoEntity.PUBLISHED, Query.FilterOperator.EQUAL, false));
        query.addSort(VideoEntity.PUBLISHED_DATE, Query.SortDirection.ASCENDING);
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(1);

        List<Entity> entities = datastoreService.prepare(query).asList(fetchOptions);

        //If the list is empty
        if (entities.size() != 1) return null;

        Entity entity = entities.get(0);
        publishVideo(entity);
        return entity;
    }

    /**
     * Publishes the given {@link Entity}
     *
     * @param entity the {@link Entity} to be published
     */
    public void publishVideo(Entity entity) {
        entity.setProperty(VideoEntity.PUBLISHED, true);
        entity.setProperty(VideoEntity.PUBLISHED_DATE, Calendar.getInstance().getTime());
        datastoreService.put(entity);
    }
}
