/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author thuyento
 */
public class User implements Serializable {

  private int uid;
  private String uname;
  private String upass;
  private String urole;
  private boolean access;

  public User() {
  }

  public User(String uname, String upass) {
    this.uname = uname;
    this.upass = upass;
  }

  public User(String uname, String upass, String urole) {
    this.uname = uname;
    this.upass = upass;
    this.urole = urole;
  }

  public User(int uid, String uname, String upass, String urole, boolean access) {
    this.uid = uid;
    this.uname = uname;
    this.upass = upass;
    this.urole = urole;
    this.access = access;
  }

  /**
   * @return the uid
   */
  public int getUid() {
    return uid;
  }

  /**
   * @param uid the uid to set
   */
  public void setUid(int uid) {
    this.uid = uid;
  }

  /**
   * @return the uname
   */
  public String getUname() {
    return uname;
  }

  /**
   * @param uname the uname to set
   */
  public void setUname(String uname) {
    this.uname = uname;
  }

  /**
   * @return the upass
   */
  public String getUpass() {
    return upass;
  }

  /**
   * @param upass the upass to set
   */
  public void setUpass(String upass) {
    this.upass = upass;
  }



  /**
   * @return the access
   */
  public boolean isAccess() {
    return access;
  }

  /**
   * @param access the access to set
   */
  public void setAccess(boolean access) {
    this.access = access;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.getUname());
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final User other = (User) obj;
    return Objects.equals(this.getUname(), other.getUname());
  }

  /**
   * @return the urole
   */
  public String getUrole() {
    return urole;
  }

  /**
   * @param urole the urole to set
   */
  public void setUrole(String urole) {
    this.urole = urole;
  }

}
