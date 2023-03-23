package group.tic.tac.gui;

import java.util.ArrayList;
import java.util.List;

import group.tic.tac.gui.Panel.PlacePref;
import group.tic.tac.utils.Utils;

public class PanelCollector {
  public List<Panel> lstDeployed = new ArrayList<Panel>();

  public int MaxHeight,MaxWidth;

  public static final int DefaultHeight = 30, DefaultWidth = 120;

  public PanelCollector(){
    this(PanelCollector.DefaultWidth, PanelCollector.DefaultHeight);
  }

  public PanelCollector(int MaxWidth, int MaxHeight){
    this.MaxHeight = MaxHeight;
    this.MaxWidth = MaxWidth;
  }

  public boolean Place(Panel Target){
    Panel blockedBy = intersectsWith(lstDeployed, Target);
    while (blockedBy != null){
      if (Target.PlacePrefrence == PlacePref.Right){
        Target.Location = new Cords(Target.Location.Row, blockedBy.getRight()+1);
      }
      else{
        Target.Location = new Cords(blockedBy.getBottom()+1, Target.Location.Column);
      }
      blockedBy = intersectsWith(lstDeployed, Target);
    }

    if (Target.Location.Row + Target.Height >= this.MaxHeight || Target.Location.Column+ Target.Widht >= this.MaxWidth)
      return false;
    this.lstDeployed.add(Target);
    return true;
  }

  public Panel intersectsWith(List<Panel> lstPlaced, Panel Target){
    Panel Out = null;
    for (Panel panel : lstPlaced) {
      if (Gui.Intersects(Target, panel))
        Out = panel;
    }

    return Out;
  }

  public List<String> sortPanels(){
    List<String> sortedPanels = new ArrayList<String>();

    return sortedPanels;
  }

  public int[] getMaxTopLeft(){
    int top = 0,left = 0;
    for (Panel p: lstDeployed){
      if (p.getRight()> left)
        left = p.getRight();
      if (p.getBottom() > top )
        top =  p.getBottom();
    }
    return new int[] {top,left};
  }

  public List<String> renderToString(){
    List<String> lstOut = new ArrayList<String>();
    int Row = 0;
    int[] res = this.getMaxTopLeft();
    while (Row <= res[0]){
      String sLine ="";
      for (Panel p: lstDeployed){
        // System.out.println(String.format("%b %b is %b", (p.Location.Column > 0), (sLine.equals("")), (p.Location.Column > 0 && sLine == "")));
        if (p.Location.Column > 0 && sLine.equals("")){
          sLine += Utils.genFiller(p.Location.Column-1, ' ');
        }
        sLine += p.getLineAt(Row);
      }
      lstOut.add(sLine);
      Row++;
    }
    return lstOut;
  }
}
