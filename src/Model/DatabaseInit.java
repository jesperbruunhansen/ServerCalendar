package Model;

import Config.Config;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jesperbruun on 09/11/14.
 */
public class DatabaseInit extends Model {


    /**
     * Initialize database environment
     */
    public static void init() {

        try {
            if (!doesDatabaseExist())
            {
                System.out.println("Database does not exist");
                readfromSqlFile(Config.getResPath() + "createDBscript.sql");
            }
            getConnection(false);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


}
