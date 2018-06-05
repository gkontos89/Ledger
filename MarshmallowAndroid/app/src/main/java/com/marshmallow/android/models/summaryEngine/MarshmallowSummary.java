package com.marshmallow.android.models.summaryEngine;

/**
 * Created by George on 6/4/2018.
 */
public interface MarshmallowSummary {
    public int getNumberOfCategories();

    public int getValueByCategory(String category);

    public int getTotalValue();

    public int getCategoryPercentOfTotal(String category);

    public void decreaseCategoryTotal(String category, int amount);

    public void increaseCategoryTotal(String category, int amount);
}
