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

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/4/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class FirewallServiceTest extends FirewallService {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private FirewallService firewallService;

    @Before
    public void setUp() throws Exception {
        helper.setUp();
        firewallService = new FirewallService();
    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    @Test
    public void testProcessRequest() throws Exception {

        MemcacheService requestMemcache = MemcacheServiceFactory.getMemcacheService(REQUESTS_NAMESPACE);
        MemcacheService blacklistMemcache = MemcacheServiceFactory.getMemcacheService(BLACKLIST_NAMESPACE);
        requestMemcache.clearAll();

        //Ban this IP
        String blackListedIP = "1.1.1.1";
        String whiteListedIP = "1.1.1.2";

        for (int i = 0; i <= MAX_REQUESTS; i++)
            firewallService.processRequest(blackListedIP);

        for (int i = 0; i < firewallService.MAX_REQUESTS - 1; i++)
            firewallService.processRequest(whiteListedIP);

        assertTrue(firewallService.isBlacklisted(blackListedIP));
        assertFalse(firewallService.isBlacklisted(whiteListedIP));

        //Check second level cache
        assertTrue(requestMemcache.contains(blackListedIP));

        //Check first level cache reset
       /* blacklistMemcache.clearAll();
        Thread.sleep(FIRST_LEVEL_CACHE_RESET_INTERVAL);
        assertFalse(isBlacklisted(blackListedIP));*/

        //Clear request cache
        firewallService.clearRequestCache();

        for (int i = 0; i < firewallService.MAX_REQUESTS - 1; i++)
            firewallService.processRequest(whiteListedIP);

        assertFalse(firewallService.isBlacklisted(whiteListedIP));
    }
}
