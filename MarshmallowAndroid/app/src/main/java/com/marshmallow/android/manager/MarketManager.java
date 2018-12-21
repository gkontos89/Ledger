package com.marshmallow.android.manager;

import com.marshmallow.android.model.asset.Asset;
import com.marshmallow.android.model.asset.MidSizedHome;
import com.marshmallow.android.model.career.Career;
import com.marshmallow.android.model.career.Teacher;
import com.marshmallow.android.model.education.BachelorsDegree;
import com.marshmallow.android.model.education.Education;
import com.marshmallow.android.model.education.HighSchoolDiploma;

import java.util.Vector;

/**
 * Created by George on 11/29/2018.
 */
public class MarketManager {
    private static MarketManager instance = null;
    private Vector<Asset> housingMarket;
    private Vector<Asset> automotiveMarket;
    private Vector<Education> educationMarket;
    private Vector<Career> jobMarket;

    private MarketManager() {
        generateHousingMarket();
        generateAutomotiveMarket();
        generateEducationMarket();
        generateJobMarket();
    }

    private void generateJobMarket() {
        jobMarket = new Vector<>();
        jobMarket.add(new Teacher());
    }

    private void generateEducationMarket() {
        educationMarket = new Vector<>();
        educationMarket.add(new HighSchoolDiploma());
        educationMarket.add(new BachelorsDegree());
    }

    private void generateAutomotiveMarket() {
        automotiveMarket = new Vector<>();
    }

    private void generateHousingMarket() {
        housingMarket = new Vector<>();
        housingMarket.add(new MidSizedHome());
    }

    public static MarketManager getInstance() {
        if (instance == null) {
            instance = new MarketManager();
        }

        return instance;
    }
}
