package dserruya.pc.sunchales.sunchalespc.users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import dserruya.pc.sunchales.sunchalespc.R;

public class UsersActivity  extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "extra_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UsersFragment fragment = (UsersFragment)
                getSupportFragmentManager().findFragmentById(R.id.users_container);

        if (fragment == null) {
            fragment = UsersFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.users_container, fragment)
                    .commit();
        }
    }
}