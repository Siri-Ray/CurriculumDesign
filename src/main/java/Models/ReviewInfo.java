package Models;

/**
 * @Author itmei
 * @Date 2024/6/15 4:24
 * @description:
 * @Title: ReviewInfo
 * @Package Models
 */
public class ReviewInfo {
    private int reviewId;
    private String status;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        return "ReviewInfo{" +
                "reviewId=" + reviewId +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
