import java.util.*;
import java.io.*;

class Lex {

    public static void main(String[] args) {
    
        List<String> keywords = Arrays.asList("break","else","auto","case","char","const","continue","default","do","double","else","enum","extern","float","for","goto","if","int","long","register","return","short","signed","sizeof","static","struct","switch","typedef","union","unsigned","void","volatile","while");
    
        List<String> operators = Arrays.asList("+","-","*","/","%","==","!=","||","=","|","&&","&","{","}","(",")");
        List<String> delimiter = Arrays.asList(";");
        List<String> functions = Arrays.asList("printf","main","scanf");
        Vector<String> identifiers = new Vector<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("code.txt"));
            String line = reader.readLine();
            int i;
            
            while(line!=null) {
                if(line.length()==1) {
                    if(operators.contains(line)) System.out.print("Operator"+operators.indexOf(line)+" ");
                }
                else {
                    String temp="";int igb=0;
                    for(i=0;i<line.length();i++) {
                        if(line.charAt(i) == ' ') {
                            if(keywords.contains(temp)) System.out.print("Keyword"+keywords.indexOf(temp)+" ");
                            else if(operators.contains(temp)) System.out.print("Operator"+operators.indexOf(temp)+" ");  
                            //System.out.print(temp+"//");  
                            temp="";                
                        }
                        else if(line.charAt(i) == '(') {
                            if(functions.contains(temp)) {
                                System.out.print("Function"+functions.indexOf(temp)+" ");
                                igb=1;
                                temp="";
                            }
                            else {
                                System.out.print("Operator"+operators.indexOf("(")+" ");
                            }
                            //System.out.print(temp+"//");                    
                        }
                        else if(line.charAt(i) == ')') {
                            if(igb==1) igb=0;
                            else {
                                if(temp!="") { 
                                    if(keywords.contains(temp)) System.out.print("Keyword"+keywords.indexOf(temp)+" ");
                                    else if(functions.contains(temp)) System.out.print("Function"+functions.indexOf(temp)+" ");
                                    else {
                                        System.out.print("Identifier");
                                        if(!identifiers.contains(temp)) identifiers.add(temp);
                                        System.out.print(identifiers.indexOf(temp)+" ");
                                    }
                                }
                                System.out.print("Operator"+operators.indexOf(")")+" ");
                            }  
                            //System.out.print(temp+"//");
                            temp="";                  
                        }
                        else if(igb==1) continue;
                        else if(line.charAt(i) == ',') {
                            if(keywords.contains(temp)) System.out.print("Keyword"+keywords.indexOf(temp)+" SpecialCharacter");
                            else if(functions.contains(temp)) System.out.print("Function"+functions.indexOf(temp)+" SpecialCharacter");
                            else {
                                System.out.print("Identifier");
                                if(!identifiers.contains(temp)) identifiers.add(temp);
                                System.out.print(identifiers.indexOf(temp)+" SpecialCharacter ");
                            }
                            //System.out.print(temp+"//");
                            temp="";
                        }
                        else if(operators.contains(Character.toString(line.charAt(i)))) {
                            if(temp!="") {
                                if(keywords.contains(temp)) System.out.print("Keyword"+keywords.indexOf(temp)+" ");
                                else if(functions.contains(temp)) System.out.print("Function"+functions.indexOf(temp)+" ");
                                else {
                                    System.out.print("Identifier");
                                    if(!identifiers.contains(temp)) identifiers.add(temp);
                                    System.out.print(identifiers.indexOf(temp)+" ");
                                }
                            }
                            System.out.print("Operator"+operators.indexOf(Character.toString(line.charAt(i)))+" ");
                            //System.out.print(temp+"//");
                            temp="";
                        }
                        else if(delimiter.contains(Character.toString(line.charAt(i)))) {
                            if(temp!="") { 
                                if(keywords.contains(temp)) System.out.print("Keyword"+keywords.indexOf(temp)+" ");
                                else if(functions.contains(temp)) System.out.print("Function"+functions.indexOf(temp)+" ");
                                else {
                                    System.out.print("Identifier");
                                    if(!identifiers.contains(temp)) identifiers.add(temp);
                                    System.out.print(identifiers.indexOf(temp)+" ");
                                }
                            }
                            System.out.print("Delimiter ");
                            //System.out.print(temp+"//?");
                            temp="";
                        }
                        else temp+=Character.toString(line.charAt(i));                       
                    }
                
                }
                System.out.println();
                line = reader.readLine();
            
            }
            System.out.println("Identifiers: "+identifiers);        
        }catch(IOException e){}      
    
    }

}
