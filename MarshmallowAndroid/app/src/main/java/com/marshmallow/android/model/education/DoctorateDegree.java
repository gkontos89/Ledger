package com.marshmallow.android.model.education;

/**
 * Created by George on 1/9/2019.
 */
public class DoctorateDegree extends Education {
    public DoctorateDegree() {
        super("Doctorate Degree", "", 0);
        MastersDegree mastersDegree = new MastersDegree();
        preRequisites.put(mastersDegree.getDegreeTitle(), mastersDegree);
    }
}
