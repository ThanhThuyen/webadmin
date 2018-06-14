/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import database.MakeConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.User;

/**
 *
 * @author thuyento
 */
public class Users implements Serializable {

  public static List<User> getAllUsers() {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<User> data = null;
    try (Connection con = new MakeConnection("webadmin", "webadmin", "localhost", "webadmin").getCON()) {
      String sql = "SELECT `users`.`uid`, `users`.`uname`, `users`.`upass`, `users`.`urole`, `users`.`access` FROM `users`;";
      pstm = con.prepareStatement(sql);
      rs = pstm.executeQuery();
      data = new ArrayList<>();
      while (rs.next()) {
        data.add(new User(rs.getInt("uid"), rs.getString("uname"), rs.getString("upass"), iRole2sRole(rs.getInt("urole")), rs.getBoolean("access")));
      }
    } catch (SQLException ex) {
      Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return data != null && data.size() > 0 ? data : null;
    }
  }

  public static User checkUserLogin(String uname, String upass) {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    User data = null;
    try (Connection con = new MakeConnection("webadmin", "webadmin", "localhost", "webadmin").getCON()) {
      String sql = "SELECT users.* FROM users WHERE users.uname = ? && users.upass = ?;";
      pstm = con.prepareStatement(sql);
      pstm.setString(1, uname);
      pstm.setString(2, upass);
      System.out.println(pstm.toString());
      rs = pstm.executeQuery();
      if (rs.next()) {
        data = new User(rs.getInt("uid"), rs.getString("uname"), rs.getString("upass"), iRole2sRole(rs.getInt("urole")), rs.getBoolean("access"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return data;
    }
  }

  private static boolean checkUsername(String uname) {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    boolean output = false;
    try (Connection con = new MakeConnection("webadmin", "webadmin", "localhost", "webadmin").getCON()) {
      String sql = "SELECT users.* FROM users WHERE users.uname = %";
      pstm = con.prepareStatement(sql);
      pstm.setString(1, uname);
      rs = pstm.executeQuery();
      output = rs.next();
    } catch (SQLException ex) {
      Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return output;
    }
  }

  public static boolean createUser(String uname, String upass, String role) {
    if (!checkUsername(uname)) {
      PreparedStatement pstm = null;
      boolean output = false;
      try (Connection con = new MakeConnection("webadmin", "webadmin", "localhost", "webadmin").getCON()) {
        String sql = "INSERT INTO `users`(`uname`,`upass`,`urole`) VALUES (%,%,%)";
        pstm = con.prepareStatement(sql);
        pstm.setString(1, uname);
        pstm.setString(2, upass);
        pstm.setInt(3, sRole2iRole(role));
        output = pstm.executeUpdate() > 0;
      } catch (Exception e) {
      } finally {
        if (pstm != null) {
          try {
            pstm.close();
          } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        return output;
      }
    }
    return false;
  }

  public static boolean updateUserByUname(String uname, String upass, String role) {
    if (checkUsername(uname)) {
      PreparedStatement pstm = null;
      boolean output = false;
      try (Connection con = new MakeConnection("webadmin", "webadmin", "localhost", "webadmin").getCON()) {
        String sql = "UPDATE `users` SET `upass` = % ,`urole` = % WHERE `uname` = %";
        pstm = con.prepareStatement(sql);
        pstm.setString(1, upass);
        pstm.setString(3, uname);
        pstm.setInt(2, sRole2iRole(role));
        output = pstm.executeUpdate() > 0;
      } catch (Exception e) {
      } finally {
        if (pstm != null) {
          try {
            pstm.close();
          } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        return output;
      }
    }
    return false;
  }

  public static HashMap<Integer, String> getAllUserRoles() {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    HashMap<Integer, String> data = null;
    try (Connection con = new MakeConnection("webadmin", "webadmin", "localhost", "webadmin").getCON()) {
      String sql = "SELECT `roles`.`rid`,`roles`.`rname` FROM `roles`;";
      pstm = con.prepareStatement(sql);
      rs = pstm.executeQuery();
      data = new HashMap<>();
      while (rs.next()) {
        data.put(rs.getInt("rid"), rs.getString("rname"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        } catch (SQLException ex) {
          Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return data != null && data.size() > 0 ? data : null;
    }
  }

  public static int sRole2iRole(String role) {
    HashMap<Integer, String> roles = getAllUserRoles();
    int irole = 101;
    for (Map.Entry<Integer, String> entry : roles.entrySet()) {
      if (entry.getValue().equals(role)) {
        return entry.getKey();
      }
    }
    return 101;
  }

  public static String iRole2sRole(int role) {
    HashMap<Integer, String> roles = getAllUserRoles();
    return roles.containsKey(role) ? roles.get(role) : roles.get(101);
  }

}
