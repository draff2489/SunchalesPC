package dserruya.pc.sunchales.sunchalespc.data;

import com.j256.ormlite.field.DatabaseField;

public class CircuitScore {

    public static final String ID = "_id";
    public static final String SCORE = "score";
    public static final String USER = "user";
    public static final String CIRCUIT = "circuit";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = SCORE)
    private int score ;
    @DatabaseField(foreign = true, columnName = USER)
    private User user;
    @DatabaseField(foreign = true, columnName = CIRCUIT)
    private Circuit circuit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getuser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
