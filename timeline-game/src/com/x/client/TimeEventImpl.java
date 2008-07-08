package com.x.client;

public class TimeEventImpl implements TimeEvent
{

  private int startYear;
  private String description;

  public TimeEventImpl(int startYear, String description)
  {
    this.description = description;
    this.startYear = startYear;
  }

  public int getStartYear()
  {
    return startYear;
  }

  public void setStartYear(int startYear)
  {
    this.startYear = startYear;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

}
