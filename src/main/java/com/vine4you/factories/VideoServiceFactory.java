/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com) and                 *
 * Adrian Szell (szelladrian@gmail.com)                                       *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.factories;

import com.vine4you.service.VideoService;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/16/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class VideoServiceFactory {
    private static VideoService videoService = new VideoService();

    public static VideoService getVideoService() {
        return videoService;
    }
}
