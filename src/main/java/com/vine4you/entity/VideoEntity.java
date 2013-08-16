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

import java.util.Date;


public class VideoEntity implements EntityInterface {

    public final static String kind = "Video";
    private Key key;
    private String title;
    private String author;
    private String vineURL;
    private String videoURL;
    private String imageURL;
    private Date publishedDate;
    private boolean published;

    public VideoEntity(Entity entity) {
        key = entity.getKey();
        title = (String) entity.getProperty("title");
        author = (String) entity.getProperty("author");
        vineURL = (String) entity.getProperty("vineURL");
        videoURL = (String) entity.getProperty("videoURL");
        imageURL = (String) entity.getProperty("imageURL");
        publishedDate = (Date) entity.getProperty("publishedDate");
        published = (boolean) entity.getProperty("published");
    }

    public VideoEntity(String title, String author, String vineURL, String videoURL, String imageURL, Date publishedDate, boolean published) {
        this.title = title;
        this.author = author;
        this.vineURL = vineURL;
        this.videoURL = videoURL;
        this.imageURL = imageURL;
        this.publishedDate = publishedDate;
        this.published = published;
    }

    public static String getKind() {
        return kind;
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

    public String getVineURL() {
        return vineURL;
    }

    public void setVineURL(String vineURL) {
        this.vineURL = vineURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public Entity generateEntity() {
        Entity entity = new Entity(kind);

        entity.setProperty("title", title);
        entity.setProperty("author", author);
        entity.setProperty("vineURL", vineURL);
        entity.setProperty("videoURL", videoURL);
        entity.setProperty("imageURL", imageURL);
        entity.setProperty("publishedDate", publishedDate);
        entity.setProperty("published", published);

        return entity;
    }
}
