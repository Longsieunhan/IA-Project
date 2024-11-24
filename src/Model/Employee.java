/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguyenthanhlong
 */
public class Employee
{

  private int id;

  public Employee(String name, int id)
  {
    this.name = name;
    this.id = id;
  }
  private String name;
  private int age;
  private String status;
  private String gender;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getGender()
  {
    return gender;
  }

  public void setGender(String gender)
  {
    this.gender = gender;
  }

  public Employee(int id, String name, int age, String status, String gender)
  {
    this.id = id;
    this.name = name;
    this.age = age;
    this.status = status;
    this.gender = gender;
  }

  public Employee()
  {
  }
  
  public Employee(String name) {
    this.name = name;
  }

}
