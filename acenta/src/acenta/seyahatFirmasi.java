
package acenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Zahid
 */
public class seyahatFirmasi {
    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    int id;
    String isim;
    

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    seyahatFirmasi(String isim) throws SQLException{
    
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();
        Acenta a=new Acenta();
        int sonId = a.tablodakiVeriSayisi("firma");
        preparedStatement = con.prepareStatement("INSERT INTO acenta.firma("
                + "id,"
                + "isim)"
                + "VALUES (?, ?)");

        preparedStatement.setInt(1, sonId);
        preparedStatement.setString(2, isim);

        preparedStatement.executeUpdate();
        
    }
    
    
}
