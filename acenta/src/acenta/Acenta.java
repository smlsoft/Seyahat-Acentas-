
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
        return veriSayisi; //satır sayısını döndürür yani
    }
    
    public static void main(String[] args) throws SQLException {    

        //seyahatFirmasi sF= new seyahatFirmasi("Devlet Demir Yollari");
        //Arac u=new Arac();
        //u.AracEkle("istanbul", "ankara", 1, 100, 50, new Date(2016,12,11));
        //otel o= new otel();
        //o.otelEkle("paradiz", 5 , "istanbul");
        // personel p=new personel();
        //p.personelekle("mehmeft","123" , "zahid", "çoban");
      
        
        
}
}