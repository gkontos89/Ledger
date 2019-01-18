package com.marshmallow.android.model.education;

/**
 * Created by George on 1/9/2019.
 */
public class MastersDegree extends Education {
    public MastersDegree() {
        super("Masters Degree", null, 40000);
        BachelorsDegree bachelorsDegree = new BachelorsDegree();
        preRequisites.put(bachelorsDegree.getDegreeTitle(), bachelorsDegree);
    }
}
