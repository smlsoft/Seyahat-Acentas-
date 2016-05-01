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
public class otel {

    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";

    int  yildiz, bonus, fiyat;  //id
    String adi, sehir, aciklama;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    void otelEkle(String isim, int yildiz, String sehir, String aciklama, int bonus,int fiyat) throws SQLException {  //bonus yüzde olarak %1 ise 1

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
                + "fiyat)"
                + "VALUES (?, ?, ?, ?, ?, ?)");

        preparedStatement.setInt(1, yildiz);
        preparedStatement.setString(2, isim);
        preparedStatement.setString(3, sehir);
        preparedStatement.setString(4, aciklama);
        preparedStatement.setInt(5, bonus);
        preparedStatement.setInt(6, fiyat);
        
        preparedStatement.executeUpdate();

    }
}
