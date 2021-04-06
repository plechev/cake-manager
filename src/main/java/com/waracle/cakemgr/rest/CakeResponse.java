package com.waracle.cakemgr.rest;

import com.waracle.cakemgr.domain.Cake;

public class CakeResponse implements Comparable<CakeResponse> {

    private final Integer id;
    private final String title;
    private final String description;
    private final String image;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public CakeResponse(final Cake cake) {
        this.id = cake.getId();
        this.title = cake.getTitle();
        this.description = cake.getDescription();
        this.image = cake.getImage();
    }

    @Override
    public int compareTo(final CakeResponse other) {
        return getTitle().compareTo(other.getTitle());
    }

}
