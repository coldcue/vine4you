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
    private static final String accessToken = "CAACEdEose0cBABg9Qk27ZCDIGca44NisIf5CZCsnWZCwnEZBkPJZBcveKa2DyBfvkS0uuSMGR2AZCAX6WkxfPGE1HRZAS1maXVgqZAtQfG2EHOxbZA4UGZBkMAAOHBkKKnXoiqiwlteFaC2DiLTnhZB1J6obOZA7qXYecKIZCpi9fm0xRoIMxcPwvGuEzmEW5SslnUAMynUCgrNIx0wZDZD";

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
