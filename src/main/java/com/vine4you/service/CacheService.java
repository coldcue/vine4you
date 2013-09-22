/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import com.vine4you.entity.VideoEntity;
import com.vine4you.enums.CacheType;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/22/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CacheService {
    private static Cache cache;

    static {
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static Cache getCache() {
        return CacheService.cache;
    }

    /**
     * Clear cache
     */
    public static void clearCache() {
        try {
            cache.clear();
        } catch (Exception ignored) {

        }
    }

    /**
     * Add a video to the cache
     *
     * @param type  the CacheType
     * @param video the VideoEntity
     */
    public static void addVideoEntity(CacheType type, VideoEntity video) {
        try {
            cache.put(type.toString(), video);
        } catch (Exception ignored) {

        }
    }

    /**
     * Add a video to the cache
     *
     * @param type the CacheType
     */
    public static VideoEntity getVideoEntity(CacheType type) {
        try {
            return (VideoEntity) cache.get(type.toString());
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Add a featured list
     *
     * @param type          type
     * @param videoEntity   the video
     * @param videoEntities the list
     */
    public static void addFeaturedList(CacheType type, VideoEntity videoEntity, List<VideoEntity> videoEntities) {
        try {
            cache.put(type.toString() + "_" + videoEntity.getKey().getId(), videoEntities);
        } catch (Exception ignored) {

        }
    }

    /**
     * Get a featured list
     *
     * @param type        type
     * @param videoEntity the video
     */
    public static List<VideoEntity> getFeaturedList(CacheType type, VideoEntity videoEntity) {
        try {
            //noinspection unchecked
            return (List<VideoEntity>) cache.get(type.toString() + "_" + videoEntity.getKey().getId());
        } catch (Exception ignored) {
            return null;
        }
    }

}
