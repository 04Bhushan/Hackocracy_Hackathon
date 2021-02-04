package com.example.hackathone1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter_ForVP2 extends RecyclerView.Adapter<SliderAdapter_ForVP2.SlierViewHolder>{
    private List<SliderItem_VP2> sliderItem_vp2;
    private ViewPager2 viewPager2;
    Context context;
    int duplicatePOS = 0;

    public SliderAdapter_ForVP2(List<SliderItem_VP2> sliderItem_vp2, ViewPager2 viewPager2,Context context) {
        this.sliderItem_vp2 = sliderItem_vp2;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public SlierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlierViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container_for_viewpager2,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlierViewHolder holder, final int position) {
        holder.setImage(sliderItem_vp2.get(position));
        if(position == sliderItem_vp2.size() -2){
            viewPager2.post(runnable);
        }
        holder.roundedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"image "+ String.valueOf(duplicatePOS),Toast.LENGTH_LONG).show();
//                try{
//                    Intent intent = new Intent(context.getApplicationContext(), MainActivity2.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.getApplicationContext().startActivity(intent);
//                }catch (Exception e){
//
//                }
            }
        });

//        if(duplicatePOS == 0){
//
//        }
//        else if(duplicatePOS == 1){
//            holder.roundedImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context,"image "+ String.valueOf(duplicatePOS),Toast.LENGTH_LONG).show();
//
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return sliderItem_vp2.size();
    }

    class SlierViewHolder extends RecyclerView.ViewHolder
    {
        private RoundedImageView roundedImageView;

        public SlierViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
        }
        void setImage(SliderItem_VP2 sliderItem_vp2s){
            // If you want to display image form the internet,
            // you can put code here using glide or picasso.
            roundedImageView.setImageResource(sliderItem_vp2s.getImage());
            duplicatePOS = sliderItem_vp2s.getPosition();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItem_vp2.addAll(sliderItem_vp2);
            notifyDataSetChanged();
        }
    };
}

