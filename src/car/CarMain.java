package car;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CarMain {

	public static void main(String[] args) throws FileNotFoundException {
		java.io.File file = new java.io.File("C:\\car.TXT");
		Scanner input = new Scanner(file);	
		int a1[] = new int[10];
		int a[] = new int[10];
		for (int i = 1; i <= 9; i++) {
			a[i] = input.nextInt();
			a1[i] = a[10-i];
			System.out.print(a[i]);
		}
		for (int i = 1; i <= 9; i++) 
			a1[i] = a[10-i];
			
		System.out.println();
		Stack_Car s = new Stack_Car(a1, 9, 3);
		Stack_Car.railroad();

	}

}
