package com.marshmallow.android.model.education;

/**
 * Created by George on 11/29/2018.
 */
public class BachelorsDegree extends Education {
    public BachelorsDegree() {
        super("Bachelors Degree", "", 35000);
        HighSchoolDiploma highSchoolDiploma = new HighSchoolDiploma();
        preRequisites.put(highSchoolDiploma.getDegreeTitle(), highSchoolDiploma);
    }
}
