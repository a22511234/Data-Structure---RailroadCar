package car;
import java.util.*;
import java.io.*;
public class Car {
	private static int smallestCar; //所有避車道的最小
	private static int itsTrack;  //有最小值的避車道
	private static int numberOfCars=0;
	private static int numberOfTracks=3;
	private static int out[]=new int [9];
	private static int count=0;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		File file= new File("C://car3.TXT");
		Scanner input=new Scanner(file,"MS950");
		int p1[]=new int[9];
		int p[]=new int[9];
	    int track [][]=new int[4][10] ; // 3個避車道 1.2.3 最多避10台車
		while(input.hasNext()) {
			p1[numberOfCars]=input.nextInt();
			numberOfCars++;
		}
		for(int i=0;i<9;i++) 
			p[8-i]=p1[i];       
		for(int i=0;i<numberOfCars;i++) 
			System.out.print(p1[i]+" ");
		System.out.println();
		railroad(p,track);
		for(int i=numberOfCars-1;i>=0;i--) 
		System.out.print(out[i]+" ");
	}	
	
	public static boolean railroad(int p[] ,int track[][]) {
		int nextCarToOutput=1;
		smallestCar=numberOfCars+1;
		for(int i=0;i<numberOfCars;i++) {
			if(p[i]==nextCarToOutput) {
				out[count]=p[i];
				count++;
				System.out.println("move car "+p[i]+" from input track to output track ");	
			nextCarToOutput++;
			while(smallestCar==nextCarToOutput) {
				outputFromHoldingTrack(track);
				nextCarToOutput++;
			}}
			else {
				if(!putHoldingTrack(p[i],track))
					return false;
			}
		}
		return true;
	}
	
	public static boolean putHoldingTrack(int c,int track[][]) {
		int bestTrack=0;
		int bestTop=numberOfCars+1; //一個假設 不會有比bestTop更大的
		for(int i=1 ; i<=numberOfTracks ; i++) {//track.1.2.3道
			if(empty(track,i)) { //如果避車道是空的
				if(bestTrack==0)
					bestTrack=i;   } //if}
			else {  //如果避車道不是空的
				int topCar=peek(track,i); //偷看最上面的數字是多少
				if(c<topCar && topCar<bestTop) {
					bestTop=topCar;
					bestTrack=i;  }
			}  //else}
		} //for}
		if(bestTrack==0)
			return false;
		push(track,bestTrack,c);
		System.out.println("move car "+c+" from input track to holding track "+bestTrack);
		if(c<smallestCar) {
			smallestCar=c;
			itsTrack=bestTrack;  }
		return true;
	}
	
	public static void outputFromHoldingTrack(int track[][]) {
		int n=pop(track,itsTrack);
		System.out.println("move car "+smallestCar+" from holding track "+itsTrack+" to output track");
		out[count]=smallestCar;
		count++;
		smallestCar=numberOfCars+2;
		for(int i=1 ; i<=numberOfTracks ; i++) {
			if(!empty(track,i) && peek(track,i)<smallestCar) {
				smallestCar=peek(track,i);
				itsTrack=i;
			}
		}
	}
	
	//push
	public static void push( int a[][] , int bestTrack , int c){
	     int top=0;
		 for(int i=0;i<10;i++){  //找避車道裡面有多少個數了
			  if(a[bestTrack][i]==0) {
			  top=i;             
			  break;}
		 }
		 a[bestTrack][top]=c;
	} 
	//pop
	public static int pop( int a[][] , int bestTrack ){
	     int top=0;
		 for(int i=0;i<10;i++){  //找避車道裡面有多少個數了
			  if(a[bestTrack][i]==0) {
			  top=i;             
			  break;}
		 }
		 int x=a[bestTrack][top-1];
		 a[bestTrack][top-1]=0;
		return x;
	} 
	
	//empty
	public static boolean empty(int a[][] ,int bestTrack ) {
		int top=0;
		for(int i=0;i<10;i++){  //找避車道裡面有多少個數了
			  if(a[bestTrack][i]==0) {
			  top=i;             
			  break;  }
		 }
		if(top==0)              //如果避車道是空的，回傳true
			return true;        //如果避車道不是空的，回傳false
		return false;
	}
	
	//peek
	public static int peek(int a[][] ,int bestTrack) {
		int n=0; //最上面的數字
		int top=0;
		for(int i=0;i<10;i++){  //找避車道裡面有多少個數了
			  if(a[bestTrack][i]==0) {
			  top=i;             
			  break;  }
		 }
		if(top!=0) 
			return n=a[bestTrack][top-1];
		else
		    return -1;
	}
	  
}
