package dserruya.sunchalespadelclub.sunchalespadelclub.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dserruya.sunchalespadelclub.sunchalespadelclub.R;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.FirebaseMethods;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseMethods firebaseMethods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started");

        mContext = RegisterActivity.this;
        firebaseMethods = new FirebaseMethods(mContext);

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void init(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if (checkInputs(email, username, password)) {
                   mProgressBar.setVisibility(View.VISIBLE);
                   loadingPleaseWait.setVisibility(View.VISIBLE);

                   firebaseMethods.registerNewEmail(email, password, username);
                }
            }
        });
    }

    private boolean checkInputs (String email, String username, String Password){
        Log.d(TAG, "checkInputs: checking inputs for null values");
        if (email.equals("") || username.equals("") || password.equals("")) {
            Toast.makeText(mContext, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

/*
    Initialize activity widgets
*/

    private void initWidgets(){
        Log.d(TAG, "initWidgets: Initializing Widgets");
        mProgressBar = (ProgressBar) findViewById(R.id.registerProgressBar);
        loadingPleaseWait = (TextView) findViewById(R.id.register_please_wait);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mUsername = (EditText) findViewById(R.id.input_username);
        mContext = RegisterActivity.this;
        mProgressBar.setVisibility(View.GONE);
        loadingPleaseWait.setVisibility(View.GONE);
    }

    private boolean isStringNull(String string) {
        Log.d(TAG, "isStringNull: checking if string is null");

        if (string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /*
    ---------------------------------------------- Firebase ----------------------------------------------
*/

    public void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting Firebase Auth");
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        Log.d(TAG, "updateUI: checking if user is logged in");

        if (user != null) {
            Log.d(TAG, "updateUI: User signed in" + user.getUid());
        } else {
            Log.d(TAG, "updateUI: user signed out");
            /*Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);*/
        }
    }

/*
    ---------------------------------------------- Firebase ----------------------------------------------
*/
}
