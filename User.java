package example.emre.com.emineproject;

public class User {
    String username;
    String age;
    String gender;
    String mail;

    public User(String username, String age, String gender, String mail) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
