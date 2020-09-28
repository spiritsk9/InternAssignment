package com.learn2crack.recyclerswipeview;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ImageClickListener {
    private static final int IMG_REQUEST = 121;
    private ArrayList<String> imageDataList = new ArrayList<>();
    private DataAdapter adapter;
    private RecyclerView recyclerView;
    private AlertDialog.Builder alertDialog;
    private EditText et_country;
    private int edit_position;
    private View view;
    private boolean add = false;
    private Paint p = new Paint();
    private int itemPosition;
    private DataAdapter.ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDialog();
    }

    private void initViews() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DataAdapter(imageDataList, this, this);
        recyclerView.setAdapter(adapter);
        //initSwipe();


    }



    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        alertDialog.setView(view);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (add) {
                    add = false;
                    adapter.addItem(et_country.getText().toString());
                    dialog.dismiss();
                } else {
                    imageDataList.set(edit_position, et_country.getText().toString());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        et_country = (EditText) view.findViewById(R.id.et_country);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                removeView();
                add = true;
                alertDialog.setTitle("Type the title");
                et_country.setText("");
                alertDialog.show();
                break;
        }
    }

    @Override
    public void onImageClicked(int position, DataAdapter.ViewHolder viewHolder) {
        itemPosition = position;
        holder = viewHolder;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Pictures: "), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<Uri> uri = new ArrayList<>();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == this.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        uri.add(data.getClipData().getItemAt(i).getUri());
                    }
                    adapter.setImageList(uri, itemPosition, holder);

                }
            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
            }
        }
    }
}
