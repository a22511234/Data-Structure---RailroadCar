package car;

import java.util.*;
import java.util.Scanner;
import java.util.Stack;

public class RailroadWithStacks {
	// data members
	private static Stack[] track; // array of holding tracks
	private static int numberOfCars; // 車廂數（9）
	private static int numberOfTracks; // 避車道數（3）
	private static int smallestCar; // 在避車道中编號最小的車廂 smallest car in any holding track
	private static int itsTrack; // 停靠著最小编號車廂的避車道 holding track with car smallestCar
	private static int []ans=new int [10];
	private static int count=1;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		java.io.File file = new java.io.File("C:\\car2.TXT");
		Scanner input = new Scanner(file, "Ms950");
		int a1[]=new int [10];
		while (input.hasNext()) {
			int a[] = new int[10];
			a[0] = 0;
			for (int i = 1; i <= 9; i++) {
				a[i] = input.nextInt();
				System.out.print(a[i]);
			}
			for (int i = 1; i <= 9; i++) {
				a1[i] = a[10-i];
							}
			railroad(a1, 9, 3);
			System.out.println();			
		}
		
		input.close();
	}

	/** output the smallest car from the holding tracks */ // 將编號最小的車廂從避車道移到出軌道
	private static void outputFromHoldingTrack() {
		// remove smallestCar from itsTrack
		// 從itsTrack中移除編號最小的車廂		
		track[itsTrack].pop();
		System.out.println("Move car " + smallestCar + " from holding " + "track " + itsTrack + " to output track");
		ans[count]=smallestCar;
		count++;
		// find new smallestCar and itsTrack by checking top of all stacks
		// 檢查所有避車道的頂部，尋找編號最小的車廂和他所屬的itsTrack避車道
		smallestCar = numberOfCars + 2; // 9+2=11
		for (int i = 1; i <= numberOfTracks; i++)
			if (!track[i].empty() && ((Integer) track[i].peek()).intValue() < smallestCar) {
				smallestCar = ((Integer) track[i].peek()).intValue();
				itsTrack = i;
			}
	}

	/**
	 * put car c into a holding track @return false iff there is no feasible holding
	 * track for this car
	 */
	// 將車廂c移到一個避車道。return false，代表没有可用的避車道
	private static boolean putInHoldingTrack(int c) {
		// 為車廂c尋找最合適的避車道
		int bestTrack = 0, // 目前最合適的避車道
			bestTop = numberOfCars + 1; // 取bestTrack中的頂部車廂

		// 掃描避車道
		for (int i = 1; i <= numberOfTracks; i++)
			if (!track[i].empty()) {
				// 如果避車道i不是空的
				int topCar = ((Integer) track[i].peek()).intValue();
				// 大中最小
				if (c < topCar && topCar < bestTop) {
					bestTop = topCar;
					bestTrack = i;
				}
			} else {
				// 如果避車道i是空的
				if (bestTrack == 0)
					bestTrack = i;
			}

		if (bestTrack == 0) {
			System.out.print("Error,the tracks are fail");
			return false;
			// 没有可用的避車道
		}
		// 把車廂c移到避車道中的bestTrack
		track[bestTrack].push(new Integer(c));
		System.out.println("Move car " + c + " from input track " + "to holding track " + bestTrack);

		// update smallestCar and itsTrack if needed
		if (c < smallestCar) {
			smallestCar = c;
			itsTrack = bestTrack;
		}

		return true;
	}

	/**
	 * rearrange railroad cars beginning with the initial order
	 * inputOrder[1:theNumberOfCars] @return true if successful, false if
	 * impossible.
	 */
	// 從初始順序開始重排車廂，如果重排成功，return true，否則 return false
	public static boolean railroad(int[] inputOrder, int theNumberOfCars, int theNumberOfTracks) {
		numberOfCars = theNumberOfCars;
		numberOfTracks = theNumberOfTracks;

		// 創一個新的避車道陣列
		track = new Stack[numberOfTracks + 1];//??
		for (int i = 1; i <= numberOfTracks; i++)
			track[i] = new Stack();//??

		int nextCarToOutput = 1;
		smallestCar = numberOfCars + 1; // 緩衝車道中無車廂//??

		// 重排車廂
		for (int i = 1; i <= numberOfCars; i++)
			if (inputOrder[i] == nextCarToOutput) {// send car inputOrder[i] straight out
				ans[count]=nextCarToOutput;
				count++;
				System.out.println("Move car " + inputOrder[i] + " from input track to output track");
				nextCarToOutput++;

				// 將車廂從避車道output
				while (smallestCar == nextCarToOutput) {
					outputFromHoldingTrack();
					nextCarToOutput++;
				}
			} else
			// 將車廂移到另一個避車道
			if (!putInHoldingTrack(inputOrder[i]))
				return false;

		for(int i=9;i>=1;i--) {
			System.out.print(ans[i]+" ");
		}
		return true;
	}

	/** test program */

}