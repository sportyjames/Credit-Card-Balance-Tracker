//Jiayu Wu
//10/24/18
//This class accesses mySQLdatabase
package dbclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author wujiayu
 */
public class MySQLdbAccess {
    private String dbName;
    private Object[][] data;
    private Connection dbConn;
    
    public MySQLdbAccess()
    {
        this.dbName="";
        this.data = null;
        this.dbConn = null;
    }
    public MySQLdbAccess(String dbName)
    {
        this.dbName = dbName;
        this.data = null;
        setDbConn();
    }

    public String getDbName() {
        return dbName;
    }
    
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Object[][] getData(String tableName,String[] tableHeaders) {
        int columnCount = tableHeaders.length;
        ResultSet rs = null;
        Statement s = null;
        String dbQuery = "SELECT * FROM " + tableName;
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        
        try{
//            System.out.println("1");
            s = this.dbConn.createStatement();
//            System.out.println("2");
            rs = s.executeQuery(dbQuery);
//            System.out.println("3");
            while(rs.next()) {
            ArrayList<String> row = new ArrayList <>();
                for(int i=0; i<columnCount; i++)
                {
                    row.add(rs.getString(tableHeaders[i]));
//                    System.out.println("4");
                    
                }
                dataList.add(row);
            }
            this.data = new Object[dataList.size()][columnCount];
            for(int i=0;i<dataList.size();i++)
            {
                ArrayList<String> row;
                
                row = dataList.get(i);
                
                for(int j=0; j<columnCount;j++)
                {
                    this.data[i][j] = row.get(j);
                }
            }
        }
            catch(SQLException err)
                    {
                    System.out.println("unable to get the data from database!");
                    System.exit(0);
                    }
            return this.data;
    }
    
    public void setData(Object[][] data) {
        this.data = data;
    }

    public Connection getDbConn() {
        return dbConn;
    }

    public void setDbConn() {
        String connectionURL = "jdbc:mysql://localhost:3306/"+ this.dbName;
        this.dbConn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.dbConn = DriverManager.getConnection(connectionURL,"root","mysql1");
        }
        catch(SQLException err)
        {
            System.out.println("SQL Connection error.");
            System.exit(0);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Class For Name not found");
            System.exit(0);
        }
    }
    
    public void closeDbConn(){
        try{
            this.dbConn.close();
        }
        catch(Exception err)
        {
            System.out.println("DB closing error");  
            System.exit(0);
        }
    }
   
   
    public void createDb(String newDbName){
        this.dbName = newDbName;
        String connectionURL = "jdbc:mysql://localhost:3306/";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.dbConn = DriverManager.getConnection(connectionURL,"root","mysql1");
            Statement s = dbConn.createStatement();
            int result = s.executeUpdate("Create DATABASE " + newDbName);
            System.out.println("Database created");
            this.dbConn.close();
            
                    
        }
        
        catch(Exception err){
            System.out.println("DB creation error "+ newDbName);
            System.exit(0);
        }   
    }
    public void createTable(String newTable, String dbName)
    {
        Statement s;
        
        setDbName(dbName);
        setDbConn();
        try{
            s = this.dbConn.createStatement();
            s.execute(newTable);
            System.out.println("New table created.");
            this.dbConn.close();
        }
        catch(SQLException err)
        {
            System.out.println("Error creating table "+ newTable);
            System.exit(0);
        }
    }
    
        
    
    
    
}
