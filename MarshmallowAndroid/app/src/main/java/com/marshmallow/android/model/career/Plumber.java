package com.marshmallow.android.model.career;

import com.marshmallow.android.model.education.Certification;

/**
 * Created by George on 1/9/2019.
 */
public class Plumber extends Career {
    public Plumber() {
        super("Plumber", 57000);
        Certification certification = new Certification();
        preRequisites.put(certification.getDegreeTitle(), certification);
    }
}
