/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/1/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookService {
    private static final Logger log = Logger.getLogger(FacebookService.class.getName());
    private static final String graphURL = "https://graph.facebook.com/me/feed";
    private static final String accessToken = "CAAIcMo11IVYBACTNXTl4eJoXi01Gw407aDPGUoZAh3RDigCwOv9ZCWKydZB9iCZAwSXEi89cNL9xc1thpUr2HSOOqxDOPpx14mpM1lKj5HaRMURZBRik7u8GZBBhWHOUn1DN8R0BnReSHMxB52V4B1NL2lWYqgFMzhThp9ZCi61UORJ92dLxHJ2weS2IvtEKfUZD";

    public static void publishVideo(long id) throws IOException {
        URL url = new URL(graphURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write("access_token=" + URLEncoder.encode(accessToken, "UTF-8") + '&');
        writer.write("link=" + URLEncoder.encode("http://www.vine4you.com/v/" + id, "UTF-8"));
        writer.close();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            log.info("Video " + id + " is published on facebook!");
        } else {
            log.severe("Video " + id + " cannot be published on facebook!");
        }
    }
}
