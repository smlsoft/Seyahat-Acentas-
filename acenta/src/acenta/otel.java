/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class otel {     //çalışıyor

    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";

    int  yildiz, bonus, fiyat;  //id
    String adi, sehir, aciklama,giris_tarih,cikis_tarih;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    void otelEkle(String isim, int yildiz, String sehir, String aciklama, int bonus,int fiyat,int odaSayisi, String giris_tarih, String cikis_tarih) throws SQLException {  //bonus yüzde olarak %1 ise 1

        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();
      //  Acenta a = new Acenta();
     //   int sonId = a.tablodakiVeriSayisi("otel");
        preparedStatement = con.prepareStatement("INSERT INTO acenta.otel("
               // + "id,"
                + "yildiz,"
                + "adi,"
                + "sehir,"
                + "aciklama,"
                + "bonus,"
                + "fiyat"
                + "giris_tarih"
                + "cikis_tarih)"
                + "VALUES (?, ?, ?, ?, ?, ?,?,?)");

        preparedStatement.setInt(1, yildiz);
        preparedStatement.setString(2, isim);
        preparedStatement.setString(3, sehir);
        preparedStatement.setString(4, aciklama);
        preparedStatement.setInt(5, bonus);
        preparedStatement.setInt(6, fiyat);
        preparedStatement.setString(7, giris_tarih);
        preparedStatement.setString(8, cikis_tarih);
        
        preparedStatement.executeUpdate();
        stat.close();
        
        
        stat = con.createStatement();
        ResultSet res = stat.executeQuery("select * from acenta.otel");
        int otelId = 0;
        while (res.next()) {
                otelId = res.getInt("id");
            }
        
        
        oteleOdaEkle(odaSayisi,otelId);
    }
    
     void oteleOdaEkle(int adet, int otel_id) throws SQLException {
        Acenta a=new Acenta();
        int koltukSayisi=a.tablodakiVeriSayisi("koltuk");
        Connection con = DriverManager.getConnection(adres, username, password);
        
        
        /*Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("SELECT COUNT(*) AS rowcount FROM acenta.koltuk");
        res.next();
        int koltukSayisi = res.getInt("rowcount");
        res.close();*/

        
        for (int i = 0; i < adet; i++) {
            preparedStatement = con.prepareStatement("INSERT INTO acenta.oda("
                    //+ "id,"
                    + "musteri_id,"
                    + "otel_id, "
                    + "dolumu)"
                    + "VALUES (?, ?, ?)");
            
            preparedStatement.setString(1, "-");
            preparedStatement.setInt(2, otel_id);
            preparedStatement.setInt(3, 0);

            preparedStatement.executeUpdate();

        }

    }
    
    
}
