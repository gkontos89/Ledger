package com.marshmallow.android.model.education;

import java.util.HashMap;

/**
 * Created by George on 11/28/2018.
 */
public class Education {
    protected String degreeTitle;
    protected String school;
    protected int cost;
    protected HashMap<String, Education> preRequisites;

    public Education(String degreeTitle, String school, int cost) {
        this.degreeTitle = degreeTitle;
        this.school = school;
        this.cost = cost;
        preRequisites = new HashMap<>();
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public String getSchool() {
        return school;
    }

    public int getCost() {
        return cost;
    }

    public HashMap<String, Education> getPreRequisites() {
        return preRequisites;
    }

    public boolean educationObtainable(HashMap<String, Education> preRequisites) {
        int prerequisiteHits = 0;
        for (String preRequisiteKey : preRequisites.keySet()) {
            if (this.preRequisites.containsKey(preRequisiteKey)) {
                prerequisiteHits++;
            }
        }

        return (prerequisiteHits == this.preRequisites.size());
    }
}
