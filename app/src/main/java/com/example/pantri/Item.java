package com.example.pantri;

public class Item
{
    private String mIMageUrl;
    private String mMeal;
    private String mrecipe;
    private int mcalories;

    public Item(String imageUrl, String meal, String recipe, int calories)
    {
        mIMageUrl = imageUrl;
        mMeal = meal;
        mrecipe = recipe;
        mcalories = calories;

    }

    public String getImageURL()
    {
        return mIMageUrl;
    }

    public String getMeal()
    {
        return mMeal;
    }

    public String getRecipe()
    {
        return mrecipe;

    }

    public int getcalories()
    {
        return mcalories;
    }
}