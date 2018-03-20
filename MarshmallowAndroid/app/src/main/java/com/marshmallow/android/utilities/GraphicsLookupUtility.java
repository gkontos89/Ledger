package com.marshmallow.android.utilities;

import android.media.Image;

import java.util.HashMap;

/**
 * Singleton class that will allow for us to dynamically look up an image for an asset or other parts
 * of the game. We will hash off the class of the object and a unique string for the object itself
 * Created by Caleb on 3/20/2018.
 */

public class GraphicsLookupUtility
{
    // Holds a map of uniqueStrings and images,
    protected HashMap<String, Image> imageLookupMap;
    // If we cant find it then you git this
    protected Image basicBitchImage;


    // Singleton
    protected static GraphicsLookupUtility instance = null;

    public static GraphicsLookupUtility Instance()
    {
        if(instance == null)
            instance = new GraphicsLookupUtility();
        return instance;
    }

    public Image getNoLoveImage()
    {
        return basicBitchImage;
    }

    /**
     * Looks for the image in the hashmap of resources. If it cannot find it then the app
     * needs to be reversioned on your phone.
     * @param imageKey unique key for whatever image you are trying to look up
     * @return Image for the resource that was packaged with the app
     * @throws Exception Exception telling why you suck
     */
    public Image lookupImage(String imageKey) throws Exception
    {
        if( !imageLookupMap.containsKey(imageKey) )
        {
            if( !imageLookupMap.containsKey(imageKey.toLowerCase()))
            {
                throw new Exception("Was not able to find a resource with the imageKey "+imageKey);
            }
            return imageLookupMap.get(imageKey.toLowerCase());
        }
        // This way we can allow for it to be case sensative if we want.
        return imageLookupMap.get(imageKey);
    }

    protected GraphicsLookupUtility()
    {
        imageLookupMap = new HashMap<String, Image>();
        // TODO load in the images from some assets. probably have them in our resources
        // We will want to use reflective resource look up and then just store in the map off the
        // name of each image. we can make it case insensative for all we care
    }
}
