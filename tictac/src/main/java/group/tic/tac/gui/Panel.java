package group.tic.tac.gui;
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
  public int[] Margins = new int[]{0,0,0,0};
  public List<String> Display;
  public List<String> Raw;
  public int InnerWidth;
  public int Widht,Height;
  public PlacePref PlacePrefrence = PlacePref.Right;

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
    // this.InnerWidth = Utils.longestWidth(Display);
    this.InnerWidth = this.Display.get(0).length();

    if (GenFrame)
      Gui.GenerateFrame(this);

    this.Widht = this.Display.get(0).length();
    Gui.AddMargins(this);

    this.Height = this.Display.size();
    this.Widht = this.Display.get(0).length();
  }

  public int getRight(){
    return this.Location.Column + this.Widht;
  }

  public int getBottom(){
    return this.Location.Row + this.Height;
  }

  public String getLineAt(int index){
    if (index >= this.Location.Row && index < this.getBottom())
      return this.Display.get(index-this.Location.Row);
    // return Utils.genFiller(this.Widht,' ');
    return "";
  }
}
