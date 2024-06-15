package Models;

/**
 * @Author itmei
 * @Date 2024/6/15 5:34
 * @description:
 * @Title: SP
 * @Package Models
 */
public class SP {
    private int studentId;
    private String password;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "UP{" +
                "studentId=" + studentId +
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
