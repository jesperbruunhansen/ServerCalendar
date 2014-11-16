package Model.QueryBuild;


/**
 * Created by jesperbruun on 15/11/14.
 */
public class On {

    private QueryBuilder queryBuilder;
    private Where where;

    private String leftTableName;
    private String operator;
    private String rightTableName;

    public String getLeftTableName() {
        return leftTableName;
    }

    public String getOperator() {
        return operator;
    }

    public String getRightTableName() {
        return rightTableName;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }
    public Where getWhere(){
        return where;
    }

    private void setLeftTableName(String name){
        this.leftTableName = name;
    }
    private void setOperator(String operator){
        this.operator = operator;
    }
    private void setRightTableName(String name){
        this.rightTableName = name;
    }

    public On(QueryBuilder qb, Where where){
        this.queryBuilder = qb;
        this.where = where;
    }
    private On(){}


    public Execute on(String leftTableName, String operator, String rightTableName){
        On onObj = new On();
        onObj.setLeftTableName(leftTableName);
        onObj.setOperator(operator);
        onObj.setRightTableName(rightTableName);
        return new Execute(getQueryBuilder(), getWhere(), onObj);

    }


}
