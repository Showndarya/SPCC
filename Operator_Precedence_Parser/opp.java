import java.util.*;
import java.io.*;

class opp
{

	static String input="",modinp="",eprod="";
	static char prev,curr,top;
	static int i,j,k,flag=0,sp=0;
	static char[] stack = new char[100];
	static HashMap<Integer,String> productions=new HashMap<Integer,String>();  
	
	public static void initprod()
	{
	    productions.put(1,"E*E");
	    productions.put(2,"E+E");    
	}
	
	public static int precedence(char a, char b)
	{
		if(a=='x') return 1;
		else if(b=='x') return 2;
		else if(b=='*') return 2;//g
		else if(a=='*'|| a==b) return 1;//l
		else return 3;
	}
	
	public static char retnt()
	{
	    for(int i=sp-1;i>=0;i--)
	    {
	        if(stack[i]!='$' && stack[i]!='E') return stack[i];
	        //System.out.println("/"+ stack[i]);
	    }
		return 'e';
	}
	
	public static void replacest(char a)
	{
	    for(int i=sp-1;i>=0;i--)
	    {
	        if(stack[i]==a) 
	        {
	             stack[i]='E'; 
	        }
	    }	
	}
	public static void pushst(char a)
	{
		stack[sp] = a;
		sp++;
	}
	
	public static void printst()
	{
	   System.out.print("The string after replacing with non-terminals is: ");
	   for(int i=0;i<=sp;i++) System.out.print(stack[i]+" "); 
	   System.out.println();
	}
	
	public static void inpwpre()
	{
	    modinp+="$l"+input.charAt(0);
		prev = input.charAt(0);
		for(i=1;i<input.length();i++)
		{
			if(prev=='x' && prev==input.charAt(i)) flag=1;
			else if(precedence(prev,input.charAt(i))==1) modinp+= "g"+Character.toString(input.charAt(i));
			else if(precedence(prev,input.charAt(i))==2) modinp+= "l"+Character.toString(input.charAt(i));	
			prev = input.charAt(i);	
		}
		modinp+="g$";
		System.out.println("The modified input string with precedence order is ( l- <.; g- .>): "+modinp);
	}
	
	public static void replaceprod()
	{
	    if(eprod.equals("E")) flag=2;
	    else 
	    {
	        for (Map.Entry<Integer,String> entry : productions.entrySet())
	        {
	            if(eprod.contains(entry.getValue()))  
	            {
	                eprod = eprod.replace(entry.getValue(),"E");
	                flag=-1;
	            }
	            //System.out.println("/"+eprod);
	        }
	    }	    
	    if(flag==-1) flag=0;
	    else flag=1;
	}
	
	public static void main(String args[])
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Operater Precedence Parser for the grammar: E → E+E | E*E | x");
			System.out.println("Enter string: ");
			input = br.readLine();
			
			input = "$"+input+"$";
			initprod();
			inpwpre();
			
			for(i=0;i<input.length();i++)
			{
			    //System.out.println("--------"+i+"----------");
				curr = input.charAt(i);
				
				if(curr=='$')  pushst(curr);
				else if(curr=='x') 
			    {
			        if(retnt()=='x') 
			        {
			            flag=1;
			            break;
			        }
			        else pushst(curr);
			    }
				else if(curr=='+' || curr=='*') 
				{
				    top = retnt();
				    //System.out.println("|"+top);
				    k = precedence(curr,top);
				    if(k==1) pushst(curr);
				    else
				    {
				        replacest(top);
				        pushst(curr);
				    }
				}
				else flag=1;				
				//printst();
			}
			replacest('x');
			printst();
			if(flag==1) System.out.println("Entered string is invalid");
			else
			{
			    flag=0;
			    for(i=0;i<sp;i++) 
			    {
			        if(stack[i]=='$') continue;
			        else eprod+=Character.toString(stack[i]);
			    }
			    //System.out.println(eprod);
			
			
			    while(flag!=1)
			    {
			        replaceprod(); 
			        //System.out.println(eprod);
			        if(flag==2) break;  
			    }
			    //System.out.println(eprod);
			    if(eprod.equals("E")) System.out.println("Entered string is valid");
			    else System.out.println("Entered string is invalid");
			}
		}
		catch(IOException e){}
	}
}


