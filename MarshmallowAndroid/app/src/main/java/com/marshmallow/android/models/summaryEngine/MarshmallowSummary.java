package com.marshmallow.android.models.summaryEngine;

/**
 * Created by George on 6/4/2018.
 */
public interface MarshmallowSummary {
    int getNumberOfCategories();

    int getValueByCategory(String category);

    int getTotalValue();

    int getCategoryPercentOfTotal(String category);

    void decreaseCategoryTotal(String category, int amount);

    void increaseCategoryTotal(String category, int amount);
}
