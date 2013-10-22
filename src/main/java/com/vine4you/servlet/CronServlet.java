/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com) and                 *
 * Adrian Szell (szelladrian@gmail.com)                                       *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.servlet;

import com.vine4you.cron.CronTasks;
import com.vine4you.factories.CronServiceFactory;
import com.vine4you.factories.FirewallServiceFactory;
import com.vine4you.service.CronService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/17/13
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CronServlet extends HttpServlet {
    private CronService cronService = CronServiceFactory.getCronService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String[] split = request.getPathInfo().split("/", 2);
        CronTasks task = CronTasks.valueOf(split[1].toUpperCase());

        switch (task) {

            case PUBLISHVIDEO:
                cronService.publishVideo();
                break;

            case REFRESHLIKES:
                cronService.refreshLikes();
                break;

            case CLEARREQUESTCACHE:
                FirewallServiceFactory.getFirewallService().clearRequestCache();
                break;
        }


    }
}
