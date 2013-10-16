/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.factories;

import com.vine4you.service.CronService;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/16/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class CronServiceFactory {
    private static CronService cronService = new CronService();

    public static CronService getCronService() {
        return cronService;
    }
}
