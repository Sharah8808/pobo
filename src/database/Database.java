package database;

import java.util.ArrayList;
import java.sql.*;

public class Database {

  private static final Connection CONN = connect();

  static Connection connect(){
    // create a mysql database connection
    String myDriver = "com.mysql.jdbc.Driver";
    String myUrl = "jdbc:mysql://localhost/treasure_hunter?serverTimezone=UTC";
    Connection con = null;
    try {
      Class.forName(myDriver);
      con = DriverManager.getConnection(myUrl, "root", "");
    } catch (Exception e) {
      System.err.println(e.getMessage());    
    }
    return con;
  }

  public static ArrayList<Highscore> getHighscores(){
    ResultSet rs = null;
    try {
      Statement st = CONN.createStatement();
      // note that i'm leaving "date_created" out of this insert statement
      String query = "SELECT * FROM scores order by score desc limit 3";
      
      // execute the query, and get a java resultset
      rs = st.executeQuery(query);
      ArrayList<Highscore> hs = new ArrayList<Highscore>();
      // iterate through the java resultset
      while (rs.next())
      {
        int score = rs.getInt("score");
        String scorer = rs.getString("name");
        hs.add(new Highscore(score, scorer));
      }
      st.close();
      return hs;
    }
    catch (Exception e){
      System.err.println(e);
      return null;
    }
  }
  
  public static void saveHighscore(int score, String scorer){

    try {      
      Statement st = CONN.createStatement();

      // note that i'm leaving "date_created" out of this insert statement
      st.executeUpdate("INSERT INTO scores (score, name) "
          +"VALUES (" + score + ", '" + scorer + "')");
    }
    catch (Exception e){
      System.err.println(e.getMessage());
    }
  }

}
