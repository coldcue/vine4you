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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vine4you.enums.CacheType.VIDEO;

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

    /**
     * Clear cache
     */
    public static void clearCache() {
        try {
            cache.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a unique video to the cache with CacheType. This is used for the first videos and etc.
     *
     * @param type  the CacheType
     * @param video the VideoEntity
     */
    public static void addVideoEntity(CacheType type, VideoEntity video) {
        try {
            String key = generateKey(type);

            //Remove if exists
            if (cache.containsKey(key))
                cache.remove(key);

            cache.put(key, video);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get an unique video from the cache. This is used for the first videos and etc.
     *
     * @param type the CacheType
     */
    public static VideoEntity getVideoEntity(CacheType type) {
        String key = generateKey(type);
        try {
            //Check if exists
            if (cache.containsKey(key)) {
                return (VideoEntity) cache.get(key);
            }
            return null;
        } catch (Exception ignored) {
            cache.remove(key);
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
        //The key in the cache
        String key = generateKey(type, videoEntity);

        try {
            List<Long> videoEntityIDList = new ArrayList<>(videoEntities.size());

            for (VideoEntity entity : videoEntities) {
                //Add the id
                long id = entity.getKey().getId();
                videoEntityIDList.add(id);

                //Check the cache
                if (!isVideoEntityCached(id))
                    //Add if not contains
                    addVideoEntity(entity);
            }
            cache.put(key, videoEntityIDList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the cache contains it and false if not
     *
     * @param id the id of the video
     * @return true, false
     */
    private static boolean isVideoEntityCached(long id) {
        return cache.containsKey(generateKey(VIDEO, id));
    }

    /**
     * Get a featured list
     *
     * @param type        type
     * @param videoEntity the video
     */
    public static List<VideoEntity> getFeaturedList(CacheType type, VideoEntity videoEntity) {
        //The key in the cache
        String key = generateKey(type, videoEntity);

        try {
            //if its not cached
            if (!cache.containsKey(key))
                return null;

            //noinspection unchecked
            List<Long> videoEntityIDList = (List<Long>) cache.get(key);

            List<VideoEntity> videoEntities = new ArrayList<>(videoEntityIDList.size());

            for (Long id : videoEntityIDList) {
                //Check cache
                VideoEntity entity = VideoService.getVideoEntityCached(id);
                //Add to list
                videoEntities.add(entity);
            }

            return videoEntities;
        } catch (Exception ignored) {
            cache.remove(key);
            return null;
        }
    }

    /**
     * Add a VideoEntity to the cache
     *
     * @param entity the VideoEntity
     */
    public static void addVideoEntity(VideoEntity entity) {
        try {
            String key = generateKey(VIDEO, entity);

            //check if exists
            if (cache.containsKey(key)) {
                cache.remove(key);
            }
            cache.put(key, entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a VideoEntity from the cache
     *
     * @param id the id of the VideoEntity
     * @return the videoEntity
     */
    public static VideoEntity getVideoEntity(long id) {
        String key = generateKey(VIDEO, id);
        try {
            if (cache.containsKey(key)) {
                return (VideoEntity) cache.get(key);
            }
            return null;
        } catch (Exception ignored) {
            cache.remove(key);
            return null;
        }
    }

    /**
     * Generate cache key to save storage
     *
     * @param cacheType cache type
     * @param id        the ID of the video or list
     * @return the key
     */
    private static String generateKey(CacheType cacheType, long id) {
        return cacheType.ordinal() + "_" + Long.toHexString(id);
    }

    /**
     * Generate cache key to save storage
     *
     * @param cacheType cache type
     * @return the key
     */
    private static String generateKey(CacheType cacheType) {
        return Integer.toString(cacheType.ordinal());
    }

    /**
     * Generate cache key to save storage
     *
     * @param cacheType   cache type
     * @param videoEntity the videoentity
     * @return the key
     */
    private static String generateKey(CacheType cacheType, VideoEntity videoEntity) {
        return generateKey(cacheType, videoEntity.getKey().getId());
    }

}
