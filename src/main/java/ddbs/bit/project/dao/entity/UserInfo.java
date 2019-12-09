package ddbs.bit.project.dao.entity;


public class UserInfo {
    public UserInfo() {
    }
    public UserInfo(String UserID, String userName, String password, String email) {
        this.userID = UserID;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    private String userID;
    private String userName;
    private String password;
    private String email;

    public String getUseID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUseID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
