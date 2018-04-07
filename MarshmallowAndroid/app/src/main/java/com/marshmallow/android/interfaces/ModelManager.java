package com.marshmallow.android.interfaces;

/**
 * This interface defines ModelManagers which hold models of certain types and manages the connections
 * between models, controllers and views.
 *
 * ModelManagers also route data and message between activities and ModelListeners
 *
 * Created by George on 4/7/2018.
 */

public interface ModelManager {

    /**
     * Checks to see if an asset is in the manager
     * @param uniqueId
     * @return true if model existed
     */
    boolean hasModel(Integer uniqueId);

    /**
     * add a model to the manager
     * @param model
     * @param uniqueId
     */
    void addModel(Object model, Integer uniqueId);

    /**
     * update an existing model with new model data
     * @param model
     * @param uniqueId
     */
    void updateModel(Object model, Integer uniqueId);

    /**
     * Method for retrieving a model from the manager
     * @param uniqueId
     * @return model
     */
    Object getModel(Integer uniqueId);

    /**
     * remove a model from this manager
     * @param uniqueId
     * @return whether removal was valid
     */
    boolean removeModel(Integer uniqueId);
}
