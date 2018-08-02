package dserruya.sunchalespadelclub.sunchalespadelclub.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import dserruya.sunchalespadelclub.sunchalespadelclub.DataBase.Tournaments;
import dserruya.sunchalespadelclub.sunchalespadelclub.R;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.BottomNavigationViewHelper;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.TournamentAdapter;
import dserruya.sunchalespadelclub.sunchalespadelclub.Utils.UniversalImageLoader;

public class ProfileActivity extends AppCompatActivity{

    private static final String TAG = "ProfileActivity";

    private static final int ACTIVITY_NUM = 3;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;

    private ImageView profilePhoto;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started");

        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        RecyclerView rvTournaments = (RecyclerView) findViewById(R.id.rv_tournaments);
        LinearLayoutManager glm = new LinearLayoutManager(mContext);
        rvTournaments.setLayoutManager(glm);
        TournamentAdapter adapter = new TournamentAdapter(dataSet());
        rvTournaments.setAdapter(adapter);
    }


        private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile photo");
        String imageURL = "static1.squarespace.com/static/54f3390ee4b095d9141b86a5/t/55d748b2e4b086d36aa80de2/1475761660263/Phishing+Tests";
        UniversalImageLoader.setImage(imageURL, profilePhoto, mProgressBar, "https://");
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

    private void setupActivityWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    private void setupToolbar () {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to account settings. ");
                Intent intent = new Intent (mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
