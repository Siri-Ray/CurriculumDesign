package Models;

/**
 * @Author itmei
 * @Date 2024/6/15 7:39
 * @description:
 * @Title: KT
 * @Package Models
 */
public class KT {
    private String keyword;
    private String type;

    @Override
    public String toString() {
        return "KT{" +
                "keyword='" + keyword + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
