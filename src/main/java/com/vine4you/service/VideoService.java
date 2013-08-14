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

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/14/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class VideoService {

    private static DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    public static VideoEntity getVideoEntity(long id) throws EntityNotFoundException {
        Entity video = datastoreService.get(KeyFactory.createKey(VideoEntity.kind, id));
        return new VideoEntity(video);
    }

    public static Key addVideoEntity(VideoEntity videoEntity) {
        return datastoreService.put(videoEntity.generateEntity());
    }
}
