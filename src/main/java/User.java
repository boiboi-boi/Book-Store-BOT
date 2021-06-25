import java.sql.Date;

public class User {
    private long chat_id;
    private Boolean flag;
    public User(){}

    public User(Boolean flag){
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
