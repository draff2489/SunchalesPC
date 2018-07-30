package dserruya.pc.sunchales.sunchalespc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dserruya.pc.sunchales.sunchalespc.tournaments.TournamentsActivity;
import dserruya.pc.sunchales.sunchalespc.users.UsersActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callTournamentScreen(View view) {
        Intent intent = new Intent(this, TournamentsActivity.class);
        startActivity(intent);
    }

    public void callUsersScreen(View view) {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }
}
