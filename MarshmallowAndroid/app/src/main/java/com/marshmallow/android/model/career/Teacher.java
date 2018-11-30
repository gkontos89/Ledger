package com.marshmallow.android.model.career;

import com.marshmallow.android.model.education.BachelorsDegree;

/**
 * Created by George on 11/29/2018.
 */
public class Teacher extends Career {
    public Teacher() {
        super("Teacher", 40000);
        BachelorsDegree bachelorsDegree = new BachelorsDegree();
        preRequisites.put(bachelorsDegree.getDegreeTitle(), bachelorsDegree);
    }
}
