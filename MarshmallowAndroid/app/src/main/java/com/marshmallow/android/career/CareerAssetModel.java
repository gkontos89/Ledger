package com.marshmallow.android.career;

import com.marshmallow.android.asset.AssetModel;

import java.util.Vector;

/**
 * This class represents the data model for a pursuable Career in the marketplace
 *
 * Created by George on 3/23/2018.
 */

public class CareerAssetModel extends AssetModel {

    // These are certifications needed to pursue this career
    protected Vector<String> requiredCertifications;
    protected Integer salary;
    protected Integer hoursPerWeek;

    CareerAssetModel() {
        requiredCertifications = new Vector<>();
        salary = new Integer(0);
        hoursPerWeek = new Integer(0);
    }

    // Getters and Setters
    public Vector<String> getRequiredCertifications() { return requiredCertifications; }
    public Integer getSalary() { return salary; }
    public Integer getHoursPerWeek() { return hoursPerWeek; }

    public void addRequiredCertification(String requiredCertification) {
        requiredCertifications.add(requiredCertification);
    }
    public void setSalary(Integer salary) { this.salary = salary; }
    public void setHoursPerWeek(Integer hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }

    /**
     * Wrapping the getters and setters from the base class for API clarity
     */
    public String getCareerName() {
        return super.getAssetName();
    }

    public void setCareerName(String careerName) {
        super.setAssetName(careerName);
    }
}
