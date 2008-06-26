package com.x.client;

import com.allen_sauer.gwt.dnd.client.DragController;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

public class TimeEventPanel extends FocusPanel implements TimeEvent
{

  private int startYear;
  private String description;

  public TimeEventPanel(int startYear, String text, int height)
  {
    this.description = text;
    this.startYear = startYear;
    this.setPixelSize(75, height);
    Label testLabel = new Label(text);
    this.add(testLabel);
    this.setStyleName("movingPanel");

  }

  public void makeDraggable(DragController dragController)
  {
    dragController.makeDraggable(this);
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
