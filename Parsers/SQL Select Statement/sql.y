%{

void yyerror (char *s);
int yylex(void);
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

%}

%start start

%token Select from distinct where like and or number id le ge eq ne

%left and or
%left '<' '>' le ge eq ne

%right '='

%%

start   :  sql_a ';'                  { printf("Valid SQL statement"); }
        |
        ;

sql_a   :  Select attributes from tables sql_b       { ; }
        |  Select distinct attributes from tables sql_b       { ; }
        |  Select distinct attributes from tables       { ; } 
        |  Select attributes from tables       { ; } 
        ;
        
sql_b   :  where condition    { ; }
        ;

attributes :   id ',' attributes
               | '*'
               | id
               ;
               
tables    :    id ',' tables
               | id
               ;
               
condition  :   condition or condition 
               | condition and condition 
               | E
               ;
               
E     :        F '=' F
               | F '<' F 
               | F '>' F  
               | F le F 
               | F ge F
               | F eq F
               | F ne F
               | F or F 
               | F and F 
               | F like F 
               ;
               
F   :          id 
               | number
               ;
        
%%

int main(void)
{
    printf("\n\n*****SQL Parser*****\n\n");
    return yyparse();
}

void yyerror (char *s) { fprintf (stderr, "%s\n", s); } 















