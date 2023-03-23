package group.tic.tac.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Utils {
  static Scanner scan;

  static {
    scan = new Scanner(System.in);
  }

  public static void clearConsole(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void initStringArr(String[] Arr){
    for (int i = 0; i < Arr.length;i++){
      Arr[i] = "";
    }
  }

  public static int longestWidth(List<String> Arrs){
    int max = 0;
    for (int i = 0; i < Arrs.size();i++){
      if (Arrs.get(i).length() > max)
        max = Arrs.get(i).length();
    }
    return max;
  }

  public static String genFiller(int size, char val){
    String Out = "";
    for (int i = 0; i < size;i++)
      Out += val;

    return Out;
  }

  public static <T> List<T> arrToArray(T[] Arr){
    List<T> lstOut = new ArrayList<T>();
    for (T obj: Arr)
      lstOut.add(obj);
    return lstOut;
  }

  public static <T> List<T> deepCopy(List<T> lst){
    List<T> Temp = new ArrayList<T>();
    for (T t :lst) {
      Temp.add(t);
    }
    return Temp;
  }

  public static void printArray(String[] Arr){
    for (String s: Arr){
      System.out.println(s);
    }
  }

  public static void printArray(List<String> Arr) {
    for (String s : Arr) {
      System.out.println(s);
    }
  }

  public static int getUserInt(String Message){
    System.out.print(Message);
    return getUserInt();
  }

  public static int getUserInt(){
    int temp = -2;
    try{
      temp = scan.nextInt();
      return temp;
    }catch(InputMismatchException numException)
    {
      System.out.println("Please provide a number");
      scan.next();
      return getUserInt();
    }

  }

  public static void waitForInput(String Message) {
    System.out.print(Message);
    waitForInput();
  }

  public static void waitForInput(){
    scan.nextLine();
  }

  public static String getIP(){
    String ip;
    try {
      URL whatismyip = new URL("http://checkip.amazonaws.com");
      BufferedReader in = new BufferedReader(new InputStreamReader(
              whatismyip.openStream()));

      ip = in.readLine(); // you get the IP as a String
    } catch (Exception e) {
      // TODO: handle exception
      ip = "1.2.3.4";
    }

    return ip;
  }

}
