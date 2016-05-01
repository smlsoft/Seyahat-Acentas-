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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 0535 383 8356
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

    int uygunKoltuk(int firmaid, Date kalkis_zaman, String kalkis_yer, String varis_yer, boolean biletkesimmi) throws SQLException { //uygun koltuğun idsini döndürür. yoksa 999 döndürür
                                                                                                                                          //ekranda 999 idsi gelirse koltuk bulunamadı diyeceğiz  
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();

        ResultSet res = stat.executeQuery("select * from acenta.koltuk where arac_id="
                                       + "{select arac_id where firma_id="+firmaid
                                       +" and kalkis_zaman="+kalkis_zaman
                                       +" and kalkis_yer="+kalkis_yer
                                       +" and varis_yer="+varis_yer+"}");
        
        ResultSetMetaData metadata = res.getMetaData();
        int columnCount = metadata.getColumnCount();
      
        if(columnCount>0){
            while(res.next()){
            if(res.getInt("dolumu")==1){
                return res.getInt("id");
            }
                }
            return 999;
        }
        else return 999;
    }
    
    void biletkes(String musteri_tc, int odeme_turu, int koltuk_id,int kullanilacakBonusTL) throws SQLException {
        Connection con = DriverManager.getConnection(adres, username, password);//"jdbc:mysql://localhost:3306/acenta?useSSL=false", "root", "6122");
        Acenta a = new Acenta();
        int sonBiletId = a.tablodakiVeriSayisi("bilet");
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("select fiyat from acenta.arac where arac.id={select arac_id from koltuk where id="+koltuk_id+"}");
        int fiyat = res.getInt("fiyat");
        res.close();
        preparedStatement = con.prepareStatement("INSERT INTO acenta.bilet("
                + "id,"
                + "musteri_id,"
                + "odeme_turu, "
                + "koltuk_id,"
                + "tutar) "
                + "VALUES (?, ?, ?, ?, ?)");
        
        preparedStatement.setInt(1, sonBiletId + 1);
        preparedStatement.setString(2, musteri_tc);
        preparedStatement.setInt(3, odeme_turu);
        preparedStatement.setInt(4, koltuk_id);
        preparedStatement.setInt(5, fiyat-kullanilacakBonusTL);

        preparedStatement.executeUpdate();
      
        res = stat.executeQuery("select toplam_bonus from acenta.musteri where id="+musteri_tc);
        int musteriBonus=res.getInt("toplam_bonus");
        
        preparedStatement = con.prepareStatement("UPDATE musteri SET toplam_bonus=? where id=?");
        preparedStatement.setInt(1,musteriBonus-kullanilacakBonusTL);
        preparedStatement.executeUpdate();
        

    }

}
