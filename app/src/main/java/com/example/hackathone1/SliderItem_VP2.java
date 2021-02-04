package com.example.hackathone1;

public class SliderItem_VP2 {
    //Here we can use the String variable to store the image url
    //if we set the images from internet
    private int image;
    private int position;
    SliderItem_VP2(int image, int position){
        this.image = image;
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public int getPosition() {
        return position;
    }
}
