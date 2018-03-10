package marshmallowlearning.marshmallowandroid;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by George on 3/10/2018.
 */

public class BackEndListenerTask extends AsyncTask <Void, Void, Boolean>{

    private Socket backendSocket;
    private Boolean shouldClose;

    BackEndListenerTask(Socket backendSocket) {
        this.backendSocket = backendSocket;
        shouldClose = false;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean result = true;

        int bytesRead = 0;
        while(!shouldClose) {
            try {
                byte[] inputBuffer = new byte[65535];
                bytesRead = backendSocket.getInputStream().read(inputBuffer);
                if (bytesRead < 0) {
                    shouldClose = true;
                    // TODO: notify user that connection is bad
                    break;
                }

                // the input buffer will only be filled up to the amount of bytes read, everything else is 0
                byte[] dataBytes = new byte[bytesRead];
                System.arraycopy(inputBuffer, 0, dataBytes, 0, bytesRead);

                // TCP can sometimes be rapid and package multiple messages together.
                // So we will read until all of the databytes have been processed or we get an error
                //while(dataBytes.length > 0)
                //{
                try {
                    MarshmallowMessage receivedMessage = MessageFactory.getMessage(dataBytes);
                    MessageManager.Instance().publishMessage(receivedMessage);
                }catch(IOException e) {
                    break;
                }
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
