/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.entity;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


public class VideoEntity implements EntityInterface {

    public final static String kind = "Video";
    private Key key;
    private String title;
    private String author;

    public VideoEntity(Entity entity) {
        key = entity.getKey();
        title = (String) entity.getProperty("title");
        author = (String) entity.getProperty("author");
    }

    public VideoEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Entity generateEntity() {
        Entity entity = new Entity(kind);

        entity.setProperty("title", title);
        entity.setProperty("author", author);

        return entity;
    }
}
