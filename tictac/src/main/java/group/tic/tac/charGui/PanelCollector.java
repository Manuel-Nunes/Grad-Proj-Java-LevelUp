package group.tic.tac.charGui;

import java.util.ArrayList;
import java.util.List;

public class PanelCollector {
  public List<Panel> lstDeployed = new ArrayList<Panel>();
  public List<Panel> lstAllPanels = new ArrayList<Panel>();

  public boolean Place(Panel Target){
    boolean isBlocked = false;
    Panel BlockedBy = null;
    for (Panel thing: lstDeployed){
      if (Gui.Intersects(thing, Target)){
        isBlocked = true;
        break;
      }
    }
    return isBlocked;
  }
}
