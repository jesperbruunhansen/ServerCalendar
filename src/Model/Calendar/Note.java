package Model.Calendar;

/**
 * Created by jesperbruun on 15/11/14.
 */
public class Note {

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    private int createdby;
    private String text;
    private String createdon;



}
