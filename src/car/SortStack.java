package car;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;;

public class SortStack {
	public static Stack<Integer> sortstack(Stack<Integer> input) {
		Stack<Integer> tmpStack = new Stack<Integer>();
		while (!input.isEmpty()) { 
			int tmp = input.pop(); 
			while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
				input.push(tmpStack.pop());
			}
			tmpStack.push(tmp);
		}
		return tmpStack;
	}

	public static void main(String args[]) throws FileNotFoundException {
		Stack<Integer> input = new Stack<Integer>();
		File file = new File("C:\\car.txt");// 讀檔
		Scanner input1 = new Scanner(file);// 設定從檔案輸入
		while (input1.hasNext()) {// 讀檔 把資料放入陣列
			input.add(input1.nextInt());
		}
		Stack<Integer> tmpStack = sortstack(input);
		System.out.println("Sorted numbers are:");

		while (!tmpStack.empty()) {
			System.out.print(tmpStack.pop() + " ");
		}
		
	}
}