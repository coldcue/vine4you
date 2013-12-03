/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com) and                 *
 * Adrian Szell (szelladrian@gmail.com)                                       *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 9/1/13
 * Time: 6:12 PM
 * <p/>
 * This class is used to communicate with facebook
 */
@SuppressWarnings("FieldCanBeLocal")
public class FacebookService {
    private final Logger log = Logger.getLogger(FacebookService.class.getName());
    private final String graphURL = "https://graph.facebook.com/me/feed";
    private final String accessToken = "CAAIcMo11IVYBAGAowZAVIzSQW1jl91UY621WZBFwnvACpU6YCdXJGZAgQrl4ojkj1FYErZAQgFfy4GNFznJSQb44TUs7AOqLKr4Ci2xOpYACoMLNeDA6xBRQwZAnZACwDg3Iop94NzsZAt4JRIXGvntfg2ZBYTyj7vSUGBXHgYvEfvqPJxexKKhFFOnBFeZCZAaoUZD";

    /**
     * Publishes a video on the Facebook page
     *
     * @param id the ID of the video
     * @throws IOException
     */
    public void publishVideoToPage(long id) throws IOException {
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
            log.severe("Video " + id + " cannot be published on facebook! " + connection.getResponseMessage());
        }
    }

    /**
     * Fetches the like count on a video
     *
     * @param videoId the ID of the video
     * @return the number of likes
     * @throws IOException
     * @throws JSONException
     */
    public long getLikes(long videoId) throws IOException, JSONException {
        URL url = new URL("https://graph.facebook.com/?ids=" + URLEncoder.encode("http://www.vine4you.com/v/" + videoId, "UTF-8"));

        Reader reader = new InputStreamReader(url.openStream());

        JSONTokener jsonTokener = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(jsonTokener);

        //Get the link object
        String key = jsonObject.keys().next().toString();
        JSONObject linkObject = jsonObject.getJSONObject(key);

        //Get the shares
        long shares = linkObject.getLong("shares");

        reader.close();

        return shares;
    }
}
