/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.service;

import au.com.bytecode.opencsv.CSVReader;
import com.vine4you.entity.VideoEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/16/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImportService {
    public static void letsdoit() {
        try {
            URL url = new URL("http://www.galaxyhosting.eu/import.csv");
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(url.openStream())));
            String[] line;

            //title,author,vine_url,video_url,image_url
            while ((line = reader.readNext()) != null) {
                if (line[0].equals("title")) continue;
                VideoEntity videoEntity = new VideoEntity(line[0], line[1], line[2], line[3], line[4], Calendar.getInstance().getTime(), true);

                try {
                    VideoService.addVideoEntity(videoEntity);
                } catch (Exception e) {
                    //do nothing
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}