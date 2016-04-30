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
public class koltuk {

    int id, dolumu, arac_id;
    String musteri_tc;
    //dolumu 1 veya 0 alabilir. dolu ise 1 boş ise 0


    koltuk() {
    }

    koltuk(int id, int dolumu, int arac_id, String musteri_tc) {
        this.id = id;
        this.dolumu = dolumu;
        this.arac_id = arac_id;
        this.musteri_tc = musteri_tc;
    }
    
    
  
    }
    
    


