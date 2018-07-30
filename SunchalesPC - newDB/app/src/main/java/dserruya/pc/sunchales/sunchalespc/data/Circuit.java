package dserruya.pc.sunchales.sunchalespc.data;

import com.j256.ormlite.field.DatabaseField;

public class Circuit {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String ROUNDS = "rounds";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = ROUNDS)
    private int rounds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds= rounds;
    }
}
