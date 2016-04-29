package acenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class musteri {

    int bonus;
    String telno, tc, ad, soyad, mail, sifre, cinsiyet;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    musteri() {
    }

    musteri(String tc, String telno, String ad, String soyad, String mail, String sifre, String cinsiyet) {
        this.tc = tc;
        this.telno = telno;
        this.ad = ad;
        this.soyad = soyad;
        this.mail = mail;
        this.sifre = sifre;
        this.cinsiyet = cinsiyet;
        bonus = 0;
    }

    void musteriekle(String tc, String telno, String ad, String soyad, String mail, String sifre, String cinsiyet) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/acenta?useSSL=false", "root", "6122");
        preparedStatement = con.prepareStatement("INSERT INTO acenta.musteri("
                + "id,"
                + "telno,"
                + "toplam_bonus, "
                + "mail,"
                + "sifre,"
                + "cinsiyet,"
                + "adi,"
                + "soyadi) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, tc);
        preparedStatement.setString(2, telno);
        preparedStatement.setInt(3, 0);
        preparedStatement.setString(4, ad);
        preparedStatement.setString(5, soyad);
        preparedStatement.setString(6, mail);
        preparedStatement.setString(7, sifre);
        preparedStatement.setString(8, cinsiyet);

        preparedStatement.executeUpdate();

    }

    musteri getmusteribytc(String tc) throws SQLException {
        String telno=null;
        String ad=null;
        String soyad=null;
        String mail=null;
        String sifre=null;
        String cinsiyet=null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/acenta?useSSL=false", "root", "6122");

        Statement stat = con.createStatement();

        ResultSet res = stat.executeQuery("select * from acenta.musteri");

        while (res.next()) {
            if (res.getString("id").equals(tc)) {
                telno = res.getString("telno");
                ad = res.getString("adi");
                soyad = res.getString("soyadi");
                mail = res.getString("mail");
                sifre = res.getString("sifre");
                cinsiyet = res.getString("cinsiyet");
            }
        }

        return new musteri(tc, telno, ad, soyad, mail, sifre, cinsiyet);
    }

    public static void main(String args[]) throws SQLException {
        musteri mus = new musteri();
        //mus.musteriekle("35008702589", 537, "zahidcoban@gmail.com", "123", "erkek", "Muhammed Zahid", "Çoban");
        System.out.println(mus.getmusteribytc("35008702588").ad);
    }
    
//veri değiştirme sql kodu ama eklemedim
//UPDATE `acenta`.`musteri` SET `adi`='ahmet' WHERE `id`='35008702589';
    
}

