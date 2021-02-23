package entity;

public class ClientDetails  implements SuperEntity{
private String fullname;
private String username;
private String password;
private String email;
private String phonenumber;
private String gender;
private String profile;

    public ClientDetails() {
    }

    public ClientDetails(String fullname, String username, String password, String email, String phonenumber, String gender, String profile) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.profile = profile;
    }
    public ClientDetails(String fullname, String email, String phonenumber, String gender, String profile) {
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.profile = profile;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
