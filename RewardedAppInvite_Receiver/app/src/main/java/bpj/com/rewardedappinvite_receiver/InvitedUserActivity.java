package bpj.com.rewardedappinvite_receiver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class InvitedUserActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invited_user);

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Open Deep Link
        Intent intent = getIntent();
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            Log.i(TAG, "DATA:" + data);

            // TODO: Parse the data URL and show right content for the URL.
            String userid = data.substring(data.indexOf("userid%3D")+9); //parse the uri query here

            //TODO: Reward the recipient w/ an arbitrary amount of in-app currency, Reward the sender
            rewardInvitedUser(1000); //TODO: Good idea to store the reward amount in Firebase Config so it can easily be changed later
            rewardInviteSender(userid);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void rewardInvitedUser (int amount) {
        Log.i(TAG, "Rewarding invite recipient: " + amount + " gold.");
        // TODO: Check if user has already been rewarded (Use Firebase Realtime DB or proprietary back-end)
        // TODO: If not, reward user and set rewarded flag
        // TODO: If yes, just push back to main activity (optional: display message stating that reward has already been given)
    }

    private void rewardInviteSender (String userid) {
        Log.i(TAG, "Rewarding invite sender: " + userid);
        // TODO: To reward the sender of the userid, pass their userid as part of the deeplink
        // TODO: ... then credit them here (ensure this is done only once by tracking this on the back-end using Firebase Realtime DB or proprietary back-end)
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("InvitedUser Page")
                .setUrl(Uri.parse("http://bpj.io/invite"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
