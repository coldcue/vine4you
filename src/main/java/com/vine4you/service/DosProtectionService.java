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

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/2/13
 * Time: 3:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class DosProtectionService {
    public static boolean isAttacker(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Search for Siege
        if (request.getHeader("User-Agent").matches(".*Siege.*")) {
            response.sendError(403);
            return true;
        }

        return false;
    }
}
