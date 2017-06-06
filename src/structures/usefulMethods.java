package structures;

import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.util.List;

public class usefulMethods {

	/**Returns true if the mouse is within the given bounds. Naturally applies a 10% buffer to the space provided*/
	public static boolean mouseWithin(int x, int y, int width, int height) {
		return (MouseInfo.getPointerInfo().getLocation().x + ((double)(Math.abs(x+width)))*.1 >= x && 
				MouseInfo.getPointerInfo().getLocation().x - ((double)(Math.abs(x+width)))*.1 <=x+width && 
				MouseInfo.getPointerInfo().getLocation().y + ((double)(Math.abs(y+height)))*.1 >=y && 
				MouseInfo.getPointerInfo().getLocation().y - ((double)(Math.abs(y+height)))*.1 <=y+height);
	}

	/**Returns true if the mouse is within the given bounds. Naturally applies a 10% buffer to the space provided*/
	public static boolean mouseWithin(Rectangle r) {
		return mouseWithin(r.x, r.y, r.width, r.height);
	}

	/**Returns true if the mouse is within the given bounds. Applies a buffer equal to percent Buffer*/
	public static boolean mouseWithin(int x, int y, int width, int height, double percentBuffer) {
		return (MouseInfo.getPointerInfo().getLocation().x + ((double)(Math.abs(x+width)))*(percentBuffer/100) >= x && 
				MouseInfo.getPointerInfo().getLocation().x - ((double)(Math.abs(x+width)))*(percentBuffer/100) <=x+width && 
				MouseInfo.getPointerInfo().getLocation().y + ((double)(Math.abs(y+height)))*(percentBuffer/100) >=y && 
				MouseInfo.getPointerInfo().getLocation().y - ((double)(Math.abs(y+height)))*(percentBuffer/100) <=y+height);
	}


	/**Returns true if the mouse is within the given bounds. Applies a buffer equal to percent Buffer*/
	public static boolean mouseWithin(Rectangle r, double percentBuffer) {
		return mouseWithin(r.x, r.y, r.width, r.height, percentBuffer);
	}


	/**Construct a string that represents the migrant layout constructor*/

	/** Prints any 2D List of Objects to the console*/
	public static void print2DList(List<? extends List<? extends Object>> obArray) {
		for (List<? extends Object> s: obArray) {
			for(Object o: s) {
				System.out.print(o + "\t\t\t");
			}
			System.out.println();
		}
	}


	/**Prints a 1D List of Objects to the Console*/
	public static void print1DList(List<? extends Object> obArray) {
		int i=0;
		for(Object o: obArray) {
			System.out.println(i + o.toString());
			i++;

		}
	}


	/**Prints a 1D Array of Objects to the Console*/
	public static void print1DArray(Object[] obArray) {
		int i=0;
		for(Object o: obArray) {
			System.out.println(i + o.toString());
			i++;

		}
	}


	/**Prints a 1D Array of bytes to the Console*/
	public static void print1DArrayPrim(byte[] intArray) {
		int i=0;
		for(int j: intArray) {
			System.out.println(i +": "+ j);
			i++;

		}
	}

	/**Prints a 1D Array of ints to the Console*/
	public static void print1DArray(int[] intArray) {
		int i=0;
		for(int j: intArray) {
			System.out.println(i +": "+ j);
			i++;

		}
	}

	/**Returns the type double value that is the average of the bytes in the array*/
	public static double calcAvg(byte[] num) {
		double total=0;
		for (int i = 0; i < num.length; i++)
		{
			total += num[i];
		}
		return total/num.length;
	}

	/**Returns the Standard Deviation of the array of bytes*/
	public static double stantardDeviation(byte[] num) {
		double average = calcAvg(num);
		double sd = 0;
		for (int i = 0; i < num.length; i++)
		{
			sd += Math.pow((num[i] - average),2) / num.length;
		}
		return Math.sqrt(sd);
	}

	/**Returns the Standard Deviation of the array of bytes with average average*/
	public static double stantardDeviation(byte[] num, double average) {
		double sd = 0;
		for (int i = 0; i < num.length; i++)
		{
			sd += Math.pow((num[i] - average),2) / num.length;
		}
		return Math.sqrt(sd);
	}

	
	/**Returns the type double value that is the average of the bytes in the ints*/
	public static double calcAvg(int[] num) {
		double total=0;
		for (int i = 0; i < num.length; i++)
		{
			total += num[i];
		}
		return total/num.length;
	}

	/**Returns the Standard Deviation of the array of ints*/
	public static double stantardDeviation(int[] num) {
		double average = calcAvg(num);
		double sd = 0;
		for (int i = 0; i < num.length; i++)
		{
			sd += Math.pow((num[i] - average),2) / num.length;
		}
		return Math.sqrt(sd);
	}

	/**Returns the Standard Deviation of the array of ints with average average*/
	public static double stantardDeviation(int[] num, double average) {
		double sd = 0;
		for (int i = 0; i < num.length; i++)
		{
			sd += Math.pow((num[i] - average),2) / num.length;
		}
		return Math.sqrt(sd);
	}
	
	
	/**Returns the maximum value of the array of ints with average average*/
	public static int getMax(int[] num) {
		int max = Integer.MIN_VALUE;
		for(int i=0;i<num.length;i++) {
			if (num[i]>max) {
				max=num[i];
			}
		}
		return max;
	}
	
	
	/**Returns the minimum value of the array of ints with average average*/
	public static int getMin(int[] num) {
		int min = Integer.MAX_VALUE;
		for(int i=0;i<num.length;i++) {
			if (num[i]<min) {
				min=num[i];
			}
		}
		return min;
	}
	
}
