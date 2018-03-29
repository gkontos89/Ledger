package com.marshmallow.android.utilities;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.marshmallow.android.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;

/**
 * Singleton class that will allow for us to dynamically look up a Resource for an asset or other parts
 * of the game. We will hash off the class of the object and a unique string for the object itself
 * Created by Caleb on 3/20/2018.
 */

public class ResourceLookupUtility
{
    protected static Application mainApplication = null;

    // Holds a map of uniqueStrings and images,
    protected HashMap<String, ImageView> imageLookupMap;
    // If we cant find it then you git this
    protected ImageView basicBitchImage;

    // Singleton
    protected static ResourceLookupUtility instance = null;

    public static void initialializeApp(Context entryPoint)
    {
        if(mainApplication == null)
            mainApplication = (Application)entryPoint.getApplicationContext();
    }

    public static ResourceLookupUtility Instance()
    {
        if(instance == null)
            instance = new ResourceLookupUtility();
        return instance;
    }

    public ImageView getNoLoveImage()
    {
        return basicBitchImage;
    }

    /**
     * Utility that allows us to loud any resource xml and get its view despite not having the context
     * This is usefull if you are in a class or utility that will abstractly handout views that can be
     * stored or shown
     * @param ResourceId
     * @return
     */
    public View getViewFromXmlLayout(int resourceId) throws Exception
    {
        if( mainApplication == null)
            throw new Exception("main Application context has not been initialized yet");
        Resources globalRes= mainApplication.getResources();
        XmlPullParser parser = (XmlPullParser)globalRes.getLayout(resourceId);
        LayoutInflater inflater = (LayoutInflater) mainApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(parser, null);
    }

    /**
     * Looks for the image in the hashmap of resources. If it cannot find it then the app
     * needs to be reversioned on your phone.
     * @param imageKey unique key for whatever image you are trying to look up
     * @return Image for the resource that was packaged with the app
     * @throws Exception Exception telling why you suck
     */
    public ImageView lookupImage(String imageKey) throws Exception
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

    protected ResourceLookupUtility()
    {
        imageLookupMap = new HashMap<String, ImageView>();
        // TODO load in the images from some assets. probably have them in our resources
        // We will want to use reflective resource look up and then just store in the map off the
        // name of each image. we can make it case insensative for all we care
    }
}
