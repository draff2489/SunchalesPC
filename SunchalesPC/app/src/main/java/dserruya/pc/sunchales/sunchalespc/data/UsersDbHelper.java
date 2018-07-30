package dserruya.pc.sunchales.sunchalespc.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsersDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";
    public static final String TAG = "DBHelper";

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UsersContract.UserEntry.TABLE_USERS + " ("
                + UsersContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsersContract.UserEntry.ID + " TEXT NOT NULL,"
                + UsersContract.UserEntry.NAME + " TEXT NOT NULL,"
                + UsersContract.UserEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                + UsersContract.UserEntry.MAIL + " TEXT NOT NULL,"
                + UsersContract.UserEntry.AVATAR_URI + " TEXT,"
                + UsersContract.UserEntry.SCORE + " INTEGER,"
                + "UNIQUE (" + UsersContract.UserEntry.ID + "))");


        // Insertar datos ficticios para prueba inicial
        //mockData(db);

    }

    /*private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockUser(sqLiteDatabase, new User("Carlos Perez", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "carlos_perez.jpg"));
        mockUser(sqLiteDatabase, new User("Daniel Samper", "Abogado accidentes de tráfico",
                "300 200 2222", "Gran profesional con experiencia de 5 años en accidentes de tráfico.",
                "daniel_samper.jpg"));
        mockUser(sqLiteDatabase, new User("Lucia Aristizabal", "Abogado de derechos laborales",
                "300 200 3333", "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "lucia_aristizabal.jpg"));
        mockUser(sqLiteDatabase, new User("Marina Acosta", "Abogado de familia",
                "300 200 4444", "Gran profesional con experiencia de 5 años en casos de familia.",
                "marina_acosta.jpg"));
        mockUser(sqLiteDatabase, new User("Olga Ortiz", "Abogado de administración pública",
                "300 200 5555", "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "olga_ortiz.jpg"));
        mockUser(sqLiteDatabase, new User("Pamela Briger", "Abogado fiscalista",
                "300 200 6666", "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "pamela_briger.jpg"));
        mockUser(sqLiteDatabase, new User("Rodrigo Benavidez", "Abogado Mercantilista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en redacción de contratos mercantiles",
                "rodrigo_benavidez.jpg"));
        mockUser(sqLiteDatabase, new User("Tom Bonz", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "tom_bonz.jpg"));
    }*/

    /*public long mockUser(SQLiteDatabase db, User user) {
        return db.insert(
                UserEntry.TABLE_NAME,
                null,
                user.toContentValues());
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);
    //clear all data
        db.execSQL("DROP TABLE IF EXISTS " + UsersContract.UserEntry.TABLE_USERS);

        //recreate tables
        onCreate(db);
    }

    public long saveUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UsersContract.UserEntry.TABLE_USERS,
                null,
                user.toContentValues());

    }

    public Cursor getAllUsers() {
        return getReadableDatabase()
                .query(
                        UsersContract.UserEntry.TABLE_USERS,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getUserById(String userId) {
        Cursor c = getReadableDatabase().query(
                UsersContract.UserEntry.TABLE_USERS,
                null,
                UsersContract.UserEntry.ID + " LIKE ?",
                new String[]{userId},
                null,
                null,
                null);
        return c;
    }

    public int deleteUser(String userId) {
        return getWritableDatabase().delete(
                UsersContract.UserEntry.TABLE_USERS,
                UsersContract.UserEntry.ID + " LIKE ?",
                new String[]{userId});
    }

    public int updateUser(User user, String userId) {
        return getWritableDatabase().update(
                UsersContract.UserEntry.TABLE_USERS,
                user.toContentValues(),
                UsersContract.UserEntry.ID + " LIKE ?",
                new String[]{userId}
        );
    }
}
