/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/2/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filePath = "/WEB-INF/static/" + req.getPathInfo().substring(1);
        try {
            req.getRequestDispatcher(filePath).include(req, resp);
        } catch (ServletException | IOException e) {
            resp.sendError(404);
            return;
        }

        if (filePath.matches(".*\\.css"))
            resp.setHeader("Content-Type", "text/css");
        else if (filePath.matches(".*\\.js"))
            resp.setHeader("Content-Type", "text/javascript");
        else if (filePath.matches(".*\\.png"))
            resp.setHeader("Content-Type", "image/png");

        resp.setHeader("Cache-Control", "public, max-age=31536000");
        resp.setHeader("Last-Modified", getServerTime());
    }

    private String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}
