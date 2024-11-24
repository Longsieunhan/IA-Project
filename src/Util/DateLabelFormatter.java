package Util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField;

/**
 *
 * @author nguyenthanhlong
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter
{

  private final String datePattern = "yyyy-MM-dd";
  private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

  @Override
  public Object stringToValue(String text) throws ParseException
  {
    return dateFormatter.parseObject(text);
  }

  @Override
  public String valueToString(Object value)
  {
    if (value != null)
    {
      Calendar cal = (Calendar) value;
      return dateFormatter.format(cal.getTime());
    }
    return "";
  }
}
