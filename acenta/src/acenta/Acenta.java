
package acenta;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acenta {
    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    
    
    int getIntDataFromDatabase(String tabloAdi,String verilenSutun,String verilenData, String istenenSutun) throws SQLException{
        Connection con = DriverManager.getConnection(adres, username, password);

        Statement stat = con.createStatement();

        ResultSet res = stat.executeQuery("select "+istenenSutun+" from acenta."+tabloAdi+" where "+verilenSutun+"="+verilenData);
        res.next();
        return res.getInt(istenenSutun);
    }
    
    String getStringDataFromDatabase(String tabloAdi,String verilenSutun,String verilenData, String istenenSutun) throws SQLException{
        Connection con = DriverManager.getConnection(adres, username, password);

        Statement stat = con.createStatement();

        ResultSet res = stat.executeQuery("select "+istenenSutun+" from acenta."+tabloAdi+" where "+verilenSutun+"="+verilenData);
        res.next();
        return res.getString(istenenSutun);
    }
    
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
        
        Acenta a=new Acenta();
        System.out.println(a.getStringDataFromDatabase("musteri", "id", "35008702589", "telno"));
        
        
        seyahatFirmasi sF= new seyahatFirmasi("Devlet Demir Yollari");
      //  Arac u=new Arac();
      //  u.AracEkle("istanbul", "ankara", 2, 50, 50, new Date(2016,12,11),1);
        otel o= new otel();
        o.otelEkle("paradiz", 5 , "istanbul","çok güzel gelin buraya",2,200);
        // personel p=new personel();
        //p.personelekle("mehmeft","123" , "zahid", "çoban");
      
        
        
}
}