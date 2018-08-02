package dserruya.sunchalespadelclub.sunchalespadelclub.models;

public class User {

    private String user_id;
    private long phone_number;
    private String email;
    private String username;

    public User(String user_id, long phone_number, String email, String username) {
        this.email = email;
        this.phone_number = phone_number;
        this.user_id = user_id;
        this.username = username;
    }

    public User (){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
