package dserruya.pc.sunchales.sunchalespc.data;

import com.j256.ormlite.field.DatabaseField;

public class Zone {

    public static final String ID = "_id";
    public static final String TOURNAMENT = "tournament";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(foreign = true, columnName = TOURNAMENT)
    private Tournament tournament;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
