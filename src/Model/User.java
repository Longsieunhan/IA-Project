/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguyenthanhlong
 */
public class User
{

  public User()
  {
  }

  private int id;
  private String username;
  private String password;
  private String role;
  private int employee_id;

  public User(int id, String username, String password,String role, int employee_id)
  {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.employee_id = employee_id;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public int getEmployee_id()
  {
    return employee_id;
  }

  public void setEmployee_id(int employee_id)
  {
    this.employee_id = employee_id;
  }

  public User(int id, String username, String password, String role)
  {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }



}
