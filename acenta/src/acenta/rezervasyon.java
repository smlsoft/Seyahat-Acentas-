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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Zahid
 */
public class rezervasyon {  //çalışıyor
    
        String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";

    String musteri_tc;
    int id, oda_id, tutar, odeme_turu_id;  //odeme_turu_id 0 ise nakit, 1 ise kredi kartı, 2 ise çek. bunun için tabloya gerek yok. yeni ödeme yöntemi eklenmeyecek çünkü

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    otel[] uygunOtel(String giris_tarih, String cikis_tarih, String sehir) throws SQLException { //uygun otelin idsini döndürür. yoksa 999 döndürür
        //ekranda 999 idsi gelirse koltuk bulunamadı diyeceğiz  
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();
      //SELECT COUNT(*) AS rowcount FROM acenta."+tabloAdi
        ResultSet res = stat.executeQuery("select COUNT(*) AS rowcount from acenta.otel where "
                + "sehir='"+sehir
                + "' and giris_tarih='"+giris_tarih
                + "' and cikis_tarih='"+cikis_tarih+"'");
       //
        ResultSetMetaData metadata = res.getMetaData();
      
        res.next();
        int rowCount = res.getInt("rowcount");
        res.close();
      
        otel[] oteller=new otel[rowCount];
      ///
        
        
        res = stat.executeQuery("select * from acenta.otel where "
                + "sehir='"+sehir
                + "' and giris_tarih='"+giris_tarih
                + "' and cikis_tarih='"+cikis_tarih+"'");
        
        int i=0;
        if (rowCount > 0) {
            
            while (res.next()) {
           
                int odaid=oteldekiSiradakiBosOdaId((res.getInt("id")));
                System.out.println(odaid);
                if (odaid!=999) {
                    oteller[i]=new otel(
                            res.getInt("id"), //id eklendi
                            res.getInt("yildiz"),
                            res.getInt("bonus"),
                            res.getInt("fiyat"),
                            res.getString("adi"),
                            res.getString("sehir"),
                            res.getString("aciklama"),
                            res.getString("giris_tarih"),
                            res.getString("cikis_tarih"),
                            odaid);
                }
                i++;
            }
            
            
        } else {
            return null;
        }
            return oteller;
    }

    int oteldekiSiradakiBosOdaId(int otel_id) throws SQLException{
        Connection con = DriverManager.getConnection(adres, username, password);
        Statement stat = con.createStatement();

        ResultSet res = stat.executeQuery("select * from acenta.oda where otel_id="+otel_id);
        ResultSetMetaData metadata = res.getMetaData();
        int columnCount = metadata.getColumnCount();

        if (columnCount > 0) {
            while (res.next()) {
                if (res.getInt("dolumu") == 0) {
                    return res.getInt("id");
                }
            }
            return 999;
        } else {
            return 999;
        }
    }
    
    void rezervasyonYap(String musteri_tc, int odeme_turu, int otel_id, int kullanilacakBonusTL) throws SQLException {
        Connection con = DriverManager.getConnection(adres, username, password);

        
        int oda_id=oteldekiSiradakiBosOdaId(otel_id);
        
        //oda fiyatını çek
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery("select fiyat from acenta.otel where otel.id="+otel_id);
        res.next();
        int fiyat = res.getInt("fiyat");
        res.close();
        

        
        //rezarvasyona bilgileri gir
        preparedStatement = con.prepareStatement("INSERT INTO acenta.rezervasyon("
                + "oda_id,"
                + "musteri_id, "
                + "odeme_turu_id,"
                + "tutar) "
                + "VALUES (?, ?, ?, ?)");  //?
        
        preparedStatement.setInt(1, oda_id);
        preparedStatement.setString(2, musteri_tc);
        preparedStatement.setInt(3, odeme_turu);
        preparedStatement.setInt(4, fiyat - kullanilacakBonusTL);

        preparedStatement.executeUpdate();

        
        //musterinin mevcut bonusu
        res = stat.executeQuery("select toplam_bonus from acenta.musteri where id='" + musteri_tc+"'");
        res.next();
        int musteriBonus = res.getInt("toplam_bonus");
        res.close();

    
        //otelin bonusu
        res = stat.executeQuery("select bonus from acenta.otel where otel.id="+otel_id);
        res.next();
        int bonus = res.getInt("bonus");

        
        //musteri bonusu update et
        preparedStatement = con.prepareStatement("UPDATE musteri SET toplam_bonus=? where id=?");

        int musteriSonBonus = 0;
        if (bonus / 100 == 0) {
            musteriSonBonus = musteriBonus - kullanilacakBonusTL + 1;
        } else {
            musteriSonBonus = musteriBonus - kullanilacakBonusTL + fiyat * bonus / 100;
        }
        preparedStatement.setInt(1, musteriSonBonus);
        preparedStatement.setString(2, musteri_tc);
        preparedStatement.executeUpdate();

        

//oda update
        preparedStatement = con.prepareStatement("UPDATE acenta.oda SET oda.dolumu=1 , oda.musteri_id=? where oda.id=?");
        preparedStatement.setString(1, musteri_tc);
        preparedStatement.setInt(2, oda_id);
        preparedStatement.executeUpdate();



//kasa update
        res = stat.executeQuery("select para from kasa");
        res.next();
        int kasadakiPara = res.getInt("para");
        int kasadakiSonPara = kasadakiPara+fiyat-kullanilacakBonusTL;
        preparedStatement = con.prepareStatement("UPDATE acenta.kasa SET para="+kasadakiSonPara);
        preparedStatement.executeUpdate();

    }
    
    
}
