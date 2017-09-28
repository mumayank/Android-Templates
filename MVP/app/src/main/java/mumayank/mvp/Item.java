package mumayank.mvp;

/**
 * Created by Mayank on 28-09-2017.
 */

public class Item {

    private String sender;
    private String date;
    private String body;

    public Item(String sender, String date, String body) {
        this.sender = sender;
        this.date = date;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
