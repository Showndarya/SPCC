import java.util.*;
import java.io.*;

class rdp
{
	static String input;
	static int i=0,lc=0;
	
	public static void S()
	{
		if(input.charAt(lc)=='b' && input.length()==1) valid();
		else if(input.length()>1)
		{
			if(input.charAt(input.length()-1) =='b') A();
			else throwerror();
		}
		else throwerror();			
	}
		
	public static void A()
	{
		//System.out.println("inb");
		if(input.charAt(lc)=='b')
		{
			lc++;
			if(input.charAt(lc)=='d')
			{
				lc++;
				if(input.charAt(lc)=='c' || input.charAt(lc)=='a') B();
				else throwerror();
			}
			else throwerror();
		}
		else if(input.charAt(lc)=='c' || input.charAt(lc)=='a') B();
		else throwerror();
	}
	
	public static void B()
	{
		if(input.charAt(lc)=='c' && (input.charAt(lc+1)=='c' || input.charAt(lc+1)=='a'))
		{
			lc++;
			if(input.charAt(lc)=='c' || input.charAt(lc)=='a')	B();
			else throwerror();
		}
		else if(input.charAt(lc)=='a')
		{
			lc++;
			if(input.charAt(lc)=='d')
			{
				lc++;
				if(input.charAt(lc)=='c' || input.charAt(lc)=='a') B();
				else if(input.charAt(lc)=='b' && lc==input.length()-1)	valid();
			}
			else throwerror();
		}
		else if(input.charAt(lc)=='c') valid();
		else throwerror();
	}
	
	public static void throwerror()
	{
		System.out.println("String is invalid");
	}
	
	public static void valid()
	{
		System.out.println("String is valid");
	}
	
	public static void main(String args[])
	{
		System.out.println("Enter string: ");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
			S();
		}
		catch(IOException e){}
	}
}
