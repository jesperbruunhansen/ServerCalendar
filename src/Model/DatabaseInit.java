package Model;

import Config.Config;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jesperbruun on 09/11/14.
 */
public class DatabaseInit extends Model {

    public static void init() {

        try {
            if (!doesDatabaseExist())
            {
                readfromSqlFile(Config.getResPath() + "createDBscript.sql");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //Note fra helena

    }


}
