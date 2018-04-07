package com.marshmallow.android.asset;

import com.marshmallow.android.interfaces.ModelManager;

import java.util.HashMap;

/**
 * This class is the model manager for assets
 */

public class AssetsModelManager implements ModelManager{

    public HashMap<Integer, AssetModel> assetMap;
    public static AssetsModelManager instance = null;

    private AssetsModelManager(){
        assetMap = new HashMap<Integer, AssetModel>();
    }

    public static AssetsModelManager Instance(){
        if(instance == null)
            instance = new AssetsModelManager();
        return instance;
    }

    public boolean hasModel(Integer uniqueId){ return assetMap.containsKey(uniqueId); }
    public void addModel(Object model, Integer uniqueId)
    {
        AssetModel assetModel = (AssetModel) model;
        if(assetMap.containsKey(uniqueId)) {
            updateModel(assetModel, assetModel.getUniqueId());
            return;
        }

        AssetController assetController = new AssetController();
        assetController.setModel(assetModel);
        assetModel.setAssetController(assetController);
        assetMap.put(uniqueId, assetModel);

        /** TODO each manager will have to establish a relationship with their respective
         * GUI/Activity for updates
         *
         * Activities will have handles to the manager, so they can request data on creation and
         * send data based on user input.  That is easy.
         *
         * When managers receive new data the GUI needs to update. For things
         * like UserSummary it won't be bad because the XML the Activity loads will be identical
         * to the entire UserModel/Controller/View that it interacts with.
         * But Assets for example have a model/controller/view for each individual asset. The manager
         * can hold many of these.  The XML that the Activity will load will be completely different,
         * a scrollable list perhaps.  So this Manager will have to add new items or update existing
         * items in that list.
         */
    }

    public void updateModel(Object model, Integer uniqueId)
    {
        if(!hasModel(uniqueId)){
            System.out.println("Error tried to update a model with key:"+uniqueId+" but it is not in the manager");
            return;
        }

        // First find the model needing updating, get its controller, and point controller to new model
        AssetModel assetModel = (AssetModel) model;
        AssetModel modelToUpdate = assetMap.get(uniqueId);
        AssetController assetController = modelToUpdate.getController();
        assetController.setModel(assetModel);
        modelToUpdate.setAssetController(assetController);

        assetMap.put(uniqueId, assetModel);
    }

    public Object getModel(Integer uniqueId) { return assetMap.get(uniqueId); }

    public boolean removeModel(Integer uniqueId)
    {
        return assetMap.remove(uniqueId) == null;
    }
}
