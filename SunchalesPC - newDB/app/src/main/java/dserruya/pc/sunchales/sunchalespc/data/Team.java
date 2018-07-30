package dserruya.pc.sunchales.sunchalespc.data;

import com.j256.ormlite.field.DatabaseField;

public class Team {

    public static final String ID = "_id";
    public static final String USER1 = "user1";
    public static final String USER2 = "user2";
    public static final String ZONE = "zone";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(foreign = true, columnName = USER1)
    private User user1;
    @DatabaseField(foreign = true, columnName = USER2)
    private User user2;
    @DatabaseField(foreign = true, columnName = ZONE)
    private Zone zone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
