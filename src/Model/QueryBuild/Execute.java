package Model.QueryBuild;

import Model.Model;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by jesperbruun on 16/10/14.
 */
public class Execute extends Model {

    private final String SELECT = "SELECT ";
    private final String FROM = " FROM ";
    private final String WHERE = " WHERE ";
    private final String INSERTINTO = "INSERT INTO ";
    private final String VALUES = " VALUES ";

    private QueryBuilder queryBuilder;
    private Where where;
    private Values values;
    private boolean getAll = false;
    private On on;

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public Where getWhere() {
        return where;
    }
    public Values getValues(){return values;}
    public boolean isGetAll(){return getAll;}
    public On getOn(){return on;}

    public Execute(QueryBuilder queryBuilder, boolean getAll){
        this.queryBuilder = queryBuilder;
        this.getAll = getAll;
    }
    public Execute(QueryBuilder queryBuilder, Where where){
        this.queryBuilder = queryBuilder;
        this.where = where;
    }
    public Execute(QueryBuilder queryBuilder, Values values){
        this.queryBuilder = queryBuilder;
        this.values = values;
    }
    public Execute(QueryBuilder queryBuilder, Where where,  On onobj){
        this.queryBuilder = queryBuilder;
        this.on = onobj;
        this.where = where;
    }

    public CachedRowSetImpl ExecuteQuery() throws SQLException{
        String sql;
        try {
            if(isGetAll()){
                sql = SELECT + getQueryBuilder().getSelectValue() + FROM + getQueryBuilder().getTableName() + ";";
                try {
                    getConnection();
                    sqlStatement = getConn().prepareStatement(sql);
                    CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
                    cachedRowSet.populate(sqlStatement.executeQuery());

                    return cachedRowSet;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    sqlStatement.close();
                    getConn().close();
                }
            }
            else if(getOn() != null){
                sql =
                        SELECT + getQueryBuilder().getSelectValue() +
                                FROM + getQueryBuilder().getTableName() +
                                " INNER JOIN " + getWhere().getJoinTableName() +
                                " ON " + getOn().getLeftTableName() + " " + getOn().getOperator() + " " + getOn().getRightTableName() + " ;";
                //System.out.println(sql);
                try{
                    getConnection();
                    sqlStatement = getConn().prepareStatement(sql);
                    CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
                    cachedRowSet.populate(sqlStatement.executeQuery());

                    return cachedRowSet;
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                finally {
                    sqlStatement.close();
                    getConn().close();
                }
            }
            else {
                sql = SELECT + getQueryBuilder().getSelectValue() +
                        FROM + getQueryBuilder().getTableName() +
                        WHERE + getWhere().getWhereKey() + " " + getWhere().getWhereOperator() + " ?;";

                try {
                    getConnection();
                    sqlStatement = getConn().prepareStatement(sql);
                    sqlStatement.setString(1, getWhere().getWhereValue());
                    CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();

                    cachedRowSet.populate(sqlStatement.executeQuery());

                    return cachedRowSet;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    sqlStatement.close();
                    getConn().close();
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return null;

    }



    public boolean Execute() throws SQLException{
        //setSelectedDatabase("cbscalendar");

        if(getQueryBuilder().isSoftDelete()){
        //    String sql = "UPDATE" + getQueryBuilder().getTableName() + " Set " +
            }
        else{
            String sql = INSERTINTO + getQueryBuilder().getTableName() + " (" + getQueryBuilder().getFields() + ")" + VALUES + "(";

            StringBuilder sb = new StringBuilder();
            for (String n : getValues().getValues()) {
                if (sb.length() > 0) sb.append(',');
                sb.append(" ?");
            }
            sql += sb.toString();
            sql += " );";

            try {
                getConnection();
                sqlStatement = getConn().prepareStatement(sql);
                int x = 0;
                for(int i = 0; i < getValues().getValues().length; i++){
                    x = i;
                    sqlStatement.setString(x+1, getValues().getValues()[i]);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                sqlStatement.close();
                getConn().close();
            }
        }


        return sqlStatement.execute();
    }

    public String toString(){
        return SELECT + getQueryBuilder().getSelectValue() +
                FROM + getQueryBuilder().getTableName() +
                WHERE + getWhere().getWhereKey() + " " + getWhere().getWhereOperator() + " ?";
    }

}
