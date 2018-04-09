package com.marshmallow.android.asset;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

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

    public HashMap<Integer, AssetModel> getAssetMap() { return assetMap; }

    public boolean hasModel(Integer uniqueId){ return assetMap.containsKey(uniqueId); }
    public void addModel(Object model, Integer uniqueId)
    {
        AssetModel assetModel = (AssetModel) model;
        if(assetMap.containsKey(uniqueId)) {
            updateModel(assetModel, assetModel.getUniqueId());
            return;
        }

        assetMap.put(uniqueId, assetModel);
    }

    public void updateModel(Object model, Integer uniqueId)
    {
        if(!hasModel(uniqueId)){
            System.out.println("Error tried to update a model with key:"+uniqueId+" but it is not in the manager");
            return;
        }

        AssetModel assetModel = (AssetModel) model;
        assetMap.put(uniqueId, assetModel);
    }

    public Object getModel(Integer uniqueId) { return assetMap.get(uniqueId); }

    public boolean removeModel(Integer uniqueId)
    {
        return assetMap.remove(uniqueId) == null;
    }

}
