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

import com.vine4you.enums.AdminTasks;
import com.vine4you.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/16/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] split = request.getPathInfo().split("/", 2);
        AdminTasks task = AdminTasks.valueOf(split[1].toUpperCase());

        switch (task) {

            case REFRESH:
                int count = AdminService.importVideos(false);
                response.getWriter().println(count + " videos refreshed!");
                break;
            case NEW:
                int count2 = AdminService.importVideos(true);
                response.getWriter().println(count2 + " videos refreshed!");
                break;

            case DELETE:
                int deletecount = AdminService.deleteVideos();
                response.getWriter().println(deletecount + " videos deleted!");
                break;
        }
    }
}
