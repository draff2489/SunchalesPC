package dserruya.pc.sunchales.sunchalespc.data;

import com.j256.ormlite.field.DatabaseField;

public class Tournament {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String CIRCUIT = "circuit";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(foreign = true, columnName = CIRCUIT)
    private Circuit circuit;

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

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
