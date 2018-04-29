import java.util.*;

class Fnf {
    
    static int n=5;
    static Set<Character> firstans = new HashSet<Character>();
    static char[][] firstarr = new char[5][5];
    static char[][] followarr = new char[5][5];
    static char[][] followt = new char[5][10];
    static Set<Character> followans = new HashSet<Character>();
    static HashMap<String,String> prod = new HashMap<String,String>();
    static int ftj = 0;
    public static void main(String[] args) {
    	
    	String a,b;
    	int i=0,j=0;
    	System.out.println("*****First and Follow Sets for LL(1) Parser*****\n");
    	System.out.println("Enter number of productions: ");
    	Scanner sc = new Scanner(System.in);
    	n = sc.nextInt();
    	System.out.println("Enter E->A|B as E A|B (Enter e for null production): ");
    	for(i=0;i<n;i++) {
    	    a=sc.next();
    	    b=sc.next();
    	    prod.put(a,b);
    	}
    	
        i=0;j=0;
        System.out.println("*****First Set*****");
    	Iterator it = prod.keySet().iterator();
    	while(it.hasNext()) {
    	    String cast = (String)it.next();
    	    char fk = cast.charAt(0);
        	String firstkey = Character.toString(fk);
        	first(prod,firstkey,(String)prod.get(firstkey)); 
        	Iterator<Character> its = firstans.iterator();
        	firstarr[i][j] = fk;j++;
        	System.out.print(firstkey+": ");
            while(its.hasNext()){
                char value  = its.next();
                firstarr[i][j] = value;
                j++;
                System.out.print(value+" ");
            } 
            i++;j=0;
            System.out.println();
            firstans.clear();   
        }
        
        //System.out.println("*****Follow Set - Part 1*****");        
        calfollowt();
        
        /*for(i=0;i<n;i++) {
            for(j=0;j<followt[i].length;j++) {
                System.out.print(followt[i][j]+" ");
            }
            System.out.println();
        }*/
        
        System.out.println("*****Follow Set*****");        
        for(i=0;i<5;i++) {
            follow(i);
            System.out.print(followt[i][0]+": ");
            j=0;
            Iterator<Character> its = followans.iterator();
            while(its.hasNext()){
                char value  = its.next();
                System.out.print(value+" ");
                followarr[i][j] = value;
                j++;
            }
            System.out.println();
            followans.clear(); 
        }        
    }

    public static void first(HashMap<String,String> prod,String nt,String t) {
    	
    	String[] prods = t.split("\\|");
    	//System.out.println(prods[0]);
    	
    	for(String p:prods) {
    	    if(Character.isLowerCase(p.charAt(0)) == true || Character.isLetter(p.charAt(0)) == false ) {
    	        if(firstans.contains(p.charAt(0)) == false) firstans.add(p.charAt(0));
    	    }
    	    else if(Character.isUpperCase(p.charAt(0)) == true) {
    	        String key = Character.toString(p.charAt(0));
    	        first(prod,key,(String)prod.get(key));   	
    	    }
    	}
    }
    
    public static void calfollowt() {
        int i,j=0;
        
        for(i=0;i<5;i++) {
            
            j=0;followt[i][0] = firstarr[i][0];j++;            
            Iterator itm = prod.keySet().iterator();
            
            while(itm.hasNext()) {
                String key = (String)itm.next();
                String[] prods = prod.get(key).split("\\|");
                for(String p:prods) {
                    if(p.contains(Character.toString(followt[i][0])) == true) {
                        int pos = p.indexOf(followt[i][0]) +1;
                        if(p.length()<=pos) {
                            followt[i][j] = 'b';j++;
                            if(followt[i][0] != key.charAt(0)) followt[i][j] = key.charAt(0);
                            j++;
                        }
                        else {
                            followt[i][j] = p.charAt(pos);
                            if(Character.isUpperCase(followt[i][j])) if(isE(followt[i][j]) == true) calfollowte(i,j+1,pos+1,p,key);
                            j = ftj;
                        }                    
                    }
                }
            }
        }
    }
    public static boolean isE(char t) {
        int i,j;
        for(i=0;i<5;i++) {
            if(firstarr[i][0] == t) {
                for(j=1;j<firstarr[i].length;j++) {
                    if(firstarr[i][j] == 'e') return true;   
                }          
            }
        }
        return false;
    }
    public static void calfollowte(int i,int j,int pos,String p,String key) {

        if(p.length()<=pos) {
            followt[i][j] = 'b';j++;
            if(followt[i][0] != key.charAt(0)) followt[i][j] = key.charAt(0);
            j++;
            ftj=j;
        }
        else if(isE(p.charAt(pos)) == false) {
            followt[i][j] = p.charAt(pos);
        }
        else {
            followt[i][j] = p.charAt(pos);
            calfollowte(i,j+1,pos+1,p,key);
        }
    }
    public static void follow(int i) {
        int j=1,k,l;
        if(i==0) followans.add('$');
        while(j<followt[i].length) {
            //System.out.println(followt[i][j]+"//");
            if(followt[i][j] == 'b') {
                j++;
                for(k=0;k<5;k++) {
                    if(followt[k][0] == followt[i][j]) follow(k);
                }                 
            }
            else if(Character.isUpperCase(followt[i][j]) == true && Character.isLetter(followt[i][j]) == true) {
                for(l=0;l<5;l++) {
                    if(followt[l][0] == followt[i][j]) break;
                }
                
                for(k=1;k<firstarr[l].length;k++) {
                    if(firstarr[l][k] !='e') followans.add(firstarr[l][k]);
                }
            }
            else {
                followans.add(followt[i][j]);
            }
            j++;
        }
    }    
}
