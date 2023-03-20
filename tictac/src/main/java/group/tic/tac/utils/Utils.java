package group.tic.tac.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
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
}
