package com.marshmallow.android.models;

import java.util.HashMap;

/**
 * Created by George on 11/28/2018.
 */
public class Career {
    protected String careerTitle;
    protected int salary;
    protected HashMap<String, Education> preRequisites;

    public Career(String careerTitle, int salary) {
        this.careerTitle = careerTitle;
        this.salary = salary;
        preRequisites = new HashMap<>();
    }

    public String getCareerTitle() {
        return careerTitle;
    }

    public int getSalary() {
        return salary;
    }

    public HashMap<String, Education> getPreRequisites() {
        return preRequisites;
    }

    public boolean careerObtainable(HashMap<String, Education> preRequisites) {
        int prerequisiteHits = 0;
        for (String preRequisiteKey : preRequisites.keySet()) {
            if (this.preRequisites.containsKey(preRequisiteKey)) {
                prerequisiteHits++;
            }
        }

        return (prerequisiteHits == this.preRequisites.size());
    }

    public int applyPayCheck() {
        // TODO introduce tax rates hee hee hee!
        return (salary / 12);
    }
}
