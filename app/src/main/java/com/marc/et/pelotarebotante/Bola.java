package com.marc.et.pelotarebotante;

import android.widget.ImageView;

public class Bola {
    //coordenadas
    private float posX;
    private float posy;
    //velocidades
    private float vX;
    private float vy;
    //image
    private ImageView imageView;

    public Bola(float posX, float posy, float vX, float vy, ImageView imageView) {
        this.posX = posX;
        this.posy = posy;
        this.vX = vX;
        this.vy = vy;
        this.imageView = imageView;
    }

    //getters
    public float getPosX() {
        return posX;
    }

    public float getPosy() {
        return posy;
    }

    public float getvX() {
        return vX;
    }

    public float getVy() {
        return vy;
    }

    public ImageView getImageView() {
        return imageView;
    }

    //setters
    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }

    public void setvX(float vX) {
        this.vX = vX;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
