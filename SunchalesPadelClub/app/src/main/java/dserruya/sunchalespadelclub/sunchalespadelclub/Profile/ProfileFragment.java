package dserruya.sunchalespadelclub.sunchalespadelclub.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dserruya.sunchalespadelclub.sunchalespadelclub.DataBase.Tournaments;
import dserruya.sunchalespadelclub.sunchalespadelclub.R;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.BottomNavigationViewHelper;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.FirebaseMethods;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.TournamentAdapter;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.UniversalImageLoader;
import dserruya.sunchalespadelclub.sunchalespadelclub.models.UserAccountSettings;
import dserruya.sunchalespadelclub.sunchalespadelclub.models.UserSettings;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private static final int ACTIVITY_NUM = 3;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myref;
    private FirebaseMethods mFirebaseMethods;

    private Context mContext;

    private TextView mScore, mDisplayName, mUsername, mDescription;
    private ProgressBar mProgressBar;
    private CircleImageView mProfilePhoto;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView profileMenu;
    private BottomNavigationViewEx bottomNavigationViewEX;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mDisplayName = (TextView) view.findViewById(R.id.displayname);
        mUsername = (TextView) view.findViewById(R.id.username);
        mDescription = (TextView) view.findViewById(R.id.description);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
        mScore = (TextView) view.findViewById(R.id.tvScore);
        mProgressBar = (ProgressBar) view.findViewById(R.id.profileProgressBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_tournaments);
        toolbar = (Toolbar) view.findViewById(R.id.profileToolbar);
        profileMenu = (ImageView) view.findViewById(R.id.profileMenu);
        bottomNavigationViewEX = (BottomNavigationViewEx) view.findViewById(R.id.bottomNavViewBar);
        mContext = getActivity();
        mFirebaseMethods = new FirebaseMethods(getActivity());
        RecyclerView rvTournaments = (RecyclerView) view.findViewById(R.id.rv_tournaments);
        Log.d(TAG, "onCreateView: started");

        setupBottomNavigationView();
        setupToolbar();
        setupRecyclerView(rvTournaments);

        setupFirebaseAuth();

        TextView editProfile = (TextView) view.findViewById(R.id.textEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: nagigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        return view;
    }

    private void setProfileWidgets(UserSettings userSettings) {
        Log.d(TAG, "setProfileWidgets: setting widgets with data retrieved from firebase: " + userSettings.toString());

        UserAccountSettings settings = userSettings.getSettings();

        UniversalImageLoader.setImage(settings.getProfile_photo(), mProfilePhoto, null, "");

        mDisplayName.setText(settings.getDisplay_name());
        mUsername.setText(settings.getUsername());
        mDescription.setText(settings.getDescription());
        mScore.setText(String.valueOf(settings.getScore()));
        mProgressBar.setVisibility(View.GONE);

    }

    private void setupToolbar () {

        ((ProfileActivity)getActivity()).setSupportActionBar(toolbar);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to account settings. ");
                Intent intent = new Intent (mContext, AccountSettingsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupNavigationView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEX);
        BottomNavigationViewHelper.enableNavigation(mContext,getActivity(), bottomNavigationViewEX);
        Menu menu = bottomNavigationViewEX.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up febase auth.");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myref = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //retrieve user information from database
                setProfileWidgets(mFirebaseMethods.getUserSettings(dataSnapshot));

                //retrieve tournaments for the user in question
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

     /*  Datos de prueba*/
    private ArrayList<Tournaments> dataSet() {
        ArrayList<Tournaments> data = new ArrayList<>();
        data.add(new Tournaments("NOMBRE TORNEO", "2", R.drawable.ic_android));
        data.add(new Tournaments("NOMBRE TORNEO", "2", R.drawable.ic_android));
        data.add(new Tournaments("NOMBRE TORNEO", "5", R.drawable.ic_android));
        data.add(new Tournaments("NOMBRE TORNEO", "1", R.drawable.ic_android));
        data.add(new Tournaments("NOMBRE TORNEO", "6", R.drawable.ic_android));
        data.add(new Tournaments("NOMBRE TORNEO", "2", R.drawable.ic_android));
        return data;
    }

    private void setupRecyclerView(RecyclerView rvTournaments){
        LinearLayoutManager glm = new LinearLayoutManager(mContext);
        rvTournaments.setLayoutManager(glm);
        TournamentAdapter adapter = new TournamentAdapter(dataSet());
        rvTournaments.setAdapter(adapter);
    }
}
