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

import com.vine4you.service.VideoManagerService;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/22/13
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class VideoManagerServiceFactory {
    private static VideoManagerService videoEntityService = new VideoManagerService();

    public static VideoManagerService getVideoManagerService() {
        return videoEntityService;
    }
}
