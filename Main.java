import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import static java.lang.Integer.parseInt;



public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int startRow,startCol,dirtCounter;
        String directions;

        //Parsing the file line by line into an arraylist
        ArrayList<String> file = readFile("input.txt");
        ArrayList<LinkedList<Integer>> dirtCoordinateList = new ArrayList<LinkedList<Integer>>();


        //Parsing the starting coordinates of the Roomba
        startRow = parseInt(String.valueOf(file.get(1).charAt(0)));
        startCol = parseInt(String.valueOf(file.get(1).charAt(1)));

        for (int i = 2; i < file.size()-1; i++){
            LinkedList<Integer> temp = new LinkedList<Integer>();
            int tempRow = parseInt(String.valueOf(file.get(i).charAt(0)));
            int tempCol = parseInt(String.valueOf(file.get(i).charAt(1)));
            temp.add(tempRow);
            temp.add(tempCol);
            dirtCoordinateList.add(temp);
        }


        //Parsing the file with the Roomba's direction instructions.
        directions = file.get(file.size()-1);


        int i = 0;
        dirtCounter = 0;
        if(checkDirt(dirtCoordinateList,startRow,startCol)){
            dirtCounter++;
        }

        while (i < directions.length()){
            if (directions.charAt(i) == 'N'){
                startCol = moveNorth(startCol);
                if(checkDirt(dirtCoordinateList,startRow,startCol)){
                    dirtCounter++;
                }
            }
            else if (directions.charAt(i) == 'E'){
                startRow = moveEast(startRow);
                if(checkDirt(dirtCoordinateList,startRow,startCol)){
                    dirtCounter++;
                }
            }
            else if (directions.charAt(i) == 'S'){
                startCol = moveSouth(startCol);
                if(checkDirt(dirtCoordinateList,startRow,startCol)){
                    dirtCounter++;
                }
            }
            else if (directions.charAt(i) == 'W'){
                startRow = moveWest(startRow);
                if(checkDirt(dirtCoordinateList,startRow,startCol)){
                    dirtCounter++;
                }
            }
            i++;
        }

        System.out.println(startRow + " " + startCol);
        System.out.println(dirtCounter);
    }

    private static int moveNorth(int col){
        col += 1;
        return col;
    }

    private static int moveSouth(int col){
        col -= 1;
        return col;
    }

    private static int moveEast(int row){
        row += 1;
        return row;
    }

    private static int moveWest(int row){
        row-=1;
        return row;
    }

    private static ArrayList<String> readFile(String filename) throws FileNotFoundException {
        ArrayList<String> file = new ArrayList<>();
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNext()){
            String input = scan.nextLine();
            input = input.replaceAll("\\s+","");
            file.add(input);
        }
        return file;
    }

    private static boolean checkDirt(ArrayList<LinkedList<Integer>> dirtCoordinateList, int checkRow, int checkCol){
      int index = 0;
      for(LinkedList<Integer> tempList : dirtCoordinateList){
        if(tempList.get(0) == checkRow){
          if(tempList.get(1) == checkCol){
            dirtCoordinateList.remove(index);
            return true;
          }
        }
        index++;
      }
      return false;
    }
}
