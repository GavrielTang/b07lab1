import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class Driver {
	public static void main(String [] args) {
		//test 1
		// Polynomial p = new Polynomial();
		// System.out.println(p.evaluate(3));

		//test 2
		// double [] c1 = {3,6};
		// int[] e1 = {2,1};
		// Polynomial p1 = new Polynomial(c1, e1);
		// double [] c2 = {-2,-9};
		// int[] e2 = {1,4};
		// Polynomial p2 = new Polynomial(c2, e2);
		// Polynomial s = p.add(p2);
		// printPoly(p);
		// printPoly(p2);
		// printPoly(s);

		// System.out.println("s(0.1) = " + s.evaluate(0.1));
		// if(s.hasRoot(1))
		// 	System.out.println("1 is a root of s");
		// else
		// 	System.out.println("1 is not a root of s");
		
		//*Multiply Test */
		// double[] c1 = {1,-2,1};
		// int[] e1 = {4,2,0};
		// double[] c2 = {1,-1};
		// int[] e2 = {2,0};
		// Polynomial p1 = new Polynomial(c1, e1);
		// Polynomial p2 = new Polynomial(c2, e2);
		// Polynomial s = p1.multiply(p2);
		// //System.out.println(s.exponents.length);
		// System.out.print("S: ");
		// System.out.println(s.getString());

		// //write and read file test
		// double[] c1 = {5,-3,7};
		// int[] e1 = {0,2,8};
		// Polynomial p1 = new Polynomial(c1, e1);
		// p1.saveToFile("text.txt");

		// Scanner s = null;

		// try{
        //     s = new Scanner(new File("text.txt"));
        // } catch (FileNotFoundException e){	
        //     System.out.println("File Not Found");
        // }

        // System.out.println(s.nextLine());

		Polynomial p = new Polynomial(new File("text.txt"));
		System.out.println("DElta: " + p.getString());
        

	}

	public static void printPoly(Polynomial p){
		for(int i = 0; i < p.exponents.length; i++){
			System.out.print("Expo: " + p.exponents[i] + ", Coeff: " + p.coefficients[i] + "; ");
		}
		System.out.println();
	}
}