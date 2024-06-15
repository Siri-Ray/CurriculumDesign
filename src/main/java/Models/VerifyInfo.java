package Models;

/**
 * @Author itmei
 * @Date 2024/6/14 23:51
 * @description:
 * @Title: VerifyInfo
 * @Package Models
 */
public class VerifyInfo {
    private int reviewId;
    private String name;
    private String gender;
    private String idNumber;
    private String college;

    @Override
    public String toString() {
        return "VerifyInfo{" +
                "reviewId=" + reviewId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", degreeType='" + degreeType + '\'' +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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
