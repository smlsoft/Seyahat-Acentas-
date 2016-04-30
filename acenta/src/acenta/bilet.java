/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acenta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 0535 383 8356
 *
 * @author Zahid
 */
public class bilet {
    
    String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    
    
    String musteri_tc;
    int id, musteri_id, koltuk_id, tutar, odeme_turu_id;  //odeme_turu_id 0 ise nakit, 1 ise kredi kartı, 2 ise çek. bunun için tabloya gerek yok. yeni ödeme yöntemi eklenmeyecek çünkü
    
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    void biletkes(String musteri_tc, int odeme_turu, int koltuk_id) throws SQLException{
        Connection con = DriverManager.getConnection(adres, username, password);//"jdbc:mysql://localhost:3306/acenta?useSSL=false", "root", "6122");
        Acenta a=new Acenta();
        int sonBiletId=a.tablodakiVeriSayisi("bilet");
        
        
        
        preparedStatement = con.prepareStatement("INSERT INTO acenta.bilet("
                + "id,"
                + "musteri_id,"
                + "odeme_turu, "
                + "koltuk_id,"
                + "tutar) "
                + "VALUES (?, ?, ?, ?, ?)");

        preparedStatement.setInt(1, sonBiletId+1);
        preparedStatement.setString(2, musteri_tc);
        preparedStatement.setInt(3, odeme_turu);
        preparedStatement.setInt(4, koltuk_id);
        preparedStatement.setInt(5, tutar);
        
        preparedStatement.executeUpdate();
    
    }
    
    
}
