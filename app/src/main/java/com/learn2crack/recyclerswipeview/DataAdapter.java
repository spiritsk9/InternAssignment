package com.learn2crack.recyclerswipeview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private final MainActivity context;
    private final ImageClickListener imageListner;
    private ArrayList<String> title;
    private ImageAdapter adapter;
    private ArrayList<Uri> uriList=new ArrayList<>();

    public DataAdapter(ArrayList<String> title, MainActivity mainActivity, ImageClickListener listener) {
        this.title = title;
        this.context = mainActivity;
        this.imageListner = listener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        viewHolder.tv_country.setText(title.get(position));
        viewHolder.btnGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageListner.onImageClicked(position, viewHolder);
            }
        });
        viewHolder.recyclerView.setHasFixedSize(true);
        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        adapter = new ImageAdapter(uriList);
        viewHolder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public void addItem(String titles) {
        title.add(titles);
        notifyItemInserted(title.size());
    }

    public void removeItem(int position) {
        title.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, title.size());
    }

    public void setImageList(ArrayList<Uri> uri, int itemPosition, ViewHolder holder) {
        uriList = uri;
        adapter.setList(uriList);
        adapter.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_country;
        ImageButton btnGallary;
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            tv_country = (TextView) view.findViewById(R.id.tv_country);
            btnGallary = (ImageButton) view.findViewById(R.id.gallery);
            recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        }
    }
}