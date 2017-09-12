package cs6301.g21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sample {
    public static void main(String[] args)throws FileNotFoundException {
        if(args.length > 0){
            Scanner sf = new Scanner(new File(args[0]));
            Graph graph = Graph.readGraph(sf);

        }
        else
            System.out.println("usage: java cs6301.g21.TreeDiameter <path to graph file>");
    }
}
