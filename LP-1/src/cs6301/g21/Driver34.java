package cs6301.g21;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Driver class for Level 3
public class Driver34 {

    public static void main(String[] args) throws IOException {

        //For LP1 Level 3
        //ShuntingYard shuntingYard = new ShuntingYard();
        int base = 10;
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        HashMap<String, Num> repo = new HashMap<String, Num>();

        if(args.length > 0)
            base = Integer.parseInt(args[0]);

        readInput(hmap);
        calculateL3(hmap, repo);

        //output the internal representation of the last variable

    }

    //Reads the input into a hashMap works both for level 3 and level 4
    public static void readInput(HashMap<Integer, String> hmap){

        Scanner in = new Scanner(System.in);
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

    //Stores Num values against the characters, given the arithmetic functions to be performed
    public static void calculateL3(HashMap<Integer, String> hmap, HashMap<String, Num> repo){

        String [] tokens;
        String temp;

        //testing: print the hmap
        for(Map.Entry<Integer, String> entry : hmap.entrySet()){
            temp = entry.getValue();
            tokens = temp.split(" ");
            //doesn't exist for Level 3
            if(!temp.split(" ")[0].matches("\\d+")){
                //Stuff for the loop

            /*}else{*/
                if(!repo.containsKey(tokens[0])) {
                    if (tokens.length == 4){
                        //either assign a variable a number or reference it
                        if(tokens[2].contains("\\d+")) {
                            repo.put(tokens[0], new Num(tokens[2]));
                        }else if(tokens[2].contains("\\D+")){
                            repo.put(tokens[0], repo.get(tokens[2]));
                        }
                    }
                    else if(tokens.length == 6){
                        //consider the following cases: addition, subtraction, multiplication, division, modulus, power
                        if(tokens[4].contains("+")){
                            //add()
                            if(tokens[2].contains("\\d+") && tokens[3].contains("\\d+")){
                                repo.put(tokens[0], Num.add(new Num(tokens[2]), new Num(tokens[3])));
                            }else if(tokens[2].contains("\\d+") && tokens[3].contains("\\D+")){
                                repo.put(tokens[0], Num.add(new Num(tokens[2]), repo.get(tokens[3])));
                            }else if(tokens[2].contains("\\D+") && tokens[3].contains("\\d+")){
                                repo.put(tokens[0], Num.add(repo.get(tokens[2]), new Num(tokens[3])));
                            }else{
                                repo.put(tokens[0], Num.add(repo.get(tokens[2]), repo.get(tokens[3])));
                            }

                        }else if(tokens[4].contains("-")){

                            //subtract()
                        }else if(tokens[4].contains("*")){

                            //product()
                        }else if(tokens[4].contains("/")){

                            //divide()
                        }else if(tokens[4].contains("%")){

                            //mod()
                        }else if(tokens[4].contains("^")){

                            //power() case
                            //see if long() and Num() can both be called
                        }
                    }else if(tokens.length == 5){

                        //check for any other not known now

                    }

                }else if(tokens.length == 2){
                    if(tokens[1].contains("|")){

                        //squareRoot()
                    }else if(tokens[1].contains(";")){
                        System.out.println(repo.get(tokens[0])); //prints the value of the variable
                    }

                }

            }

            System.out.println(entry.getKey() + "/" + entry.getValue());
        }




    }
}
