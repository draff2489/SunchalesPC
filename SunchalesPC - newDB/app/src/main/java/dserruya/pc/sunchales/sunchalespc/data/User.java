package dserruya.pc.sunchales.sunchalespc.data;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class User {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String BIRTHDATE = "birthdate";
    public static final String PHONE = "phone";
    public static final String MAIL = "mail";
    public static final String GLOBALSCORE = "global_score";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = BIRTHDATE)
    private Date birthdate;
    @DatabaseField(columnName = PHONE)
    private int phone;
    @DatabaseField(columnName = MAIL)
    private String mail;
    @DatabaseField(columnName = GLOBALSCORE)
    private int global_score ;

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

    public Date getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getGlobalScore() {
        return global_score;
    }

    public void setGlobalScore(int global_score) {
        this.global_score = global_score;
    }

}
