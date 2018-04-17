#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include "lib_numsys.h"

void main() {

    int decimal,i,j,chk=0,radix;
    char end;
    
    printf("\n\n*****Number System Covertor Using Static Library lib_numsys.h*****");
    printf("\n\nFunctions available:\n\n");
    printf("1.toBinary(int) - return int*\n2.toHexadecimal(int) - return char*\n3.toOctal(int) - return int*\n4.toDecimal(char*) - return int\n5.e - exit\n");
    
    do {
        printf("Enter choice: ");
        scanf("%d",&chk);
        if( chk==1 || chk==2 || chk==3 ) {
        
            printf("Enter decimal number: ");
            scanf("%d",&decimal);
            
            if(chk==1) {
                
                int* binary;
                binary = toBinary(10);
                //int length = sizeof(binary)/sizeof(int);
                printf("Binary Representation: ");
                i=0;
                while(*(binary+i)!=-1) {
                
                    printf("%d",*(binary+i));
                    i++;
                 
                }
                printf("\n");
                
            }
            
            else if(chk==2) {
                
                char* hex;
                hex = toHexadecimal(decimal);
                //int length = strlen(hex);
                printf("Hexadecimal Representation: ");
                i=0;
                while(*(hex+i)!='\0') {
                
                    printf("%c",*(hex+i));
                    i++;
                 
                }
                printf("\n");
            }
            
            else if(chk==3) {
                
                int* octal;
                octal = toOctal(decimal);
                //int length = sizeof(octal)/sizeof(int);
                printf("Octal Representation: ");
                i=0;
                while(*(octal+i)!=-1) {
                
                    printf("%d",*(octal+i));
                    i++;
                 
                }
                printf("\n");
            }        
        }
        else if(chk==4) {
            
            char todec[100],ch;
            i=0;
            printf("Enter number: ");
            scanf("%s",&todec);
            printf("Enter radix: ");
            scanf("%d",&radix);   
            decimal = toDecimal(todec,radix);
            printf("Decimal Representation: %d\n",decimal);         
            decimal = 0;  
            for(i=0;i<100;i++) todec[i]='\0';          
        }
        
     }while(chk!=5);
    

}
