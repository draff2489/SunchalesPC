package dserruya.sunchalespadelclub.sunchalespadelclub.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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

        init();

        /*setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();
        setupRecyclerView();*/
    }

    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

    /*private void setupRecyclerView(){
        RecyclerView rvTournaments = (RecyclerView) findViewById(R.id.rv_tournaments);
        LinearLayoutManager glm = new LinearLayoutManager(mContext);
        rvTournaments.setLayoutManager(glm);
        TournamentAdapter adapter = new TournamentAdapter(dataSet());
        rvTournaments.setAdapter(adapter);
    }

    *//*  Datos de prueba*//*
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

*/
}
