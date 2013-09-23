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
        } catch (Exception ignored) {

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
            cache.put(type.toString(), video);
        } catch (Exception ignored) {

        }
    }

    /**
     * Get an unique video from the cache. This is used for the first videos and etc.
     *
     * @param type the CacheType
     */
    public static VideoEntity getVideoEntity(CacheType type) {
        try {
            return (VideoEntity) cache.get(type.toString());
        } catch (Exception ignored) {
            cache.remove(type.toString());
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
        String key = type.toString() + "_" + videoEntity.getKey().getId();

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

        } catch (Exception ignored) {
        }
    }

    private static boolean isVideoEntityCached(long id) {
        return cache.containsKey(VideoEntity.getKind() + "_" + id);
    }

    /**
     * Get a featured list
     *
     * @param type        type
     * @param videoEntity the video
     */
    public static List<VideoEntity> getFeaturedList(CacheType type, VideoEntity videoEntity) {
        //The key in the cache
        String key = type.toString() + "_" + videoEntity.getKey().getId();

        try {
            //noinspection unchecked
            List<Long> videoEntityIDList = (List<Long>) cache.get(key);
            //if its not cached
            if (videoEntityIDList == null) return null;
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
            cache.put(VideoEntity.getKind() + "_" + entity.getKey().getId(), entity);
        } catch (Exception ignored) {

        }
    }

    /**
     * Get a VideoEntity from the cache
     *
     * @param id the id of the VideoEntity
     * @return the videoEntity
     */
    public static VideoEntity getVideoEntity(long id) {
        String key = VideoEntity.getKind() + "_" + id;
        try {
            return (VideoEntity) cache.get(key);
        } catch (Exception ignored) {
            cache.remove(key);
            return null;
        }
    }

}
