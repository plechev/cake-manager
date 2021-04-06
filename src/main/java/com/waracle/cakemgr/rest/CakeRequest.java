package com.waracle.cakemgr.rest;

import com.waracle.cakemgr.domain.Cake;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class CakeRequest {

    @NotEmpty
    @Size(max = 100)
    private String title;
    @NotEmpty
    @Size(max = 100)
    private String description;
    @NotEmpty
    @Size(max = 300)
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public Cake assembleCake() {
        final Cake cake = new Cake();
        cake.setTitle(title);
        cake.setDescription(description);
        cake.setImage(image);
        return cake;
    }
}
