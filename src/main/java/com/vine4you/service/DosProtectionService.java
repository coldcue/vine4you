/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/2/13
 * Time: 3:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class DosProtectionService {
    private static Logger logger = Logger.getLogger(DosProtectionService.class.getSimpleName());

    public static boolean isAttacker(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Search for Siege
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = (String) headerNames.nextElement();
            if (request.getHeader(header).matches(".*Siege.*")) {
                logger.severe("DDOS ATTACK CATCHED");
                response.sendError(403);
                return true;
            }
        }

        return false;
    }
}
