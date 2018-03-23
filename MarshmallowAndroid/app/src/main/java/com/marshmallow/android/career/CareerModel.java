package com.marshmallow.android.career;

import java.util.Date;

/**
 * This class represents a Career that a User currently has or has done in the past
 *
 * Created by George on 3/23/2018.
 */

public class CareerModel extends CareerAssetModel {

    protected Date dateStarted;
    protected Date dateFinished;
    protected Boolean presentCareer;

    CareerModel() {
        dateStarted = new Date();
        dateFinished = new Date();
        presentCareer = new Boolean(false);
    }

    // Getters and Setters
    public Date getDateStarted() { return dateStarted; }
    public Date getDateFinished() { return dateFinished; }
    public Boolean getPresentCareer() { return  presentCareer; }

    public void setDateStarted(Date dateStarted) { this.dateStarted = dateStarted; }
    public void setDateFinished(Date dateFinished) { this.dateFinished = dateFinished; }
    public void setPresentCareer(Boolean presentCareer) { this.presentCareer = presentCareer; }
}
