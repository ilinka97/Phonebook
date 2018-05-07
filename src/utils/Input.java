package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

	static Scanner input=new Scanner(System.in);
	
	public static String getString(){
		
		String string=input.next();

		String nString="";
		for(int i=0; i<string.length(); i++){
			if(Character.isLetterOrDigit(string.charAt(i))){
				nString=string;
			}else{
				System.out.println("Inappropriate string input.");
				System.exit(1);
			}
		}
		return nString;
	}

	public static int getInt(){
		
		int integer=0;
		
		try{
			integer=input.nextInt();
		}catch(InputMismatchException ex){
			//System.out.println("Inappropriate input!");
			//System.exit(1);
		}
		return integer;
		
	}
	
}
