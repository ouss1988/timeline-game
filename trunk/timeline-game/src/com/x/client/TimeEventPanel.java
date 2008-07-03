package com.x.client;

import com.allen_sauer.gwt.dnd.client.DragController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TimeEventPanel extends FocusPanel implements TimeEvent
{
  private static int MARKER_LEFT_OFFSET = 9;
  private int startYear;
  private String description;
  
  private AbsolutePanel outerPanel = new AbsolutePanel();
 // private VerticalPanel outerPanel = new VerticalPanel();
  private SimplePanel topLeftPanel = new SimplePanel();
  private SimplePanel topRightPanel = new SimplePanel();
  private SimplePanel bottomPanel = new SimplePanel();

  Image markerImage = new Image("marker.gif");
  
  public TimeEventPanel(int startYear, String text, int height)
  {
    this.description = text;
    this.startYear = startYear;
    this.setPixelSize(75, height);
    outerPanel.setPixelSize(75, height);
    Label testLabel = new Label(text);
    //this.add(testLabel);
    this.setStyleName("movingPanel");
    
    bottomPanel.add(testLabel);
    bottomPanel.setPixelSize(75, height - 20); 

    bottomPanel.setStyleName("movingPanel");

//    topLeftPanel.setPixelSize(75, 20);
//    topLeftPanel.setPixelSize(20, 20);
//    topLeftPanel.setStyleName("durationPanel");
//    topLeftPanel.add(markerImage);
    outerPanel.add(markerImage,0,0);
    outerPanel.add(bottomPanel,0,20);
    
    this.add(outerPanel);
    

  }

  public int getMarkerLeftPosition()
  {
    return  getAbsoluteLeft() + MARKER_LEFT_OFFSET;
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
