
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
       //System.out.println(a.getStringDataFromDatabase("musteri", "id", "35008702589", "telno"));
        
        
        //seyahatFirmasi sF= new seyahatFirmasi("Devlet Demir Yollari");
        //Arac u=new Arac();
        //u.AracEkle("istanbul", "ankara", 2, 50, 50, "11.12.2016",1);
       // otel o= new otel();
        //o.otelEkle("tatilcehennemi", 1 , "istanbul","çok güzel gelin buraya",2,200,100,"11.12.2016","15.12.2016");
        // personel p=new personel();
        //p.personelekle("mehmeft","123" , "zahid", "çoban");
      
        //bilet b =new bilet();
        //System.out.println(b.uygunKoltuk(2,"11.12.2016" , "istanbul", "ankara"));
        //b.biletkes( "35008702589",0, 252, 0);
        //b.biletkes("35008702589",0, b.uygunKoltuk(2,"11.12.2016" , "istanbul", "ankara"), 0);
        
        
        rezervasyon r=new rezervasyon();
        //System.out.println(r.oteldekiSiradakiBosOdaId(3));
        //r.rezervasyonYap("35008702589", 0, 3, 0);
        
        //System.out.println(r.uygunOtel("11.12.2016", "15.12.2016", "istanbul")[0].aciklama);
        
}
}