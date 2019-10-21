package cs445.lab6;

import java.util.Arrays;


public class Lab6{

	static <T> void reverse(T[] a){
		int start = 0;
		int end = a.length -1;

		reverseArray(a,start,end);
	}

	static <T> void reverseArray(T[] arr, int start, int end){
		if (start>=end){
			return;
		}else{
			T temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			reverseArray(arr,start+1,end-1);
		}
	}

	static String replace(String str, char before, char after){
		if (str.length()<1)
			return str;

		char first = before == str.charAt(0) ? after : str.charAt(0);
		return first + replace(str.substring(1), before, after);



	}



}
