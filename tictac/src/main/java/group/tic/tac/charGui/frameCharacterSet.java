package group.tic.tac.charGui;

public class frameCharacterSet {
  public char LeftTopCorner = '╔';
  public char RightTopCorner = '╗';
  public char LeftBottomCorner = '╚';
  public char RightBottomCorner = '╝';
  public char HorizontalLine = '═';
  public char VerticalLine = '║';
  public char BodyChar = ' ';

  public frameCharacterSet(){}

  public frameCharacterSet(char[] cornerSet){
    this.LeftTopCorner = cornerSet[0];
    this.RightTopCorner = cornerSet[1];
    this.LeftBottomCorner = cornerSet[2];
    this.RightBottomCorner = cornerSet[3];
  }

  public frameCharacterSet(char horizontalChar, char verticalChar){
    this.HorizontalLine = horizontalChar;
    this.VerticalLine = verticalChar;
  }

  public frameCharacterSet(char leftTop,char rightTop, char leftBot, char rightBot,char horiChar, char vertiChar){
    this(new char[] {leftTop, rightTop, leftBot, rightBot});
    this.HorizontalLine = horiChar;
    this.VerticalLine = vertiChar;
  }
}
