package com.example.pantri;

public class Item
{
    private String mIMageUrl;
    private String mMeal;
    private String mrecipe;
    private int mcalories;
    private String mNutrition;
    private String mPrepingSteps;

    public Item(String imageUrl, String meal, String recipe, int calories, String nutrition, String prepingSteps)
    {
        mIMageUrl = imageUrl;
        mMeal = meal;
        mrecipe = recipe;
        mcalories = calories;
        mNutrition = nutrition;
        mPrepingSteps = prepingSteps;

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

    public String getNutrition()
    {
        return mNutrition;
    }

    public String getPreparationSteps()
    {
        return mPrepingSteps;
    }

}