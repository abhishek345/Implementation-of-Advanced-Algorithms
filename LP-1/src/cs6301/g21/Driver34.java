package cs6301.g21;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//Driver class for Level 3
public class Driver34 {

    public static void main(String[] args) throws IOException {

        //For LP3
        //ShuntingYard shuntingYard = new ShuntingYard();
        int base = 10;
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        if(args.length > 0)
            base = Integer.parseInt(args[0]);

        readInput(hmap);

    }

    //Reads the input into a hashMap works both for level 3 and level 4
    public static void readInput(HashMap<Integer, String> hmap){

        Scanner in =new Scanner(System.in);
        int lineNo = 1;

        while (in.hasNextLine()){

            String line = in.nextLine();

            if(line.equals(";"))
                break;

            String [] tokens = line.split(" ");
            if(tokens[0].matches("\\d+"))
                hmap.put(Integer.parseInt(tokens[0]), line);
            else{
                if(hmap.containsKey(lineNo))
                    hmap.put(++lineNo, line);
                else{
                    hmap.put(lineNo, line);
                    lineNo++;
                }
            }


            System.out.println(line);

        }

        in.close();
    }

    //Stores Num values against the characters
    public static void storeVals(HashMap<String, Num> repo){


    }
}
