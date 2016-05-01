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

    int id, yildiz, uygunluk, indirim;
    String adi, tur, bolge, aciklama;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    void otelEkle(String isim, int yildiz, String bolge) throws SQLException {
        tur = "";
        uygunluk = 0;
        indirim = 0;
        aciklama = "";
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();
        Acenta a = new Acenta();
        int sonId = a.tablodakiVeriSayisi("otel");
        preparedStatement = con.prepareStatement("INSERT INTO acenta.otel("
                + "id,"
                + "tur,"
                + "yildiz,"
                + "adi,"
                + "adres,"
                + "uygunluk,"
                + "aciklama,"
                + "indirim)"
                + "VALUES (?, ?,?,?,?,?,?,?)");

        preparedStatement.setInt(1, sonId + 1);
        preparedStatement.setString(2, "");
        preparedStatement.setInt(3, yildiz);
        preparedStatement.setString(4, isim);
        preparedStatement.setString(5, bolge);
        preparedStatement.setInt(6, 0);
        preparedStatement.setString(7, "");
        preparedStatement.setInt(8, 0);

        preparedStatement.executeUpdate();

    }
}
