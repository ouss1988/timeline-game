package com.x.client;

public class TimeEventData
{

  int startYear = 1400;
  int endYear = 2008;
  
  public TimeEvent[] getTimeEvents()
  {
    return new TimeEvent[] { new TimeEventImpl(1492, "Columbus Discovers America"),
        new TimeEventImpl(1596, "Pilgrims come to America"),
        new TimeEventImpl(1776, "Declaration of Independence"),
        new TimeEventImpl(1917, "First World War"),
        new TimeEventImpl(1944, "World War - II")};
  }

  
  public int getStartYear()
  {
    return startYear;
  }

  
  public void setStartYear(int startYear)
  {
    this.startYear = startYear;
  }

  
  public int getEndYear()
  {
    return endYear;
  }

  
  public void setEndYear(int endYear)
  {
    this.endYear = endYear;
  }
}
