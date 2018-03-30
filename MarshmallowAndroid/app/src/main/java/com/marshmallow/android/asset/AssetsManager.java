package com.marshmallow.android.asset;

import java.util.HashMap;

public class AssetsManager {

    public HashMap<Integer, AssetModel> assetMap;

    public static AssetsManager instance = null;

    public static AssetsManager Instance(){
        if(instance == null)
            instance = new AssetsManager();
        return instance;
    }

    public boolean hasAssetModel(Integer key){ return assetMap.containsKey(key); }
    public void addAssetModel(AssetModel model, Integer key)
    {
        if(assetMap.containsKey(key))
            return;
        assetMap.put(key, model);
    }
    public void updateAssetModel(AssetModel model, Integer key)
    {
        if(!assetMap.containsKey(key)){
            System.out.println("Error tried to update a model with key:"+key+" but it is not in the manager");
            return;
        }
        AssetModel modelToUpdate = assetMap.get(key);
        //modelToUpdate.doSomething();
        //ToDo the above
    }
    public boolean removeAssetModel(Integer key)
    {
        return assetMap.remove(key) == null;
    }

    private AssetsManager(){
        assetMap = new HashMap<Integer, AssetModel>();
    }
}
