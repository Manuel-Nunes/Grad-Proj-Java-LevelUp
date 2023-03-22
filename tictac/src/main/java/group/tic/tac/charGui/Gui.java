package group.tic.tac.charGui;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import group.tic.tac.utils.Utils;

public class Gui {

  public static void AddPadding(Panel Target){
    for (int i = 0; i < Target.Display.size();i++){
      int LeftPaddingSize = Target.Paddings[3];
      int RightPaddingSize = Target.Paddings[1];
      String LeftPadding = Utils.genFiller(LeftPaddingSize, Target.charSet.BodyChar);
      String RightPadding = Utils.genFiller(RightPaddingSize, Target.charSet.BodyChar);
      Target.Display.set(i, String.format("%s%s%s",LeftPadding,Target.Raw.get(i),RightPadding));
    }

    for (int i = 0; i < Target.Paddings[0];i++){
      Target.Display.add(0,"");
    }

    for (int i = 0; i < Target.Paddings[2];i++){
      Target.Display.add(Target.Display.size(),"");
    }
  }

  public static void AddMargins(Panel Target) {
    for (int i = 0; i < Target.Display.size(); i++) {
      int LeftMarginsSize = Target.Margins[3];
      int RightMarginsSize = Target.Margins[1];
      String LeftMargins = Utils.genFiller(LeftMarginsSize, ' ');
      String RightMargins= Utils.genFiller(RightMarginsSize, ' ');
      Target.Display.set(i, String.format("%s%s%s", LeftMargins, Target.Display.get(i), RightMargins));
    }

    int newWdith = Target.Widht + Target.Margins[1] + Target.Margins[3];
    for (int i = 0; i < Target.Margins[0]; i++) {
      Target.Display.add(0, Utils.genFiller(newWdith, ' '));
    }

    for (int i = 0; i < Target.Margins[2]; i++) {
      Target.Display.add( Utils.genFiller(newWdith, ' '));
    }
  }

  public static String genHorizontalBorder(int length, char body, char cornerLeft, char cornerRight){
    String Out = cornerLeft+"";
    for (int i = 0; i < length;i++)
      Out += body;
    Out += cornerRight;
    return Out;
  }

  public static void equalLength(Panel Target){
    int Width = Utils.longestWidth(Target.Display);
    for (int i = 0; i < Target.Display.size();i++){
      int LineSize = Target.Display.get(i).length();
      Target.Display.set(i, Target.Display.get(i)+ Utils.genFiller(Width-LineSize, Target.charSet.BodyChar));
    }
  }

  public static void GenerateFrame(Panel Target){
    Target.Display.add(
      0,
      Gui.genHorizontalBorder(
        Target.InnerWidth,
        Target.charSet.HorizontalLine,
        Target.charSet.LeftTopCorner,
        Target.charSet.RightTopCorner
      )
    );

    for (int i = 1; i < Target.Display.size();i++){
      Target.Display.set(i,
        String.format("%c%s%c",
          Target.charSet.VerticalLine,
          Target.Display.get(i),
          Target.charSet.VerticalLine
        )
      );
    }

    Target.Display.add(
      Gui.genHorizontalBorder(
        Target.InnerWidth,
        Target.charSet.HorizontalLine,
        Target.charSet.LeftBottomCorner,
        Target.charSet.RightBottomCorner
      )
    );
  }

  public static void PrintPanel(Panel Target){
    BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    String Out = "";
    for (String Line: Target.Display){
      Out += Line +'\n';
    }
    try {
      BW.write(Out, 0, Out.length());
      BW.close();
    } catch (Exception e) {
      System.out.println(e);
      // TODO: handle exception
    }
  }

  public static boolean CheckVerticalIntersect(Panel Target, Panel Placement){
    return ( (Target.Location.Row <= Placement.Location.Row) && (Placement.Location.Row <= Target.Location.Row + Target.Height ) );
  }

  public static boolean CheckHorizontalIntersect(Panel Target, Panel Placement){
    return ( (Target.Location.Column <= Placement.Location.Column) && (Placement.Location.Column <= Target.Location.Column + Target.Widht ) );
  }

  public static boolean Intersects(Panel Target, Panel Placement){
    boolean AinB = CheckHorizontalIntersect(Target, Placement) && CheckVerticalIntersect(Target, Placement);
    boolean BinA = CheckHorizontalIntersect(Placement,Target) && CheckVerticalIntersect(Placement,Target);
    return AinB || BinA;
  }
}
