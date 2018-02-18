import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class lex {
    public static void main(String[] args) {
  	Set<String> set = new HashSet<String>();
  	Set<String> dig = new HashSet<String>();
    String s="";
    int f=0,c=0,sy=0,i;
    String kw[] = {"break","else","auto","case","char","const","continue","default","do","double","else","enum","extern","float","for","goto","if","int","long","register","return","short","signed","sizeof","static","struct","switch","typedef","union","unsigned","void","volatile","while"};
    
    String op[] = {"+","-","*","/","%","==","!=","||","=","|","&&","&","{","}","(",")"};
    String sp[] = {";"};
    String pf[] = {"printf","main"};
    File file = new File("code.txt");
    if (!file.exists()) {
      System.out.println(" does not exist.");
      return;
    }
    if (!(file.isFile() && file.canRead())) {
      System.out.println(file.getName() + " cannot be read from.");
      return;
    }
    try {
      FileInputStream fis = new FileInputStream(file);
      char current;
      while (fis.available() > 0) {
        current = (char) fis.read();
        if(current==' ' ||current=='\n' || current==';')
        { 
        	if(Arrays.asList(kw).contains(s)) System.out.print("Keyword"+Arrays.asList(kw).indexOf(s)+" ");
        	else if(Arrays.asList(op).contains(s)) System.out.print("Operator"+Arrays.asList(op).indexOf(s)+" ");
        	else if(Arrays.asList(sp).contains(s)) System.out.print("SpecialCharacter0");
        	else 
        	{
        		if(s!="")
        		{
        			sy++;
        			for (i = 0; i < s.length(); i++) 
        			{
						if(!Character.isDigit(s.charAt(i))) 
						{
							set.add(s);
							break;
						}
					}
					if(i==s.length()) dig.add(s);
        			if(s!="") System.out.print("Symbol"+sy+" ");
        		}
        	}
        	if(current=='\n') System.out.println();
        	if(current==';') System.out.print("SpecialCharacter0");
        	s="";
        }
        else if(current==',' && f!=2)
        {
        	sy++;
        	if(s!="") 
        	{
        		for (i = 0; i < s.length(); i++) 
        		{
						if(!Character.isDigit(s.charAt(i))) 
						{
							set.add(s);
							break;
						}
					}
					if(i==s.length()) dig.add(s);
        	}
        	if(s!="") System.out.print("Symbol"+sy+" ");
        	s="";
        }
        else if(current=='(')
        {
        	f=1;
        }
        else if(current==')' && f==1)
        {
        	f=0;
        	System.out.print("PreDefinedFunction"+Arrays.asList(pf).indexOf(s)+" ");
        	s="";
        }
        else if(current=='"' && f==1)
        {
        	f=2;
        	System.out.print("PreDefinedFunction"+Arrays.asList(pf).indexOf(s)+" ");
        	s="";
        }
        else if(f==1)
        {
        	c=1;
        	f=0;
        	System.out.print("Operator"+Arrays.asList(op).indexOf("(")+" ");
        	sy++;
        	if(s!="") 
        	{
        		for (i = 0; i < s.length(); i++) 
        		{
						if(!Character.isDigit(s.charAt(i))) 
						{
							set.add(s);
							break;
						}
				}
					if(i==s.length()) dig.add(s);
        	}
        	
        	if(s!="") System.out.print("Symbol"+sy+" ");
        }
        
        else if(f==2 && current==')')
        {
        	f=0;
        }
        else if(f==2) continue;
        else if(c==1)
        {
        	if(Arrays.asList(op).contains(Character.toString(current))) 
        	{
        		System.out.print("Operator"+Arrays.asList(op).indexOf(Character.toString(current))+" ");
        		s="";
        	}
        	else if(current==')') 
        	{
        		sy++;
        		if(s!="") System.out.print("Symbol"+sy+" ");
        		if(s!="") 
        		{
        			for (i = 0; i < s.length(); i++) 
        			{
						if(!Character.isDigit(s.charAt(i))) 
						{
							set.add(s);
							break;
						}
					}
					if(i==s.length()) dig.add(s);
        		}
        		System.out.print("Operator"+Arrays.asList(op).indexOf(")")+" ");
        		s="";
        	}
        	else s+=current; 
        }
        else s+=current;
        
      }
      Set<String> tree_Set = new TreeSet<String>(set);
      System.out.println("Symbols: " +tree_Set);
      Set<String> dig_Set = new TreeSet<String>(dig);
      System.out.println("Constants: " +dig_Set);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

