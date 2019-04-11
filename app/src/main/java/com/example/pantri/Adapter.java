package com.example.pantri;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private Context mContext;
    private ArrayList<Item> mItemList;

    public Adapter(Context context, ArrayList<Item> itemList)
    {
        mContext = context;
        mItemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int i)
    {
        Item currentItem = mItemList.get(i);

        String imageUrl = currentItem.getImageURL();
        String creatorName = currentItem.getCreator();
        String recipe = currentItem.getRecipe();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewRecipe.setText("Recipe: " + recipe);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewRecipe;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_meal);
            mTextViewRecipe = itemView.findViewById(R.id.text_view_recipe);
        }
    }
}


