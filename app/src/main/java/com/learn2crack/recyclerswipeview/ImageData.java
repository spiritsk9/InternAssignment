package com.learn2crack.recyclerswipeview;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class ImageData {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Uri> getUris() {
        return uris;
    }

    public void setUris(ArrayList<Uri> uris) {
        this.uris = uris;
    }

    String title;
    ArrayList<Uri> uris;
}
