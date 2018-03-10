package marshmallowlearning.marshmallowandroid;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by George on 3/10/2018.
 */

public class BackEndListenerTask extends AsyncTask <Void, Void, Boolean>{

    private Socket backendSocket;

    BackEndListenerTask(Socket backendSocket) {
        this.backendSocket = backendSocket;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean result = true;

        int bytesRead = 0;
        while(true) {
            try {
                
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(final Boolean success) {

    }
}
