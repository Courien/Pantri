package com.example.pantri;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private Context mContext;
    private ArrayList<Item> mItemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

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
        String Meal = currentItem.getMeal();
        String recipe = currentItem.getRecipe();
        int calories = currentItem.getcalories();

        holder.mTextViewMeal.setText(Meal);
        holder.mTextViewRecipe.setText("Recipe: " + recipe);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.mTextViewCalories.setText("Calories: " + calories);


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView mImageView;
        public TextView mTextViewMeal;
        public TextView mTextViewRecipe;
        public TextView mTextViewCalories;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewMeal = itemView.findViewById(R.id.text_view_meal);
            mTextViewRecipe = itemView.findViewById(R.id.text_view_recipe);
            mTextViewCalories = itemView.findViewById(R.id._calories);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (mListener != null)
                    {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}


