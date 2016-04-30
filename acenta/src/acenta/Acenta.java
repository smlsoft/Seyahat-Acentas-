
package acenta;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acenta {
    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    
    
    int tablodakiVeriSayisi(String tabloAdi) throws SQLException{
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("SELECT COUNT(*) AS rowcount FROM acenta."+tabloAdi);
        res.next();
        int veriSayisi = res.getInt("rowcount");
        res.close();
        return veriSayisi;
    }
    
    public static void main(String[] args) throws SQLException {    

        seyahatFirmasi sF= new seyahatFirmasi("Devlet Demir Yolları");
        
        
}
}