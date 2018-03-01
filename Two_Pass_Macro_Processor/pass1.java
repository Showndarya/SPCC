import java.util.*;
import java.lang.*;
import java.io.*;

class pass1
{
	public static void main(String []args)
	{
		BufferedReader reader;
		int lc=0,mnti=0,mdti=0,i,j,li=0,alai=0,alac=0,alasi=0,prgi=0;
		String[] mdt = new String[200];
		String[] mnt = new String[100];
		String[] ala = new String[100];		
		int[] mntin = new int[100];
		int[] alain = new int[100];
		int[][] alas = new int[100][3];
		String[] prgstat = new String[200];
		try 
		{
			reader = new BufferedReader(new FileReader("prg.txt"));
			String line = reader.readLine();
			String[] words = line.split("\\s+");						
			//System.out.println(sym[0]+" "+symtab[0][0]);	
			while (!line.trim().equals("END")) 
			{
				if(words[0].equals("MACRO"))
				{
					li=0;alac=0;
					//System.out.println("yes");
					while(!words[0].equals("MEND"))
					{
						line = reader.readLine();
						words = line.split("\\s+");	
						if(li==0)
						{
							mnt[mnti] = words[0];
							String[] op = words[1].split(",");
							alas[alasi][0] = alai; alas[alasi][1] = mnti;alas[alasi][2]=op.length;
							for(i=0;i<op.length;i++)
							{
								ala[alai] = op[i];
								alain[alai] = alac;
								alac++;
								alai++;
							} 
							mntin[mnti] = mdti;							
							mdt[mdti] = line;
							mnti++;
							mdti++;
							alasi++;
							li++;
						}
						else
						{
							for(i=alas[alasi-1][0];i<alai;i++)
							{
								if(line.contains(ala[i])==true) line = line.replace(ala[i],"#"+Integer.toString(alain[i]));
							}
							mdt[mdti] = line;
							mdti++;
						}
					}
					//System.out.println(line);
				}
				else 
				{
					prgstat[prgi] = line;
					prgi++;
				}
				line = reader.readLine();	
				words = line.split("\\s+");								
			}
			reader.close();	
			prgstat[prgi] = line;
			prgi++;
			//for(i=0;i<mnti;i++) System.out.println(mnt[i]+" "+mntin[i]);
			//for(i=0;i<mdti;i++) System.out.println(i+" "+mdt[i]);	
			//for(i=0;i<alai;i++) System.out.println(alain[i]+" "+ala[i]);
			
			try(OutputStream fw = new FileOutputStream("macro_name_table.txt"))
			{
				for(i=0;i<mnti;i++)	
				{
					// SR NO		macro name		MDTindex
					String content = i+" "+mnt[i]+" "+mntin[i]+System.getProperty("line.separator");
					fw.write(content.getBytes(),0,content.length());
				}
			}
			catch (IOException e) { e.printStackTrace(); }
			System.out.println("Check file macro_name_table.txt");	
			try(OutputStream fw = new FileOutputStream("macro_definition_table.txt"))
			{
				for(i=0;i<mdti;i++)	
				{
					// SR NO		macro definition
					String content = i+" "+mdt[i]+System.getProperty("line.separator");
					fw.write(content.getBytes(),0,content.length());
				}
			}
			catch (IOException e) { e.printStackTrace(); }
			System.out.println("Check file macro_definition_table.txt");	
			try(OutputStream fw = new FileOutputStream("argument_list_array_pass_1.txt"))
			{
				for(i=0;i<alai;i++)	
				{
					// SR NO		argument index in mdt		argument name
					String content = i+" "+alain[i]+" "+ala[i]+System.getProperty("line.separator");
					fw.write(content.getBytes(),0,content.length());
				}
			}
			catch (IOException e) { e.printStackTrace(); }
			System.out.println("Check file argument_list_array_pass_1.txt");
			try(OutputStream fw = new FileOutputStream("prg_intermidiate.txt"))
			{
				for(i=0;i<prgi;i++)	
				{
					// program line
					String content = prgstat[i]+System.getProperty("line.separator");
					fw.write(content.getBytes(),0,content.length());
				}
			}
			catch (IOException e) { e.printStackTrace(); }
			System.out.println("Check file prg_intermidiate.txt");
			// This file is for background processing. This stores the start index of arguments of a macro in the ala as the ala is the same for all macros.
			try(OutputStream fw = new FileOutputStream("alas.txt"))
			{
				for(i=0;i<alasi;i++)	
				{
					// alastartindex		mntindex		number of arguments in that macro
					String content = alas[i][0]+" "+alas[i][1]+" "+alas[i][2]+System.getProperty("line.separator");
					fw.write(content.getBytes(),0,content.length());
				}
			}
			catch (IOException e) { e.printStackTrace(); }
		} 
		catch (IOException e) { e.printStackTrace(); }
	}
}
		
