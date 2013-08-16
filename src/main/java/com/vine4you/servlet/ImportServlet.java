/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.servlet;

import com.vine4you.service.ImportService;

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
public class ImportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImportService.letsdoit();
    }
}