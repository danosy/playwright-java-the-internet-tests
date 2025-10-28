package api.model;

public class Post extends BaseEntity{
    private int userId;
    private String body;
    private String title;

    public Post(int id, int userId, String body, String title) {
        super(id);
        this.userId = userId;
        this.body = body;
        this.title = title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }
}
