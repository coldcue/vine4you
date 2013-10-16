/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.filter;

import com.vine4you.factories.FirewallServiceFactory;
import com.vine4you.service.FirewallService;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/2/13
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirewallFilter implements Filter {

    private FirewallService firewallService = FirewallServiceFactory.getFirewallService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //Check if the IP is banned
            if (firewallService.isBlacklisted(servletRequest.getRemoteAddr())) {
                //If its banned, simply close the streams
                servletRequest.getInputStream().close();
                servletResponse.getOutputStream().close();
                return;
            }

            firewallService.processRequest(servletRequest.getRemoteAddr());
        } catch (ExecutionException | InterruptedException e) {
            //e.printStackTrace();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
