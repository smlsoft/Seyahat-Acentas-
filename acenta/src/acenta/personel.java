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
 * @author emre
 */
public class personel {
     String adres = "jdbc:mysql://94.73.170.236/acenta";
    String username = "fsm";
    String password = "RRrv34U8";
    
    String kullaniciAdi,kullaniciSifre, ad, soyad;
    int personelId;
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
     personel(String kullaniciAdi, String kullaniciSifre, String ad, String soyad) {
        this.kullaniciAdi= kullaniciAdi;
        this.kullaniciSifre = kullaniciSifre;
        this.ad = ad;
        this.soyad = soyad;
    }

    private personel() {
        
    }
    
      void personelekle(String kullaniciAdi, String kullaniciSifre, String ad, String soyad) throws SQLException {

        Connection con = DriverManager.getConnection(adres, username, password);
        preparedStatement = con.prepareStatement("INSERT INTO acenta.personel("
               
                + "id,"
                + "kullanici_adi,"
                + "kullanici_sifre, "
                + "isim,"               
                + "soyisim) "
                + "VALUES (?, ?, ?, ?, ?)");

        preparedStatement.setInt(1, personelId);
        preparedStatement.setString(2, kullaniciAdi);
        preparedStatement.setString(3, kullaniciSifre);
        preparedStatement.setString(4, ad);
        preparedStatement.setString(5, soyad);
      

        preparedStatement.executeUpdate();

    }

    personel getpersonelbykullaniciAdi(String kullaniciAdi) throws SQLException {
        
        String ad = null;
        String soyad = null;
        String sifre = null;
        
        Connection con = DriverManager.getConnection(adres, username, password);

        Statement stat = con.createStatement();

        ResultSet res = stat.executeQuery("select * from acenta.personel");

        while (res.next()) {
            if (res.getString("kullanici_adi").equals(kullaniciAdi)) {
                
                sifre = res.getString("kullanici_sifre");
                ad = res.getString("isim");
                soyad = res.getString("soyisim");  
                
              
            }
        }

        return new personel( kullaniciAdi,  kullaniciSifre,  ad,  soyad);
    }
      
      public static void main(String args[]) throws SQLException {
        personel p1 = new personel();
        //p1.personelekle("root","000", "zahid", "coban");
        System.out.println(p1.getpersonelbykullaniciAdi("root").ad+" "+p1.getpersonelbykullaniciAdi("root").soyad);
    }

}

