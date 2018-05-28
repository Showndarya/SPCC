import java.util.*;
import java.io.*;

class CodeGeneration {
    
    static Vector<String> threeadd = new Vector<>();
    static char tempVar='z';
    static int regcounter=0;
    static Map<String,Integer> reg = new HashMap<>();
    static Vector<String> machinecode = new Vector<>();
    
    public static void main(String []args) {
    
        String input;int i;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter expression: ");
        input = sc.nextLine();
        
        generateThreeAddressCode(input);
        
        System.out.println();
        System.out.println("Three Address Code: ");
        for(i=0;i<threeadd.size();i++) System.out.println(threeadd.get(i));
        
        generateMachineCode();
        
        System.out.println();
        System.out.println("Machine Code: ");
        for(i=0;i<machinecode.size();i++) System.out.println(machinecode.get(i));        
    }
    
    public static void generateThreeAddressCode(String s) {
    
        String after="";
        if(s.contains("=") && s.length()==3) {
            threeadd.add(s);
            return;
        }
        else if(s=="") return;
        
        if(s.contains("(")) {        
            int start = s.indexOf("(");
            int end = s.indexOf(")");
            String temp = tempVar+"= "+s.substring(start+1,end);
            after = s.substring(0,start)+tempVar+s.substring(end+1);
            tempVar--;
            threeadd.add(temp);        
        }
        else if(s.contains("/")) {
            int c = s.indexOf("/");
            String temp = tempVar+"= "+s.substring(c-1,c)+"/"+s.substring(c+1,c+2);
            after = s.substring(0,c-1)+tempVar+s.substring(c+2);
            tempVar--;
            threeadd.add(temp);
        }
        
        else if(s.contains("*")) {
            int c = s.indexOf("*");
            String temp = tempVar+"= "+s.substring(c-1,c)+"*"+s.substring(c+1,c+2);
            after = s.substring(0,c-1)+tempVar+s.substring(c+2);
            tempVar--;
            threeadd.add(temp);
        }
        
        else if(s.contains("+")) {
            int c = s.indexOf("+");
            String temp = tempVar+"= "+s.substring(c-1,c)+"+"+s.substring(c+1,c+2);
            after = s.substring(0,c-1)+tempVar+s.substring(c+2);
            tempVar--;
            threeadd.add(temp);
        }
        
        else if(s.contains("-")) {
            int c = s.indexOf("-");
            String temp = tempVar+"= "+s.substring(c-1,c)+"-"+s.substring(c+1,c+2);
            after = s.substring(0,c-1)+tempVar+s.substring(c+2);
            tempVar--;
            threeadd.add(temp);
        }
        //System.out.println(after);
        generateThreeAddressCode(after);
    }
    
    public static void generateMachineCode() {
    
        int i,p=0,f=0;
        for(i=0;i<threeadd.size();i++) {
            String s = threeadd.get(i);
            String temp="";
               
            if(s.contains("+")) p = s.indexOf("+");
            else if(s.contains("-")) p = s.indexOf("-");
            else if(s.contains("*")) p = s.indexOf("*");
            else if(s.contains("/")) p = s.indexOf("/");
            else if(s.contains("=")) {
                p = s.indexOf("=");
                temp = "MOV "+reg.get(s.substring(p+1))+","+s.substring(0,1);
                machinecode.add(temp);
                temp="";
                f=1;
                continue;           
            }
            
            if(reg.containsKey(s.substring(p-1,p)) == false) {
                reg.put(s.substring(p-1,p),regcounter);
                temp = "MOV "+s.substring(p-1,p)+","+regcounter;
                machinecode.add(temp);              
                regcounter++;  
            }
            //System.out.println(temp);
            temp="";
            if(reg.containsKey(s.substring(p+1,p+2)) == false) {
                reg.put(s.substring(p+1,p+2),regcounter);
                temp = "MOV "+s.substring(p+1,p+2)+","+regcounter;
                machinecode.add(temp);
                regcounter++;                
            }
            //System.out.println(temp);
            temp="";
            
            if(s.contains("+")) temp = "ADD ";
            else if(s.contains("-")) temp = "SUB "; 
            else if(s.contains("*")) temp = "MUL ";
            else if(s.contains("/")) temp = "DIV ";
            
            temp += reg.get(s.substring(p-1,p))+","+reg.get(s.substring(p+1,p+2));
            int r= reg.get(s.substring(p-1,p));
            reg.remove(s.substring(p-1,p));
            reg.put(s.substring(0,1),r);
            machinecode.add(temp);
            //System.out.println(temp);          
        } 
        if(f==0) {
            String temp="",s=threeadd.get(i-1);
            temp = "MOV "+reg.get(s.substring(0,1))+","+s.substring(0,1);
            machinecode.add(temp);
        }
    }
}
