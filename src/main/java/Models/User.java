package Models;

/**
 * @Author itmei
 * @Date 2024/6/12 20:22
 * @description:
 * @Title: User
 * @Package Models
 */
public class User {
    private int username;
    private String password;
    private String userRole;
    private String college;

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}