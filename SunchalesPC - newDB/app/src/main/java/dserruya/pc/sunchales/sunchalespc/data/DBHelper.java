package dserruya.pc.sunchales.sunchalespc.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "SunchalesPC.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Circuit, Integer> circuitDao;
    private Dao<CircuitScore, Integer> circuitScoreDao;
    private Dao<Team, Integer> teamDao;
    private Dao<Tournament, Integer> tournamentDao;
    private Dao<User, Integer> userDao;
    private Dao<Zone, Integer> zoneDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Circuit.class);
            TableUtils.createTable(connectionSource, CircuitScore.class);
            TableUtils.createTable(connectionSource, Team.class);
            TableUtils.createTable(connectionSource, Tournament.class);
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Zone.class);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<Circuit, Integer> getCircuitDao() throws SQLException {
        if (circuitDao == null) {
            circuitDao = getDao(Circuit.class);
        }
        return circuitDao;
    }

    public Dao<CircuitScore, Integer> getCircuitScoreDao() throws SQLException {
        if (circuitScoreDao == null) {
            circuitScoreDao = getDao(CircuitScore.class);
        }
        return circuitScoreDao;
    }

    public Dao<Team, Integer> getTeamDao() throws SQLException {
        if (teamDao == null) {
            teamDao = getDao(Team.class);
        }
        return teamDao;
    }

    public Dao<Tournament, Integer> getTournamentDao() throws SQLException {
        if (tournamentDao == null) {
            tournamentDao = getDao(Tournament.class);
        }
        return tournamentDao;
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<Zone, Integer> getZoneDao() throws SQLException {
        if (zoneDao == null) {
            zoneDao = getDao(Zone.class);
        }
        return zoneDao;
    }

    @Override
    public void close() {
        super.close();
        circuitDao = null;
        circuitScoreDao = null;
        teamDao = null;
        tournamentDao = null;
        userDao = null;
        zoneDao = null;
    }

}
