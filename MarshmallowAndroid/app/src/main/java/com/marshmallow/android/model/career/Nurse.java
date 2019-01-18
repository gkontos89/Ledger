package com.marshmallow.android.model.career;

import com.marshmallow.android.model.education.BachelorsDegree;

/**
 * Created by George on 1/9/2019.
 */
public class Nurse extends Career {
    public Nurse() {
        super("Nurse", 35000);
        BachelorsDegree bachelorsDegree = new BachelorsDegree();
        preRequisites.put(bachelorsDegree.getDegreeTitle(), bachelorsDegree);
    }
}
