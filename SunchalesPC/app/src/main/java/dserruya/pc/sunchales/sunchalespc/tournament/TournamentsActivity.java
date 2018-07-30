package dserruya.pc.sunchales.sunchalespc.tournament;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import dserruya.pc.sunchales.sunchalespc.R;
import dserruya.pc.sunchales.sunchalespc.users.UsersFragment;

public class TournamentsActivity extends AppCompatActivity {

    public static final String EXTRA_TOURNAMENT_ID = "extra_tournament_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournaments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UsersFragment fragment = (UsersFragment)
                getSupportFragmentManager().findFragmentById(R.id.tournament_container);

        if (fragment == null) {
            fragment = UsersFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tournament_container, fragment)
                    .commit();
        }
    }
}