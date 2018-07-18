package dserruya.pc.sunchales.sunchalespc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dserruya.pc.sunchales.sunchalespc.users.UsersActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "extra_user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callTournamentScreen(View view) {
        Intent intent = new Intent(this, Tournament.class);
        startActivity(intent);
    }

    public void callUsersScreen(View view) {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }
}
