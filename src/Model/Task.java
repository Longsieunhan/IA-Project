/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguyenthanhlong
 */
public class Task
{

  private int ID;
  private String taskName;
  private String taskDescription;
  private String deadline;
  private String employees;

  public Task(int ID, String taskName, String taskDescription, String deadline, String employees)
  {
    this.ID = ID;
    this.taskName = taskName;
    this.taskDescription = taskDescription;
    this.deadline = deadline;
    this.employees = employees;
  }

  public Task()
  {
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public String getTaskName()
  {
    return taskName;
  }

  public void setTaskName(String taskName)
  {
    this.taskName = taskName;
  }

  public String getTaskDescription()
  {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription)
  {
    this.taskDescription = taskDescription;
  }

  public String getDeadline()
  {
    return deadline;
  }

  public void setDeadline(String deadline)
  {
    this.deadline = deadline;
  }

  public String getEmployees()
  {
    return employees;
  }

  public void setEmployees(String employees)
  {
    this.employees = employees;
  }

}
