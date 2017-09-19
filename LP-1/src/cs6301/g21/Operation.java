package cs6301.g21;


public class Operation {
    private static int JUMP = 3;
    private static int POST = 2;
    private static int VAR = 1;
    private static int PRINT = 0;
    private int type;
    private int jumps;
    private Num value;
    private String expression;

    public Operation(){
        type = -1;
        jumps = 0;
        value = null;
    }
    public void setType(int type){
        this.type = type;
    }
    public Num execute(){
        switch(type){
            case 3://JUMP
                break;
            case 2://POSTFIX
                break;
            case 1://VAR ASSIGNMENT
                break;
            case 0://PRINT VAR
                System.out.print("");
                //printList
            default:

        }
        return new Num();
    }
}
