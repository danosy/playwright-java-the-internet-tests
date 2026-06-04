package api.model;

import api.model.enums.Gender;

public class LoginResponse extends BaseEntity {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String image;

    public LoginResponse() {
    }

    public LoginResponse(int id, String accessToken, String refreshToken, String username, String firstName, String lastName, String email, Gender gender, String image) {
        super(id);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.image = image;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + this.getId() +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", username='" + username + '\'' +
                ", lastname='" + lastName + '\'' +
                ", gender=" + gender + '\'' +
                ", email = " + email + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}