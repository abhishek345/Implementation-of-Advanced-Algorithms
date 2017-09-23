// Sample code for Level 2 driver for lp1
package cs6301.g21;

public class LP1L2 {
    public static void main(String[] args) {
	Num x = new Num(999);
	Num y = new Num("8");
	Num z = Num.add(x, y);
	System.out.println(z);
	Num a = Num.power(x, y);
	System.out.println(a);
	z.printList();
    }
}
