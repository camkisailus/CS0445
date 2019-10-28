package cs445.lab7;

import java.lang.Math;

public class SieveOfEratosthenes {
    public static ListInterface<Integer> primesUpTo(int max) {
        ListInterface<Integer> result = new ArrayList<>();
        for(int i =2;i<=max;i++){
        	result.add(i);
        }
        int j = 2;
        while(j<Math.sqrt(max)){
        	for(int i =j;i<result.getSize();i++){
        		if(result.get(i)%j==0){
        			result.remove(i);
        		}
        	}
        	j++;
        }
        return result;
    }


    public static void main(String[] args) {
        int end = 0;
        try {
            end = new Integer(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        }

        ListInterface<Integer> result = primesUpTo(end);
        if (result != null) {
            System.out.println("Primes:");
            for (int i = 0; i < result.getSize(); i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println(" ");
        } else {
            System.out.println("primesUpTo() returned null. Did you complete it?");
        }
    }
}
