package dserruya.pc.sunchales.sunchalespc.data;


import android.provider.BaseColumns;

/**
 * Esquema de la base de datos de usuarios
 */
public class UsersContract {

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String MAIL = "mail";
        public static final String AVATAR_URI = "avatarUri";
        public static final String SCORE = "score";
    }
}
