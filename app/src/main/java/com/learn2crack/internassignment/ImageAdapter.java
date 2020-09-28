package com.learn2crack.recyclerswipeview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<Uri> uriList;

    public ImageAdapter(ArrayList<Uri> uri) {
        uriList = uri;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_layout, parent, false);
        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageURI(Uri.parse(uriList.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public void setList(ArrayList<Uri> uri) {
        uriList = uri;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}
