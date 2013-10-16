/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
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
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("FieldCanBeLocal")
public class FacebookService {
    private final Logger log = Logger.getLogger(FacebookService.class.getName());
    private final String graphURL = "https://graph.facebook.com/me/feed";
    private final String accessToken = "CAAIcMo11IVYBAK4RQSugq4Hfl2WiKZAZCqMTm7hXYon8w0cNeEVuAXDoy7430k1d1lGCLrIKB8A68SMPmGOHdjuiIEqFkJLvVPTbqREhG7ef0ZB0H5bvZBTN5n8WYiLe5PjAPONk2BY5huZCfOoT0yDKZBBz5J2BoZAmzDSIV3k8cO1zrTPx6dFCh0d3TZBzfMoZD";

    public void publishVideo(long id) throws IOException {
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
