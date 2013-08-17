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
    private static final Logger log = Logger.getLogger(ImportService.class.getName());
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

            datastoreService.put(entity);

            log.info("A new video is published! ID: " + entity.getKey().getId() + " TITLE:" + entity.getProperty(VideoEntity.TITLE));
        }
    }
}
