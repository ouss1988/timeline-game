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
  private AbsolutePanel destinationPanel = new AbsolutePanel();
  private HorizontalPanel sourcePanel = new HorizontalPanel();
  private PickupDragController dragController;
  AbsolutePanel labelPanel = new AbsolutePanel();
  private Label[] tickLabel = new Label[NUM_TICKS];
  private int score;
  private Label scoreTextLabel = new Label("Score (Low is good) = ");
  private Label scoreLabel = new Label("0");
  
  /**
   * These variables change on each iteration, i.e. each set of events
   */
  private TimeEventData timeEventData;
  private TimeEventPanel[] timeEventPanels;
  private double scalePixelsPerYear;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    Image barImage = new Image("bar.gif");
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.addStyleName("destinationPanel");
    labelPanel.setPixelSize(TIMELINE_WIDTH, 20);

    for (int tIdx = 0; tIdx < NUM_TICKS; tIdx++)
    {
      int tickLeftPos = (int) Math
          .round((TIMELINE_WIDTH * ((double) tIdx / NUM_TICKS)));
      tickLabel[tIdx] = new Label();
      tickLabel[tIdx].addStyleName("tickLabel");
      labelPanel.add(tickLabel[tIdx], tickLeftPos, 0);
    }
   // labelPanel.addStyleName("durationPanel");
    
    // labelPanel.add(labelStart,0,0);
    // labelPanel.add(labelEnd,700,0);
    GridConstrainedDropController gridConstrainedDropController = new GridConstrainedDropController(
        destinationPanel, 1, TIMELINE_HEIGHT);
    // We can add style names.
    destinationPanel.setPixelSize(TIMELINE_WIDTH, TIMELINE_HEIGHT * 2);
    destinationPanel.addStyleName("destinationPanel");
    dragController = new PickupDragController(RootPanel.get(), true);
    dragController.registerDropController(gridConstrainedDropController);
    dragController.setBehaviorDragProxy(true);
    dragController.setBehaviorBoundaryPanelDrop(false);
    HorizontalPanel commandPanel = new HorizontalPanel();
    commandPanel.setPixelSize(TIMELINE_WIDTH, 75);
    Button submitButton = new Button("Done", new ClickListener()
    {
      public void onClick(Widget sender)
      {
        handleDone();
      }
    });
    commandPanel.add(scoreTextLabel);
    commandPanel.add(scoreLabel);
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
    generateTimeEventPanels();
  }

  /**
   * @param dragController
   */
  private void generateTimeEventPanels()
  {
    timeEventData = TimeEventData.retrieveTimeEventData();
    timeEventPanels = TimeEventPanel.createTimeEventPanels(timeEventData
        .getTimeEvents(), TIMELINE_HEIGHT);
    scalePixelsPerYear = (double) TIMELINE_WIDTH
        / (timeEventData.getEndYear() - timeEventData.getStartYear());
    for (int i = 0; i < timeEventPanels.length; i++)
    {
      timeEventPanels[i].makeDraggable(dragController);
      sourcePanel.add(timeEventPanels[i]);
    }
    for (int tIdx = 0; tIdx < NUM_TICKS; tIdx++)
    {
      int tickLeftPos = (int) Math
          .round((TIMELINE_WIDTH * ((double) tIdx / NUM_TICKS)));
      int tickYear = leftToYear(tickLeftPos);
      tickLabel[tIdx].setText(String.valueOf(tickYear));
    }
  }

  protected void handleDone()
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
    score += error;
    scoreLabel.setText(String.valueOf(score));
    Window.alert("Result: " + x);
    for (int i = 0; i < timeEventPanels.length; i++)
    {
      timeEventPanels[i].removeFromParent();
      timeEventPanels[i] = null;
    }
    
    generateTimeEventPanels();
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
