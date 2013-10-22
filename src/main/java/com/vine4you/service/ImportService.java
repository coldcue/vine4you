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

import au.com.bytecode.opencsv.CSVReader;
import com.vine4you.entity.VideoEntity;
import com.vine4you.factories.VideoServiceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * User: Andrew
 * Date: 8/16/13
 * Time: 11:24 AM
 */
public class ImportService {
    private static final Logger log = Logger.getLogger(ImportService.class.getName());

    public static int letsdoit(boolean asNew) {
        int count = 0;

        try {
            URL url = new URL("http://import.vine4you.com/import.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream(), Charset.forName("UTF-8"));
            CSVReader reader = new CSVReader(new BufferedReader(inputStreamReader));
            String[] line;

            //title,author,vine_url,video_url,image_url
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(VideoEntity.TITLE)) continue;

                String videoURL = (line[3].contains("?")) ? line[3].substring(0, line[3].indexOf("?")) : line[3];
                String imageURL = (line[4].contains("?")) ? line[4].substring(0, line[4].indexOf("?")) : line[4];

                VideoEntity videoEntity = new VideoEntity(line[0], line[1], line[2], videoURL, imageURL, Calendar.getInstance().getTime(), true);

                try {
                    VideoServiceFactory.getVideoService().addVideoEntity(videoEntity, asNew);
                    count++;
                } catch (Exception e) {
                    log.severe("Can`t import video: " + line[0] + " " + line[1] + " " + line[2]);
                }
            }

            inputStreamReader.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
