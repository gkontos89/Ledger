package com.marshmallow.android.model.education;

/**
 * Created by George on 1/9/2019.
 */
public class Certification extends Education {
    public Certification() {
        super("Certification", "", 3000);
        HighSchoolDiploma highSchoolDiploma = new HighSchoolDiploma();
        preRequisites.put(highSchoolDiploma.getDegreeTitle(), highSchoolDiploma);
    }
}
