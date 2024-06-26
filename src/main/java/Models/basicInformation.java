package Models;

/**
 * @Author itmei
 * @Date 2024/6/14 18:39
 * @description:
 * @Title: basicInformation
 * @Package Models
 */
public class basicInformation {
    private int studentId;
    private String name;
    private String gender;
    private String idNumber;
    private String college;
    public basicInformation(){}

    public basicInformation(int studentId, String name, String gender, String idNumber, String college, String major, String degreeType, String supervisor) {
        this.name = name;this.studentId = studentId;this.gender = gender;this.idNumber = idNumber;this.college = college; this.major = major;this.degreeType = degreeType;this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "basicInformation{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", degreeType='" + degreeType + '\'' +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    private String major;
    private String degreeType;
    private String supervisor;
}
