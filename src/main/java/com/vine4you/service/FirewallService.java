/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/2/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("FieldCanBeLocal")
public class FirewallService {
    private final String REQUESTS_NAMESPACE = "requests";
    private final String BLACKLIST_NAMESPACE = "blacklist";
    private final Long MAX_REQUESTS = (long) 60; // 1/sec for 1 minute
    private final int BLACKLIST_EXPIRATION = 60 * 60; // 1 Hour
    private final Logger logger = Logger.getLogger(FirewallService.class.getSimpleName());
    private AsyncMemcacheService requestsCache;
    private AsyncMemcacheService blacklistSecondLevelCache;
    /**
     * The first level local memory cache for faster processing
     */
    private SortedSet<Integer> blacklistFirstLevelCache = Collections.synchronizedSortedSet(new TreeSet<Integer>());
    private long nextFirstLevelCacheReset;
    private long FIRST_LEVEL_CACHE_RESET_INTERVAL = 60 * 1000;

    public FirewallService() {
        nextFirstLevelCacheReset = System.currentTimeMillis();
        requestsCache = MemcacheServiceFactory.getAsyncMemcacheService(REQUESTS_NAMESPACE);
        blacklistSecondLevelCache = MemcacheServiceFactory.getAsyncMemcacheService(BLACKLIST_NAMESPACE);
    }

    /**
     * Processes a request by incrementing the request count
     *
     * @param ip the remote ip address
     */
    public void processRequest(String ip) throws ExecutionException, InterruptedException {
        if (requestsCache.increment(ip, 1, (long) 0).get() >= MAX_REQUESTS) {
            logger.warning("New IP banned: " + ip);
            blacklistSecondLevelCache.put(ip, null, Expiration.byDeltaSeconds(BLACKLIST_EXPIRATION));
        }

        //Check if the cache can be cleared
        clearFirstLevelCache();
    }

    /**
     * Check if the IP is blacklisted
     *
     * @param ip the IP
     * @return true if its blacklisted, false otherwise
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean isBlacklisted(String ip) throws ExecutionException, InterruptedException {
        int ipHash = ip.hashCode();

        //Check first level
        if (blacklistFirstLevelCache.contains(ipHash)) return true;

        //Check second level
        if (blacklistSecondLevelCache.contains(ip).get()) {
            blacklistFirstLevelCache.add(ipHash);
            return true;
        }
        return false;
    }

    /**
     * Clear the first level cache
     */
    private void clearFirstLevelCache() {
        long time = System.currentTimeMillis();
        if (nextFirstLevelCacheReset <= time) {
            blacklistFirstLevelCache.clear();
            nextFirstLevelCacheReset = time + FIRST_LEVEL_CACHE_RESET_INTERVAL;
        }
    }

    /**
     * Clears the request cache
     */
    public void clearRequestCache() {
        requestsCache.clearAll();
    }

}
