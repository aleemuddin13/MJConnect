package in.ac.mjcet.mjconnect.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import in.ac.mjcet.mjconnect.Constants.StringConstants;
import in.ac.mjcet.mjconnect.Models.User;
import in.ac.mjcet.mjconnect.R;
import in.ac.mjcet.mjconnect.Utils.SharedPreferencesManager;
import in.ac.mjcet.mjconnect.Utils.VolleyManager;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private ProfileTracker profileTracker;
    private DatabaseReference mFirebaseDatabaseReference;
    private User user;
    private Query query;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initializing Facebook Sdk.
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        Profile profile = Profile.getCurrentProfile();
        Log.d(TAG, "upat k "+profile);
        profileChanged(profile);

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d(TAG, "null bro");
                profileChanged(currentProfile);
            }
        };
        profileTracker.startTracking();
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, error.toString());
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            boolean isFound = false;
            Log.d(TAG, "here");
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                isFound = true;
                break;
            }

            if(!isFound){
                query.removeEventListener(valueEventListener);
                mFirebaseDatabaseReference.child(StringConstants.USERS).child(user.getId()).setValue(user);
            }

            SharedPreferences sharedPreferences = SharedPreferencesManager.getInstance(getApplicationContext());
            sharedPreferences.edit().putString(StringConstants.ID,user.getId()).apply();
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }

        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };


    public void profileChanged(Profile profile){
        if(profile == null){
            return;
        }
        Log.d(TAG,"got profile");
        String name = profile.getName();
        Uri dpUrl = profile.getProfilePictureUri(500,500);
        String id = profile.getId();

        user = new User(id, name, dpUrl.toString());

        query = mFirebaseDatabaseReference.child(StringConstants.USERS).orderByChild(StringConstants.ID).equalTo(id);
        query.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        callbackManager.onActivityResult(arg0, arg1, arg2);
    }
}
