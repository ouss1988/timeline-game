package com.x.client;

public class TimeEventData
{

  static int dataSetIdx = 9999;

  static TimeEventData[] dataSets = new TimeEventData[3];

  static
  {
    dataSetIdx = 0;
    dataSets[dataSetIdx] = new TimeEventData();
    dataSets[dataSetIdx].startYear = 1400;
    dataSets[dataSetIdx].endYear = 2008;
    dataSets[dataSetIdx].timeEvents = new TimeEvent[] {
        new TimeEventImpl(1492, "Columbus Discovers America"),
        new TimeEventImpl(1596, "Pilgrims come to America"),
        new TimeEventImpl(1776, "Declaration of Independence"),
        new TimeEventImpl(1917, "First World War"),
        new TimeEventImpl(1944, "World War - II")};
    
    dataSetIdx++;
    dataSets[dataSetIdx] = new TimeEventData();
    dataSets[dataSetIdx].startYear = 1750;
    dataSets[dataSetIdx].endYear = 2000;
    dataSets[dataSetIdx].timeEvents = new TimeEvent[] {
        new TimeEventImpl(1861, "American Civil War"),
        new TimeEventImpl(1898, "Spanish American War"),
        new TimeEventImpl(1776, "Declaration of Independence"),
        new TimeEventImpl(1917, "First World War"),
        new TimeEventImpl(1944, "World War - II")};
    
    dataSetIdx++;
    dataSets[dataSetIdx] = new TimeEventData();
    dataSets[dataSetIdx].startYear = 1915;
    dataSets[dataSetIdx].endYear = 1950;
    dataSets[dataSetIdx].timeEvents = new TimeEvent[] {
        new TimeEventImpl(1933, "Prohibition Ends"),
        new TimeEventImpl(1929, "Great Depression"),
        new TimeEventImpl(1920, "Prohibition Starts"),
        new TimeEventImpl(1917, "First World War"),
        new TimeEventImpl(1944, "World War - II")};

  }

  static public TimeEventData retrieveTimeEventData()
  {
    dataSetIdx++;
    if (dataSetIdx >= dataSets.length)
    {
      dataSetIdx = 0;
    }

    return dataSets[dataSetIdx];
  }

  private int startYear;
  private int endYear;
  private TimeEvent[] timeEvents;

  public TimeEvent[] getTimeEvents()
  {
    return timeEvents;
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
