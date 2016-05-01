/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acenta;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Zahid
 */
public class Arac {       //çalışıyor

    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    
    
    
   // String aractur;
    String kalkis_yer, varis_yer;
    int id, firma_id, koltuk_sayisi, fiyat, bonus;
    Date kalkis_zaman;
    //Date varis_zaman;
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    
    Arac(){}
    
    //bu constructor databasede girdi oluşturmaz
    Arac(String kalkis_yer, String varis_yer, int id, int firma_id, int koltuk_sayisi, int fiyat, Date kalkis_zaman, int bonus ) {  //Date varis_zaman
        //this.aractur = "araç";
        this.kalkis_zaman = kalkis_zaman;
        this.varis_yer = varis_yer;
        this.id = id;
        this.firma_id = firma_id;
        this.koltuk_sayisi = koltuk_sayisi;
        this.fiyat = fiyat;
        this.kalkis_yer = kalkis_yer;
        this.bonus=bonus;
        //this.varis_zaman = varis_zaman;
    }

    void AracEkle(String kalkis_yer, String varis_yer, int firma_id, int koltuk_sayisi, int fiyat, Date kalkis_zaman, int bonus) throws SQLException {   //Date varis_zaman
        Connection con = DriverManager.getConnection(adres, username, password);
        preparedStatement = con.prepareStatement("INSERT INTO acenta.arac("
             //   + "id,"
                + "firma_id,"
                //+ "aractur, "
                + "koltuk_sayisi,"
                + "kalkis_zaman,"
               // + "varis_zaman,"
                + "kalkis_yer,"
                + "varis_yer,"
                + "fiyat,"
                + "bonus) "
                + "VALUES ( ?, ?, ?,?,?,?,?)"); //??
        
        //Acenta a=new Acenta();
       // int sonId=a.tablodakiVeriSayisi("arac");
        
      //  preparedStatement.setInt(1, sonId+1);
        preparedStatement.setInt(1, firma_id);
        //preparedStatement.setString(2, "araç");
        preparedStatement.setInt(2, koltuk_sayisi);
        preparedStatement.setDate(3, kalkis_zaman);
        preparedStatement.setString(4, kalkis_yer);
        preparedStatement.setString(5, varis_yer);
        preparedStatement.setInt(6, fiyat);
        preparedStatement.setInt(7, bonus);

        preparedStatement.executeUpdate();
        
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("select * from acenta.arac");
        int aracId = 0;
        while (res.next()) {
                aracId = res.getInt("id");
            }
        
        
        aracaKoltukEkle(koltuk_sayisi, aracId);

    }

    void aracaKoltukEkle(int adet, int arac_id) throws SQLException {
        Acenta a=new Acenta();
        int koltukSayisi=a.tablodakiVeriSayisi("koltuk");
        Connection con = DriverManager.getConnection(adres, username, password);
        
        
        /*Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("SELECT COUNT(*) AS rowcount FROM acenta.koltuk");
        res.next();
        int koltukSayisi = res.getInt("rowcount");
        res.close();*/

        
        for (int i = 0; i < adet; i++) {
            preparedStatement = con.prepareStatement("INSERT INTO acenta.koltuk("
                    //+ "id,"
                    + "dolumu,"
                    + "arac_id, "
                    + "musteri_id)"
                    + "VALUES (?, ?, ?)");

            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, arac_id);
            preparedStatement.setInt(3, 0);
            //preparedStatement.setString(4, "");

            preparedStatement.executeUpdate();
            //koltukSayisi++;
        }

    }
    
    
    
}
