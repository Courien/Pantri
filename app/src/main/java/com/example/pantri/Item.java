package com.example.pantri;

public class Item
{
    private String mIMageUrl;
    private String mCreator;
    private String mrecipe;
    private int mcalories;

    public Item(String imageUrl, String creator, String recipe, int calories)
    {
        mIMageUrl = imageUrl;
        mCreator = creator;
        mrecipe = recipe;
        mcalories = calories;

    }

    public String getImageURL()
    {
        return mIMageUrl;
    }

    public String getCreator()
    {
        return mCreator;
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