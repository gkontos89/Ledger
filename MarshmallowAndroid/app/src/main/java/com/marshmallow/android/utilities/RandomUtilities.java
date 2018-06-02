package com.marshmallow.android.utilities;

import com.marshmallow.android.asset.AssetModel;
import com.marshmallow.android.user.UserModel;

import java.util.Random;

/**
 * Helpful for testing and anything random we want to do here
 * Created by Caleb on 3/21/2018.
 */

public class RandomUtilities {

    //TODO seed the RNG better
    private static Random randy = new Random();

    private static int getRandomUnsignedInt()
    {
        return randy.nextInt();
    }

    private static int getRandomSignedInt()
    {
        return randy.nextBoolean() == true ? -1 * randy.nextInt() : randy.nextInt();
    }

    /**
     * @return A random string of asci characacters to 5 - 100 characters Long
     */
    private static String getRandomString()
    {
        String answer = "";
        for( int i = 0; i < 5+(getRandomUnsignedInt()%96); i++)
        {
            answer = answer + (char)(97 + (randy.nextInt()%26));
        }
        System.out.println(answer);
        return answer;
    }

    /**
     * Usefull just for quick little tests
     */
    public static AssetModel getRandomMarshmallowAsset()
    {
        AssetModel random = new AssetModel();
        random.setAssetMarketValue(getRandomSignedInt());
        random.setAssetDatePurchased((long)getRandomUnsignedInt());
        random.setAssetName(getRandomString());
        random.setAssetPurchasePrice(getRandomSignedInt());
        random.setAssetRecurringCost("$50.00");

        return random;
    }

    public static UserModel getRandomUserModel()
    {
        UserModel randomModel = new UserModel();
        randomModel.setUserName( getRandomString());
        for( int i = 0; i < 5; i ++ )
            randomModel.addAsset(getRandomMarshmallowAsset());
        return randomModel;
    }
}
