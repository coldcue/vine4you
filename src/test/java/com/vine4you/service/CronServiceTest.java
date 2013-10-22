/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.vine4you.entity.VideoEntity;
import com.vine4you.factories.CronServiceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/16/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CronServiceTest extends CronService {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    private CronService cronService;

    @Before
    public void setUp() throws Exception {
        isUnitTesting = true;
        helper.setUp();
        cronService = CronServiceFactory.getCronService();
    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    @Test
    public void testPublishVideo() throws Exception {
        Calendar calendar = Calendar.getInstance();

        //Create three entities with different dates
        Entity firstEntity = new Entity(VideoEntity.kind);
        firstEntity.setProperty(VideoEntity.PUBLISHED, false);
        firstEntity.setProperty(VideoEntity.PUBLISHED_DATE, calendar.getTime());
        firstEntity.setProperty(VideoEntity.TITLE, "FIRST");
        Key firstKey = datastoreService.put(firstEntity);

        //Add 1 hour
        calendar.add(Calendar.HOUR, 1);
        Entity secondEntity = new Entity(VideoEntity.kind);
        secondEntity.setProperty(VideoEntity.PUBLISHED, false);
        secondEntity.setProperty(VideoEntity.PUBLISHED_DATE, calendar.getTime());
        secondEntity.setProperty(VideoEntity.TITLE, "SECOND");
        Key secondKey = datastoreService.put(secondEntity);

        //Add 2 hour
        calendar.add(Calendar.HOUR, 2);
        Entity thirdEntity = new Entity(VideoEntity.kind);
        thirdEntity.setProperty(VideoEntity.PUBLISHED, true);
        thirdEntity.setProperty(VideoEntity.PUBLISHED_DATE, calendar.getTime());
        thirdEntity.setProperty(VideoEntity.TITLE, "THIRD");
        Key thirdKey = datastoreService.put(secondEntity);

        //Test the publish of the first video
        cronService.publishVideo();

        assertTrue((Boolean) datastoreService.get(firstKey).getProperty(VideoEntity.PUBLISHED));
        assertFalse((Boolean) datastoreService.get(secondKey).getProperty(VideoEntity.PUBLISHED));
        assertTrue((Boolean) datastoreService.get(thirdKey).getProperty(VideoEntity.PUBLISHED));

        //Test the publish of the second video
        cronService.publishVideo();

        assertTrue((Boolean) datastoreService.get(firstKey).getProperty(VideoEntity.PUBLISHED));
        assertTrue((Boolean) datastoreService.get(secondKey).getProperty(VideoEntity.PUBLISHED));
        assertTrue((Boolean) datastoreService.get(thirdKey).getProperty(VideoEntity.PUBLISHED));

        //Test the re-publish of the third video
        cronService.publishVideo();

        assertTrue((Boolean) datastoreService.get(firstKey).getProperty(VideoEntity.PUBLISHED));
        Entity second = datastoreService.get(secondKey);
        assertTrue((Boolean) second.getProperty(VideoEntity.PUBLISHED));
        Entity third = datastoreService.get(thirdKey);
        assertTrue((Boolean) third.getProperty(VideoEntity.PUBLISHED));

        //Make sure that the third video is re-published
        assertTrue(((Date) third.getProperty(VideoEntity.PUBLISHED_DATE)).after((Date) second.getProperty(VideoEntity.PUBLISHED_DATE)));

        datastoreService.delete(firstKey);
        datastoreService.delete(secondKey);
    }

    @Test
    public void testRefreshLikes() throws Exception {
        //dunno how to test it
    }

    @Test
    public void testRefreshLikeOrder() throws Exception {
    }
}
