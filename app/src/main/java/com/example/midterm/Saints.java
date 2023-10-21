package com.example.midterm;

import android.content.Context;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Saints extends RecyclerView.Adapter<Saints.ViewHolder> {

    private ArrayList<Athletics> parseAthletics;
    private Context context;

    public Saints (ArrayList<Athletics> parseAthletics, Context context){
    this.parseAthletics = parseAthletics;
    this.context= context;
    }


    @NonNull
    @Override
    public Saints.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Athletics athletics = parseAthletics.get(position);
         holder.textView.setText(athletics.getTitle());
        holder.textViewNumber.setText(athletics.getNumber());
        Glide.with(holder.imageView.getContext()).load(athletics.getImgUrl()).into(holder.imageView);
        Matrix matrix = new Matrix();
        holder.imageView.setScaleType(ImageView.ScaleType.MATRIX);   // required
        matrix.postTranslate(0, -100);  // Move the image downwards by 20 pixels
        holder.imageView.setImageMatrix(matrix);
    }

    @Override
    public int getItemCount() {
        return parseAthletics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textViewNumber;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             imageView=itemView.findViewById(R.id.imageView);
             textView = itemView.findViewById(R.id.textView);
             textViewNumber = itemView.findViewById(R.id.textViewNumber);
        }


    }
}
