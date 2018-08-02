package dserruya.sunchalespadelclub.sunchalespadelclub.models;

public class UserAccountSettings {

    private String description;
    private String display_name;
    private long score;
    private String profile_photo;
    private String username;

    public UserAccountSettings(String description, String display_name, String profile_photo, long score, String username) {
        this.description = description;
        this.display_name = display_name;
        this.profile_photo = profile_photo;
        this.score = score;
        this.username = username;
    }

    public UserAccountSettings(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "description='" + description + '\'' +
                ", display_name='" + display_name + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", score=" + score +
                ", username='" + username + '\'' +
                '}';
    }
}
