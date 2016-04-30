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
public class Arac {

    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    
    
    
    String aractur; //hava kara
    String kalkis_yer, varis_yer;
    int id, firma_id, koltuk_sayisi, fiyat;
    Date kalkis_zaman;
    //Date varis_zaman;
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    Arac(String aractur, String kalkis_yer, String varis_yer, int id, int firma_id, int koltuk_sayisi, int fiyat, Date kalkis_zaman ) {  //Date varis_zaman
        this.aractur = aractur;
        this.kalkis_yer = kalkis_yer;
        this.varis_yer = varis_yer;
        this.id = id;
        this.firma_id = firma_id;
        this.koltuk_sayisi = koltuk_sayisi;
        this.fiyat = fiyat;
        this.kalkis_yer = kalkis_yer;
        //this.varis_zaman = varis_zaman;
    }

    void AracEkle(String aractur, String kalkis_yer, String varis_yer, int id, int firma_id, int koltuk_sayisi, int fiyat, Date kalkis_zaman) throws SQLException {   //Date varis_zaman
        Connection con = DriverManager.getConnection(adres, username, password);
        preparedStatement = con.prepareStatement("INSERT INTO acenta.arac("
                + "id,"
                + "firma_id,"
                + "aractur, "
                + "koltuk_sayisi,"
                + "kalkis_zaman,"
               // + "varis_zaman,"
                + "kalkis_yer,"
                + "varis_yer,"
                + "fiyat) "
                + "VALUES (?, ?, ?, ?, ?,?,?,?,?)");

        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, firma_id);
        preparedStatement.setString(3, aractur);
        preparedStatement.setInt(4, koltuk_sayisi);
        preparedStatement.setDate(5, kalkis_zaman);
      //  preparedStatement.setDate(6, new Date());
        preparedStatement.setString(7, kalkis_yer);
        preparedStatement.setString(8, varis_yer);
        preparedStatement.setInt(9, fiyat);

        preparedStatement.executeUpdate();

        aracaKoltukEkle(koltuk_sayisi, id);

    }

    void aracaKoltukEkle(int adet, int arac_id) throws SQLException {
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("SELECT COUNT(*) AS rowcount FROM acenta.koltuk");
        res.next();
        int koltukSayisi = res.getInt("rowcount");
        res.close();

        for (int i = 0; i < adet; i++) {
            preparedStatement = con.prepareStatement("INSERT INTO acenta.koltuk("
                    + "id,"
                    + "dolumu,"
                    + "arac_id, "
                    + "musteri_id)"
                    + "VALUES (?, ?, ?, ?)");

            preparedStatement.setInt(1, koltukSayisi);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, arac_id);
            preparedStatement.setString(4, "");

            preparedStatement.executeUpdate();
            koltukSayisi++;
        }

    }

}
