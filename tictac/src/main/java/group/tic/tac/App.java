package group.tic.tac;

import group.tic.tac.charGui.Cords;
import group.tic.tac.charGui.Gui;
import group.tic.tac.charGui.Panel;
import group.tic.tac.charGui.PanelCollector;
import group.tic.tac.utils.Utils;

public class App
{
    public static void main( String[] args )
    {
        Utils.clearConsole();
        PanelCollector PC = new PanelCollector();

        Panel Main = new Panel("This is my main Panel\nI hope this works");
        Main.Location = new Cords(0,0);
        Main.Paddings = new int[]{2,2,2,2};
        Main.Generate(true);
        PC.Place(Main);

        Panel Second = new Panel("This is my main Panel\nI hope this works2");
        Second.Location = new Cords(0, 0);
        Second.Paddings = new int[] { 2, 2, 2, 2 };
        Second.Generate(true);
        PC.Place(Second);

        Panel Third = new Panel("This is my main Panel\nI hope this works3");
        Third.Location = new Cords(0, 0);
        Third.Paddings = new int[] { 2, 2, 2, 2 };
        Third.Generate(true);
        Third.PlacePrefrence = Panel.PlacePref.Bottom;
        PC.Place(Third);

        Utils.printArray(PC.renderToString());
    }
}
