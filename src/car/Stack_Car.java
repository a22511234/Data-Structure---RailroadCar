package car;

public class Stack_Car {
	private static int[][] track=new int [10][4]; // array of holding tracks
	private static int[] inputOrder=new int[10];
	private static int numberOfCars; // 車廂數（9）
	private static int numberOfTracks; // 避車道數（3）
	private static int smallestCar; // 在避車道中编號最小的車廂 smallest car in any holding track
	private static int itsTrack; // 停靠著最小编號車廂的避車道 holding track with car smallestCar
	private static int []ans=new int [10];
	private static int count=1;
	private static int x1=0;
	private static int x2=0;
	private static int x3=0;
	public Stack_Car(int[] inputOrders, int theNumberOfCars, int theNumberOfTracks) { //紀錄一開始起始位置
		inputOrder=inputOrders;
		numberOfCars = theNumberOfCars;
		numberOfTracks = theNumberOfTracks;
	}
	public static boolean railroad() {
		int nextCarToOutput = 1;
		smallestCar = numberOfCars + 1; 
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
	private static boolean putInHoldingTrack(int c) {// 為車廂c尋找最合適的避車道		
		int bestTrack = 0, // 目前最合適的避車道
			bestTop = numberOfCars + 1; // 取bestTrack中的頂部車廂
		for (int i = 1; i <= numberOfTracks; i++)
			if (!empty(i)) {// 掃描避車道
				// 如果避車道i不是空的
				int topCar =peek(i);				
				if (c < topCar && topCar < bestTop) {// 大中最小
					bestTop = topCar;
					bestTrack = i;
				}
			} else {				
				if (bestTrack == 0)// 如果避車道i是空的
					bestTrack = i;
			}

		if (bestTrack == 0) {// 没有可用的避車道
			System.out.print("Error,the tracks are fail");
			return false;			
		}		
		push(bestTrack,c);// 把車廂c移到避車道中的bestTrack
		System.out.println("Move car " + c + " from input track " + "to holding track " + bestTrack);		
		if (c < smallestCar) {// update smallestCar and itsTrack if needed
			smallestCar = c;
			itsTrack = bestTrack;
		}

		return true;
	}
	private static void outputFromHoldingTrack() {// remove smallestCar from itsTrack					
		pop(itsTrack);// 從itsTrack中移除編號最小的車廂	
		System.out.println("Move car " + smallestCar + " from holding " + "track " + itsTrack + " to output track");
		ans[count]=smallestCar;
		count++;		
		smallestCar = numberOfCars + 2; // 9+2=11
		for (int i = 1; i <= numberOfTracks; i++)// 檢查所有避車道的頂部，尋找編號最小的車廂和他所屬的itsTrack避車道
			if (!empty(i) && peek(i) < smallestCar) {
				smallestCar =peek(i);
				itsTrack = i;
		}
	}
	public static void push(int inputtrack, int input) { // 放置新資料
		if(inputtrack==1) {
			track[x1][inputtrack]=input;
			x1++;
		}
		else if(inputtrack==2) {
			track[x2][inputtrack]=input;
			x2++;
		}
		else if(inputtrack==3) {
			track[x3][inputtrack]=input;
			x3++;
		}
			
	}
	public static int peek(int checktrack) { // 抓取最後的X值
		if(checktrack==1) {
			if(x1==0)
				return track[x1][1];
			return track[x1-1][1];
		}
		else if(checktrack==2) {
			if(x2==0)
				return track[x2][1];
			return track[x2-1][2];
		}
		else
			if(x3==0)
				return track[x3][1];
			return track[x3-1][3];
		
	}	
	public static void pop(int outputtrack) { // 刪除最後存進的資料
		if(outputtrack==1) {
			if(x1==0) {}
			else {
				track[x1-1][1]=0;
				x1--;
			}
				
		}
		else if(outputtrack==2) {
			if(x2==0) {}
			else {
				track[x2-1][1]=0;
				x2--;
			}
		}
		else if(outputtrack==3) {
			if(x3==0) {}
			else {
				track[x3-1][1]=0;
				x3--;
			}
		}
			
	}
	public static boolean empty(int i) {
		if(track[0][i]!=0)
			return false;
		return true;		
	}
}
