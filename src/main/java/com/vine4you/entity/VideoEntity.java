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

import java.io.Serializable;
import java.util.Date;

/**
 * The transient values for cache storage optimization
 */
@SuppressWarnings("UnusedDeclaration")
public class VideoEntity implements EntityInterface, Serializable {

    public final static String kind = "Video";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String VINE_URL = "vineURL";
    public static final String VIDEO_URL = "videoURL";
    public static final String IMAGE_URL = "imageURL";
    public static final String PUBLISHED_DATE = "publishedDate";
    public static final String PUBLISHED = "published";
    public static final String LIKES = "likes";
    public static final String LIKEORDER = "likeOrder";
    private Key key;
    private String title;
    private String author;
    private transient String vineURL;
    private String videoURL;
    private String imageURL;
    private transient Date publishedDate;
    private transient boolean published;
    private transient long likes;
    private transient long likeOrder;

    public VideoEntity(Entity entity) {
        key = entity.getKey();
        if (entity.hasProperty(TITLE))
            title = (String) entity.getProperty(TITLE);
        if (entity.hasProperty(AUTHOR))
            author = (String) entity.getProperty(AUTHOR);
        if (entity.hasProperty(VINE_URL))
            vineURL = (String) entity.getProperty(VINE_URL);
        if (entity.hasProperty(VIDEO_URL))
            videoURL = (String) entity.getProperty(VIDEO_URL);
        if (entity.hasProperty(IMAGE_URL))
            imageURL = (String) entity.getProperty(IMAGE_URL);
        if (entity.hasProperty(PUBLISHED_DATE))
            publishedDate = (Date) entity.getProperty(PUBLISHED_DATE);
        if (entity.hasProperty(PUBLISHED))
            published = (boolean) entity.getProperty(PUBLISHED);
        if (entity.hasProperty(LIKES))
            likes = (long) entity.getProperty(LIKES);
        if (entity.hasProperty(LIKEORDER))
            likeOrder = (long) entity.getProperty(LIKEORDER);
    }

    public VideoEntity(String title, String author, String vineURL, String videoURL, String imageURL, Date publishedDate, boolean published) {
        this.title = title;
        this.author = author;
        this.vineURL = vineURL;
        this.videoURL = videoURL;
        this.imageURL = imageURL;
        this.publishedDate = publishedDate;
        this.published = published;
        this.likes = 0;
        this.likeOrder = Long.MAX_VALUE;
    }

    public static String getKind() {
        return kind;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
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

    public long getLikeOrder() {
        return likeOrder;
    }

    public void setLikeOrder(long likeOrder) {
        this.likeOrder = likeOrder;
    }

    /**
     * Only use it with fully loaded entity!!!
     *
     * @return the Entity
     */
    @Override
    public Entity generateEntity() {
        Entity entity = new Entity(kind);

        entity.setProperty(TITLE, title);
        entity.setProperty(AUTHOR, author);
        entity.setProperty(VINE_URL, vineURL);
        entity.setProperty(VIDEO_URL, videoURL);
        entity.setProperty(IMAGE_URL, imageURL);
        entity.setProperty(PUBLISHED_DATE, publishedDate);
        entity.setProperty(PUBLISHED, published);
        entity.setProperty(LIKES, likes);
        entity.setProperty(LIKEORDER, likeOrder);

        return entity;
    }
}
