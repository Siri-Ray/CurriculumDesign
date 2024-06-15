package Models;

/**
 * @Author itmei
 * @Date 2024/6/15 4:46
 * @description:
 * @Title: UP
 * @Package Models
 */
public class UP {
    private int username;
    private String password;

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UP{" +
                "username=" + username +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
