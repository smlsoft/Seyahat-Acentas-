
package acenta;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acenta {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
    public static void main(String[] args) throws ClassNotFoundException {
      try {
          // Class.forName("com.mysql.jdbc.Driver");
          
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/acenta?useSSL=false","root","6122");
          
          Statement stat = con.createStatement();
          
          ResultSet res = stat.executeQuery("select * from admin");
          
          while(res.next()){
              System.out.println(res.getInt("id")+" "+res.getString("kullanici_adi"));
          }
          
      } catch (SQLException ex) {
          Logger.getLogger(Acenta.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
}
