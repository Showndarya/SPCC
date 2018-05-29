import java.util.*;
import java.io.*;

class LeftRecursion {

    static Map<String,String> prod = new HashMap<>();
    static Map<String,String> newprod = new HashMap<>();
    static Vector<String> alpha = new Vector<>();
    static Vector<String> beta = new Vector<>();
    
    public static void main(String[] args) {
    
        int i;
        String a,b;
        System.out.println("Enter number of productions:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Enter productions: ");
        for(i=0;i<n;i++) {
            a=sc.next();
            b=sc.next();
            prod.put(a,b);
        }
        
        Iterator it = prod.keySet().iterator();
        while(it.hasNext()) {
            int pos=0,flag=0;i=0;
            String key = (String) it.next();
            String value = prod.get(key);
            String[] prods = value.split("\\|");
            
            for(String p:prods) {
                if(p.charAt(0) == key.charAt(0)) alpha.add(p.substring(1));                
                else beta.add(p);
                pos++;                    
            }
            
            if(alpha.size()>0) {
                String valkey="";
                for(String al:alpha) {
                    valkey+=al+key+"'|";
                }
                valkey+="e";
                newprod.put(key+"'",valkey);
                valkey="";
                for(String be:beta) {
                    valkey+=be+key+"'|";
                }
                newprod.put(key,valkey);
            }
            else {
                newprod.put(key,value);
            }
        }
        
        it = newprod.keySet().iterator();
        while(it.hasNext()) {
            String key = (String) it.next();
            System.out.println(key+" "+newprod.get(key));
        }
    }
}
