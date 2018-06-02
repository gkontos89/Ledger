package com.marshmallow.android.career;

import com.marshmallow.android.asset.AssetModel;

import java.util.Date;
import java.util.Vector;

/**
 * This class represents Careers that can be pursued in the market place, or a career that
 * a user currently has or had in the past
 *
 * Created by George on 3/23/2018.
 */

public class CareerModel extends AssetModel {

    // These are certifications needed to pursue this career such as degrees, licenses, ect.
    protected Vector<String> requiredCertifications;
    protected Integer salary;
    protected Integer hoursPerWeek;
    protected Date dateStarted;
    protected Date dateFinished;
    protected Boolean presentCareer;

    CareerModel() {
        requiredCertifications = new Vector<>();
        salary = new Integer(0);
        hoursPerWeek = new Integer(0);
        dateStarted = new Date();
        dateFinished = new Date();
        presentCareer = new Boolean(false);
    }

    // Getters and Setters
    public Vector<String> getRequiredCertifications() { return requiredCertifications; }
    public Integer getSalary() { return salary; }
    public Integer getHoursPerWeek() { return hoursPerWeek; }
    public Date getDateStarted() { return dateStarted; }
    public Date getDateFinished() { return dateFinished; }
    public Boolean getPresentCareer() { return  presentCareer; }

    public void addRequiredCertification(String requiredCertification) {
        requiredCertifications.add(requiredCertification);
    }
    public void setSalary(Integer salary) { this.salary = salary; }
    public void setHoursPerWeek(Integer hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }
    public void setDateStarted(Date dateStarted) { this.dateStarted = dateStarted; }
    public void setDateFinished(Date dateFinished) { this.dateFinished = dateFinished; }
    public void setPresentCareer(Boolean presentCareer) { this.presentCareer = presentCareer; }

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
