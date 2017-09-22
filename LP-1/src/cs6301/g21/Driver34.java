//package cs6301.g21;
//
//import java.io.IOException;
//import java.util.*;
//
////Driver class for Level 3
//public class Driver34 {
//
//    public static void main(String[] args) throws IOException {
//
//        //For LP1 Level 3
//        //ShuntingYard shuntingYard = new ShuntingYard();
//        int base = 10;
//        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
//        HashMap<String, Num> repo = new HashMap<String, Num>();
//
//        if(args.length > 0)
//            base = Integer.parseInt(args[0]);
//
//        readInput(hmap);
//        calculateL3(hmap, repo);
//
//        //output the internal representation of the last variable
//
//    }
//
//    //Reads the input into a hashMap works both for level 3 and level 4
//    public static void readInput(HashMap<Integer, String> hmap){
//
//        Scanner in = new Scanner(System.in);
//        int lineNo = 1;
//
//        while (in.hasNextLine()){
//
//            String line = in.nextLine();
//
//            if(line.equals(";"))
//                break;
//
//            String [] tokens = line.split(" ");
//            if(tokens[0].matches("\\d+"))
//                hmap.put(Integer.parseInt(tokens[0]), line);
//            else{
//                if(hmap.containsKey(lineNo))
//                    hmap.put(++lineNo, line);
//                else{
//                    hmap.put(lineNo, line);
//                    lineNo++;
//                }
//            }
//
//            System.out.println(line);
//        }
//
//        in.close();
//    }
//
//    //Stores Num values against the characters, given the arithmetic functions to be performed
//    //L3 has two cases, with post fix and without postfix, so can call Shunting yard directly (or not)
//    public static void calculateL3(HashMap<Integer, String> hmap, HashMap<String, Num> repo){
//
//        String [] tokens;
//        String temp;
//        ShuntingYard shuntingYard = new ShuntingYard();
//
//        //testing: print the hmap
//        for(Map.Entry<Integer, String> entry : hmap.entrySet()){
//            temp = entry.getValue();
//            tokens = temp.split(" ");
//            //Loop doesn't exist for Level 3
//            if(!temp.split(" ")[0].matches("\\d+")){
//            /*}else{*/
//                if(!repo.containsKey(tokens[0])) {
//                    if (tokens.length == 4){
//                        //either assign a variable a number or reference it
//                        if(tokens[2].contains("\\d+")) {
//                            repo.put(tokens[0], new Num(tokens[2]));
//                        }else if(tokens[2].contains("\\D+")){
//                            repo.put(tokens[0], repo.get(tokens[2]));
//                        }
//                    }
//                    else if(tokens.length == 6){
//                        //consider the following cases: addition, subtraction, multiplication, division, modulus, power
//                        //two cases to be considered: no postfix and with postfix
//                        if(tokens[3].contains("+")){
//                            //add()
//                            repo.put(tokens[0], Num.add(repo.get(tokens[2]), repo.get(tokens[4])));
//
//                        }else if(tokens[3].contains("-")){
//                            //subtract()
//                            repo.put(tokens[0], Num.subtract(repo.get(tokens[2]), repo.get(tokens[4])));
//
//                        }else if(tokens[3].contains("*")){
//                            //product()
//                            repo.put(tokens[0], Num.simpleMul(repo.get(tokens[2]), repo.get(tokens[4])));
//
//                        }else if(tokens[3].contains("/")){
//                            //divide()
//                            repo.put(tokens[0], Num.divide(repo.get(tokens[2]), repo.get(tokens[4])));
//
//                        }else if(tokens[3].contains("%")){
//                            //mod()
//                            repo.put(tokens[0], Num.mod(repo.get(tokens[2]), repo.get(tokens[4])));
//
//                        }else if(tokens[3].contains("^")){
//                            //power()
//                            //see if long() and Num() can both be called
//                            repo.put(tokens[0], Num.power(repo.get(tokens[2]), repo.get(tokens[4])));
//
//                        }//now go to the postfix case, convert it into queue and stack (to call shunting yard)
//                         else if(tokens[3].matches("\\D+")){
//                            //read the values from hash Map, convert into nums and pass to as Queue of String calculate of Shunting Yard algorithm
//                            Queue<String> stringQueue = new LinkedList<String>();
//
//                            String n1 = repo.get(tokens[2]).toString();
//                            String n2 = repo.get(tokens[3]).toString();
//                            String ops = tokens[4];
//                            stringQueue.add(n1);
//                            stringQueue.add(n2);
//                            stringQueue.add(ops);
//
//                            repo.put(tokens[0], shuntingYard.calculate(stringQueue));
//
//                        }
//                    }else if(tokens.length == 5){
//
//                        //check for any other not known now
//
//                    }
//
//                }else if(tokens.length == 2){
//                    if(tokens[1].contains("|")){
//                        //squareRoot()
//                        repo.put(tokens[0], Num.squareRoot(repo.get(tokens[0])));
//
//                    }else if(tokens[1].contains(";")){
//                        //prints the value of the variable
//                        System.out.println(repo.get(tokens[0]));
//                    }
//
//                }
//
//            }
//
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }
//
//
//
//
//    }
//}
