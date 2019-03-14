
package rma;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Tamas Bayer
 */
public class DB {
    String URL = "jdbc:derby:sampleDB;create=true";
    String USERNAME = "";
    String PASSWORD = "";
    
    
     Connection conn = null;
     Statement createStatement = null;
     DatabaseMetaData dbmd = null;
     
     public DB(){
       
        
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error with Connection");
            System.out.println(" "+ex);
        }
        
        if (conn != null){
            try {
                 createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Error with createStatement");
                System.out.println(" "+ex);
            }
            
        }
            
            try {
                dbmd = conn.getMetaData();
            } catch (SQLException ex) {
                System.out.println("Error with DatabaseMetaData");
                System.out.println(" "+ex);
            }
            
            try {
                ResultSet rs = dbmd.getTables(null, "APP", "RMAS", null);
                if (!rs.next()){
                    createStatement.execute("create table RMAS(rmaNummer varchar(20), cridNummer varchar(20), kunde varchar(20), datum varchar(20))");
                }
            } catch (SQLException ex) {
                System.out.println("Error with resultTable");
                System.out.println(" "+ex); 
            }
       }
     
         public ArrayList<rmaData> getAllRMA(){
        String sql = "select * from RMAS";
        ArrayList<rmaData> rmas = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            rmas = new ArrayList<>();
            
            while (rs.next()){
            rmaData actualrmaData = new rmaData(rs.getString("rmaNummer"),rs.getString("cridNummer"), rs.getString("kunde"), rs.getString("datum"));
            rmas.add(actualrmaData);
            }
        } catch (SQLException ex) {
            System.out.println("Error with showAllRMA");
            System.out.println(" "+ex);
        }    
        
        return rmas;
    }
         
        public void addRMA(String rNummer, String cNummer, String kun, String dat){
        try {
              String sql = "insert into RMAS values (?,?,?,?)";
              PreparedStatement preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, rNummer);
              preparedStatement.setString(2, cNummer);
              preparedStatement.setString(3, kun);
              preparedStatement.setString(4, dat);
              preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("Error with addUser");
            System.out.println(" "+ex);
        }
    }
         
          public void addRMA(rmaData RMA){
        try {
              String sql = "insert into RMAS values (?,?,?,?)";
              PreparedStatement preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, RMA.getRmaNummer());
              preparedStatement.setString(2, RMA.getCridNummer());
              preparedStatement.setString(3, RMA.getKunde());
              preparedStatement.setString(4, RMA.getDatum());
              preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Error with add RMA");
            System.out.println(""+ex);
        }
    }
          public void updateRMA(rmaData RMA){
        try {
              String sql = "update RMAS set rmaNummer = ?, cridNummer = ?, kunde = ?, datum = ?";
              PreparedStatement preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, RMA.getRmaNummer()); 
              preparedStatement.setString(2, RMA.getCridNummer());
              preparedStatement.setString(3, RMA.getKunde());
              preparedStatement.setString(4, RMA.getDatum());
              preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Error with add RMA");
            System.out.println(""+ex);
        }
    }
}
