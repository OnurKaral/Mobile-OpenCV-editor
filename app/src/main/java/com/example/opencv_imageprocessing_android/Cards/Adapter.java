package com.example.opencv_imageprocessing_android.Cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.opencv_imageprocessing_android.R;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    //****************************************************************************************************
    private List<ImagesData> dataModelList;
    private Context mContext;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImageView;
        public TextView titleTextView;
        public TextView subTitleTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.card_title);
            subTitleTextView = itemView.findViewById(R.id.card_subtitle);
        }

        public void bindData(ImagesData dataModel, Context context) {
            cardImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_image));
            titleTextView.setText(dataModel.getTitle());
            subTitleTextView.setText(dataModel.getSubTitle());
        }
    }

    public Adapter(List<ImagesData> modelList, Context context) {
        dataModelList = modelList;
        mContext = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate out card list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        // Return a new view holder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind data for the item at position
        holder.bindData(dataModelList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        // Return the total number of items
        return dataModelList.size();
    }
}