package com.example.pantri;

public class Item
{
    private String mIMageUrl;
    private String mCreator;
    private String mrecipe;

    public Item(String imageUrl, String creator, String recipe)
    {
        mIMageUrl = imageUrl;
        mCreator = creator;
        mrecipe = recipe;

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

}