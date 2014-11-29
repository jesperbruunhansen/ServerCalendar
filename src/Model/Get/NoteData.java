package Model.Get;

import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesperbruun on 29/11/14.
 */
public class NoteData {

    public static String getAllNotes(String eventid){
        Gson g = new Gson();
        QueryBuilder queryBuilder = new QueryBuilder();
        CachedRowSetImpl rs;
        List<GetAllNotes> getAllNotesList = new ArrayList<>();

        try {

            rs = queryBuilder.selectFrom("notes").where("eventid","=",eventid).ExecuteQuery();

            while (rs.next()){
                GetAllNotes getAllNotes = new GetAllNotes();
                getAllNotes.setNoteid(rs.getString("noteid"));
                getAllNotes.setText(rs.getString("text"));
                getAllNotes.setCreatedby(rs.getString("createdby"));
                getAllNotes.setCreated(rs.getString("created"));

                getAllNotesList.add(getAllNotes);
            }

            return g.toJson(getAllNotesList);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
class GetAllNotes {
    private String text;
    private String createdby;
    private String created;
    private String noteid;

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }
}