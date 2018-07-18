package dserruya.pc.sunchales.sunchalespc.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String phoneNumber;
    private String mail;
    private String avatarUri;
    private String score;

    public User(String name,
                String phoneNumber, String mail, String score,
                String avatarUri) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.avatarUri = avatarUri;
        this.score = "0";
    }

    public User(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(UsersContract.UserEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(UsersContract.UserEntry.NAME));
        phoneNumber = cursor.getString(cursor.getColumnIndex(UsersContract.UserEntry.PHONE_NUMBER));
        mail = cursor.getString(cursor.getColumnIndex(UsersContract.UserEntry.MAIL));
        avatarUri = cursor.getString(cursor.getColumnIndex(UsersContract.UserEntry.AVATAR_URI));
        score = cursor.getString(cursor.getColumnIndex(UsersContract.UserEntry.SCORE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(UsersContract.UserEntry.ID, id);
        values.put(UsersContract.UserEntry.NAME, name);
        values.put(UsersContract.UserEntry.PHONE_NUMBER, phoneNumber);
        values.put(UsersContract.UserEntry.MAIL, mail);
        values.put(UsersContract.UserEntry.AVATAR_URI, avatarUri);
        values.put(UsersContract.UserEntry.SCORE, score);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public String getScore() {
        return score;
    }

}
