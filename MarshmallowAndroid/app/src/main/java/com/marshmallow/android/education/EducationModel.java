package com.marshmallow.android.education;

import com.marshmallow.android.asset.AssetModel;

import java.util.Date;
import java.util.Vector;

/**
 * This class represents the model for Education.
 * This model can be used to represent a level of Education available for acquiring in the
 * marketplace, or a level of education owned by a User
 *
 * Created by George on 3/23/2018.
 */

public class EducationModel extends AssetModel {

    protected Integer educationPurchasedPrice;
    protected Date educationDatePurchased;
    /** Required certifications can be anything a user has to do prior to 'purchasing' this level of
     * education.  For example, if a model was for a Master's, the required cert would be a Bachelor's.
     */
    protected Vector<String> requiredCertifications;

    EducationModel() {
        educationPurchasedPrice = new Integer(0);
        educationDatePurchased = new Date();
        requiredCertifications = new Vector<String>();
    }

    // Getters and Setters
    public Integer getEducationPurchasePrice() { return educationPurchasedPrice; }
    public Date getEducationDatePurchased() { return educationDatePurchased; }
    public void setEducationPurchasePrice(Integer educationPurchasePrice) { this.educationPurchasedPrice = educationPurchasePrice; }
    public void setEducationDatePurchased(Date educationDatePurchased) { this.educationDatePurchased = educationDatePurchased; }
    public Vector<String> getRequiredCertifications() { return requiredCertifications; }
    public void addRequiredCertification(String requiredCertification) {
        requiredCertifications.add(requiredCertification);
    }

    /**
     * Wrapping the getters and setters from the base class for API clarity
     */
    public String getEducationName() {
        return super.getAssetName();
    }

    public Integer getEducationMarketValue() {
        return super.getAssetMarketValue();
    }

    public void setEducationName(String educationName) {
        super.setAssetName(educationName);
    }

    public void setEducationMarketValue(Integer educationMarketValue) {
        super.setAssetMarketValue(educationMarketValue);
    }
}
