package com.example.opencv_imageprocessing_android.Cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.opencv_imageprocessing_android.R;
import com.example.opencv_imageprocessing_android.Upload;
import com.squareup.picasso.Picasso;
import java.util.List;

//****************************************************************************************************
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Upload> dataModelList;
    private Context mContext;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImageView;
        public TextView titleTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            
            cardImageView = itemView.findViewById(R.id.card_imageview);
            titleTextView = itemView.findViewById(R.id.card_textview);
        }
    }

    public Adapter(Context context, List<Upload> uploads) {
            mContext = context;
            dataModelList = uploads;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(mContext).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Upload uploadCurrent = dataModelList.get(position);
        holder.titleTextView.setText(uploadCurrent.getName());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerInside()
                .into(holder.cardImageView);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }
}