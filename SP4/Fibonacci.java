package cs6301.g21;

import java.math.BigInteger;

/**
 * Find the nth Fibonacci number (0,1,1,2..) using two techniques and 
 * compare their running time.
 * 
 * @author Shreya Vishwanath Rao, Abhishek Jagwani, Vibha Belavadi, Umang Shah
 * @version 1.0: 2017/09/22
 *
 */

public class Fibonacci {

	/**
	 * Compute the nth fibonacci sequence in linear time.
	 * 
	 * @param n	: the position number in fibonacci sequence
	 * @return BigInteger  fibonacci number at the nth position
	 */
	static BigInteger linearFibonacci(int n) {
		if(n==0)
			return new BigInteger("0");
		if(n==1){
			return new BigInteger("1");
		}
		
		BigInteger fib[] = new BigInteger[n+1];
		fib[0]=new BigInteger("0");
		fib[1]=new BigInteger("1");
		for(int i=2;i<=n;i++){
			fib[i]=fib[i-1].add(fib[i-2]);
		}
		return fib[n];
	}
	
	/**
	 * Compute the nth fibonacci sequence in log n time.
	 *  
	 * @param n : the position number in fibonacci sequence
	 * @return BigInteger  fibonacci number at the nth position
	 */
	static BigInteger logFibonacci(int n) {
		
		if(n==0)
			return new BigInteger("0");
		if(n==1)
			return new BigInteger("1");
		if(n==2)
			return new BigInteger("1");
		
		/*IMatrix
		 * 1 1
		 * 1 0
		 */
		BigInteger[][] iMatrix=new BigInteger[2][2];
		iMatrix[0][0]=new BigInteger("1");
		iMatrix[0][1]=new BigInteger("1");
		iMatrix[1][0]=new BigInteger("1");
		iMatrix[1][1]=new BigInteger("0");
		
		/*pMatrix
		 * 1
		 * 0
		 */
		BigInteger[][] pMatrix=new BigInteger[2][1];
		pMatrix[0][0]=new BigInteger("1");
		pMatrix[1][0]=new BigInteger("0");
		
		BigInteger prod[][]= power(iMatrix,n-2);
		
		BigInteger fib00=prod[0][0].multiply(pMatrix[0][0]);
		BigInteger fib01=prod[0][1].multiply(pMatrix[1][0]);
		BigInteger fib10=prod[1][0].multiply(pMatrix[0][0]);
		BigInteger fib11=prod[1][1].multiply(pMatrix[1][0]);
		
		BigInteger fib1=fib00.add(fib01);
		BigInteger fib2=fib10.add(fib11);

		return fib1.add(fib2);
		
	}
	
	/**
	 * Calculates the iMatrix ^ n in a recursive manner
	 * @param iMatrix : BigInteger base matrix
	 * @param n : integer exponential
	 * @return BigInteger matrix which is the result of iMatrix^n
	 */
	static BigInteger[][] power(BigInteger[][] iMatrix, int n){
		if(n==0){
			BigInteger[][] zero=new BigInteger[2][2];
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++)
					zero[i][j]=new BigInteger("1");
			}
			return zero;
		}
		if(n==1){
			return iMatrix;
		}
		BigInteger[][] half=power(iMatrix,n/2);
		
		if(n%2==0)
			return product(half,half);
		else
			return product(product(half,half),iMatrix);
		
		}
	
	/**
	 * computes the product of two matrices 
	 * @param first :first matrix 
	 * @param second : second matrix
	 * @return BigInteger matrix that contains the product of first and second
	 */
	static BigInteger[][] product(BigInteger[][] first, BigInteger[][] second){
			
		BigInteger[][] result= new BigInteger[2][2];

		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
				result[i][j]= new BigInteger("0");
			for(int k=0;k<2;k++){
					result[i][j]=(first[i][k].multiply(second[k][j])).add(result[i][j]);
				}
			}
		}
		
		return result;
			
	}
		
	/**
	 * Main function. Computes the nth fibonacci number using two sequences
	 * and measures the time taken by each.
	 *  
	 * @param args :Command Line argument
	 */
	public static void main(String[] args){
		int n=100000;
		
		Timer t1= new Timer();
		BigInteger result1= linearFibonacci(n);
		t1.end();
		System.out.println("Linear Fibonacci "+result1);
		System.out.println(t1.toString()+"\n");
		
		t1.start();
		BigInteger result2= logFibonacci(n);
		t1.end();
		System.out.println("Logrithmic Fibonacci " + result2);
		System.out.println(t1.toString());
			
	}

}
