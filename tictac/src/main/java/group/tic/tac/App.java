package group.tic.tac;

import group.tic.tac.charGui.Gui;
import group.tic.tac.charGui.Panel;
import group.tic.tac.utils.Utils;

public class App
{
    public static void main( String[] args )throws Exception 
    {
        Utils.clearConsole();
        Panel mPanel = new Panel("This is my input String\nShould be two lines");
        mPanel.Paddings = new int[] {0,0,0,0};
        mPanel.Generate(true);
        Gui.PrintPanel(mPanel);
    }
}
