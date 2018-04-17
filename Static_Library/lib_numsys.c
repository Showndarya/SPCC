#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include "lib_numsys.h"


int toBinaryInt(int decimal) {

    int binary=0,base=1,i=0,length=0;
    
    while(decimal>0) {
        
        binary = (decimal%2)*base;
        base*=10;
        decimal = decimal/2;
        i++;
        length=i;
            
    }
    
    int bin_final = 0;
    while(binary > 0)
    {
        bin_final = bin_final*10 + binary%10;
        binary = binary/10;
    }
    return bin_final;
    

}
int* toBinary(int decimal) {

    static int *binary;
    binary = (int*) malloc(1*sizeof(int));
    int i=0,length,ln;
    
    while(decimal>0) {
    
        *(binary+i) = decimal%2;
        decimal = decimal/2;
        i++;
        length=i;
        if(i>1) binary = realloc(binary,i);
    }
    
    i=0;
    ln = length;
    length = length-1;
    
    while(i<length) {
        int temp = *(binary+i);
        *(binary+i) = *(binary+length);
        *(binary+length) = temp;
        i++;
        length--;
    }
     
    *(binary+ln) = -1;
    return binary;

}

char* toHexadecimal(int decimal) {

    static char *hex;
    hex = (char*) malloc(1*sizeof(char));
    int i=0,length,ln;
    
    while(decimal>0) {
    
        int temp = decimal%16;
        
        if(temp<10) {
        
            *(hex+i) = temp + 48;
            i++;
            length=i;
            
        }
        
        else {
        
            *(hex+i) = temp + 55;
            i++;
            length=i;
            
        }
        decimal = decimal/16;
        if(i>1) hex = realloc(hex,i);
    }
    i=0;
    ln = length;
    length = length-1; 
    
    while(i<length) {
        int temp = *(hex+i);
        *(hex+i) = *(hex+length);
        *(hex+length) = temp;
        i++;
        length--;
    }
    
    *(hex+ln) = '\0';
    return hex;

}

int* toOctal(int decimal) {

    static int *octal;
    octal = (int*) malloc(1*sizeof(int));
    int i=0,length,ln;
    
    while(decimal>0) {
    
        *(octal+i) = decimal%8;
        decimal = decimal/8;
        i++;
        length=i;
        if(i>1) octal = realloc(octal,i);
    }
    
    i=0;
    ln = length;
    length = length-1;
    
    while(i<length) {
        int temp = *(octal+i);
        *(octal+i) = *(octal+length);
        *(octal+length) = temp;
        i++;
        length--;
    }
    
    *(octal+ln) = -1;
    return octal;

}

int toDecimal(char* number, int radix) {

    static int decimal=0;
    int i=0,j=0,length=strlen(number),base=1;
    
    if(radix==16) {
        
        decimal = 0;    
        for(i=length-1;i>=0;i--) {
            if(*(number+i)>='0' && *(number+i)<='9')  {
                decimal += (*(number+i) - 48)*base;
                base*=radix;
            }
            
            else {
            
                decimal += (*(number+i) - 55)*base;
                base*=radix;
            }
        }
        
    }
    
    else {
    
        decimal = 0; 
        for(i=length-1;i>=0;i--) {
            decimal += (*(number+i) - 48)*base;
            base*=radix;
        }
    
    }
    
    return decimal;

}
