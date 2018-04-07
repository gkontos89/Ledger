package com.marshmallow.android.interfaces;

/**
 * Created by Caleb on 3/16/2018.
 */
public interface MarshmallowModel {

    /**
     * Can be used to load the implmententing class from its save state. Save state will probably be protobuff objects
     *
     * @return an object representation that can be given back to the same interface to load at a later point
     */
    public void loadFromData(Object input);

    /**
     * Can be used to save locally on the phone, or save to a protobuff object that will be placed on the server
     *
     * @return an object representation that can be given back to the same interface to load at a later point
     */
    public Object saveState();

    public Object getController();

    /**
     * Can be used for taking in protobuf data and storing it in the model
     */
    void mapProtoDataToModel(Object protoData);

    /**
     * Models must be able to generate a protobuf object based on the data they contain
     *
     * @return protobuf object
     */
    Object generateProtoDataFromModel();
}
