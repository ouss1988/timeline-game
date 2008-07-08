package com.x.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.GridConstrainedDropController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestApp implements EntryPoint
{

  public static int TIMELINE_WIDTH = 800;
  public static int TIMELINE_HEIGHT = 100;
  public static int NUM_TICKS = 20;

  private TimeEventData timeEventData = new TimeEventData();

  private TimeEventPanel[] timeEventPanels = TimeEventPanel.createTimeEventPanels(
      timeEventData.getTimeEvents(), TIMELINE_HEIGHT);

  double scalePixelsPerYear = (double) TIMELINE_WIDTH
      / (timeEventData.getEndYear() - timeEventData.getStartYear());

  private AbsolutePanel destinationPanel = new AbsolutePanel();
  private HorizontalPanel sourcePanel = new HorizontalPanel();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {

    Image barImage = new Image("bar.gif");

    VerticalPanel vPanel = new VerticalPanel();

    AbsolutePanel labelPanel = new AbsolutePanel();
    labelPanel.setPixelSize(TIMELINE_WIDTH, 20);
    Label labelStart = new Label("Start");
    labelStart.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    Label labelEnd = new Label("End");
    labelEnd.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

    for (int tIdx = 0; tIdx < NUM_TICKS; tIdx++)
    {
      int tickLeftPos = (int) Math.round((TIMELINE_WIDTH * ((double) tIdx / NUM_TICKS)));
      System.out.print(tickLeftPos);

      int tickYear = leftToYear(tickLeftPos);
      Label tickLabel = new Label(String.valueOf(tickYear));
      labelPanel.add(tickLabel, tickLeftPos, 0);
    }
    // labelPanel.add(labelStart,0,0);
    // labelPanel.add(labelEnd,700,0);

    GridConstrainedDropController gridConstrainedDropController = new GridConstrainedDropController(
        destinationPanel,
        1,
        TIMELINE_HEIGHT);

    // We can add style names.
    destinationPanel.setPixelSize(TIMELINE_WIDTH, TIMELINE_HEIGHT * 2);
    destinationPanel.addStyleName("destinationPanel");

    PickupDragController dragController = new PickupDragController(RootPanel.get(), true);
    dragController.registerDropController(gridConstrainedDropController);
    dragController.setBehaviorDragProxy(true);
    dragController.setBehaviorBoundaryPanelDrop(false);

    for (int i = 0; i < timeEventPanels.length; i++)
    {
      timeEventPanels[i].makeDraggable(dragController);
      sourcePanel.add(timeEventPanels[i]);
    }

    HorizontalPanel commandPanel = new HorizontalPanel();
    commandPanel.setPixelSize(TIMELINE_WIDTH, 75);
    Button submitButton = new Button("Done", new ClickListener()
    {

      public void onClick(Widget sender)
      {
        xcyz();
      }
    });

    commandPanel.add(submitButton);

    vPanel.add(labelPanel);
    vPanel.add(barImage);
    vPanel.add(destinationPanel);
    vPanel.add(sourcePanel);
    vPanel.add(commandPanel);

    // Add image and button to the RootPanel
    // Add image and button to the RootPanel
    RootPanel.get().setPixelSize(TIMELINE_WIDTH, 800);
    RootPanel.get().add(vPanel);

  }

  protected void xcyz()
  {
    StringBuffer x = new StringBuffer();
    int error = 0;
    for (int i = 0; i < timeEventPanels.length; i++)
    {
      int nextYear = leftToYear(timeEventPanels[i].getMarkerLeftPosition()
          - destinationPanel.getAbsoluteLeft());
      x.append(",");
      x.append(nextYear);

      error += Math.abs(nextYear - timeEventPanels[i].getStartYear());
    }
    x.append(" Error = " + error);
    Window.alert("Result: " + x);
  }

  private int yearToLeft(int year)
  {
    if (year < timeEventData.getStartYear())
    {
      return 0;
    }
    else if (year > timeEventData.getEndYear())
    {
      return TIMELINE_WIDTH;
    }

    return (int) (scalePixelsPerYear * (year - timeEventData.getStartYear()));
  }

  private int leftToYear(int left)
  {
    if (left < 0)
    {
      return timeEventData.getStartYear();
    }
    else if (left > TIMELINE_WIDTH)
    {
      return timeEventData.getEndYear();
    }

    return timeEventData.getStartYear()
        + (int) Math.round((double) left / scalePixelsPerYear);
  }

}
