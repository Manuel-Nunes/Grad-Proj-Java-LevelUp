package group.tic.tac.charGui;
import group.tic.tac.utils.Utils;

import java.util.List;

public class Panel {
  public static enum PlacePref{
    Right,
    Bottom
  }

  public Cords Location = new Cords();

  public frameCharacterSet charSet = new frameCharacterSet();
  public int[] Paddings = new int[]{0,0,0,0};
  public List<String> Display;
  public List<String> Raw;
  public int InnerWidth;
  public int Widht,Height;

  public Panel(String input){
    this(input.split("\n")); 
  }

  public Panel(String[] Lines){
    this.Raw = Utils.arrToArray(Lines);
    this.Display = Utils.deepCopy(this.Raw);
  }
  
  public void Generate(boolean GenFrame){
    Gui.AddPadding(this);
    Gui.equalLength(this);
    this.InnerWidth = Utils.longestWidth(Display);

    if (GenFrame)
      Gui.GenerateFrame(this);

    this.Height = this.Display.size();
    this.Widht = this.Display.get(0).length();
  }
}
