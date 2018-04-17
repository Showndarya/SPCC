%{

void yyerror (char *s);
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
int variable[52];

int getValue(char symbol);
void valueUpdate(char symbol, int value);

%}

%union {int num; char id;}

%start line_start

%token print
%token exit_now
%token <num> number
%token <id> sym

%type <num> line_start exp term 
%type <id> assign

%left '+' '-'
%left '*' '/' '%'

%right '='

%%

line_start  :  assign '?'                       { ; }
               | exit_now '?'                   { printf("Thank you for using me..\n");exit(EXIT_SUCCESS); }
               | print exp '?'                  { printf("%d\n",$2); }
               | line_start assign '?'          { ; }
               | line_start print exp '?'       { printf("%d\n",$3); }
               | line_start exit_now '?'        { printf("Thank you for using me..\n");exit(EXIT_SUCCESS); }
               | exp '?'                        { printf("%d\n",$1); }
               | line_start exp '?'             { printf("%d\n",$2); }
            ;
            
assign      :   sym '=' exp                     { valueUpdate($1,$3); }
            ;
            
exp         :   term                            { $$ = $1; }
                | exp '+' term                  { $$ = $1 + $3; }
                | exp '-' term                  { $$ = $1 - $3; }
                | exp '*' term                  { $$ = $1 * $3; }
                | exp '/' term                  { $$ = $1 / $3; }
                | exp '%' term                  { $$ = $1 % $3; }
                | '(' exp ')'                   { $$ = $2; }
            ;
            
term        :   number                          { $$ = $1; }
                | sym                           { $$ = getValue($1); }
            ;

%%

int getVariableValue(char token)
{
    int vv = -1;
    if(islower(token)) vv = token - 'a' + 26;
    else if(isupper(token)) vv = token - 'A';
    return vv;
}   

int getValue(char symbol)
{
    int id = getVariableValue(symbol);
    return variable[id];
}

void valueUpdate(char symbol, int value)
{
    int id = getVariableValue(symbol);
    variable[id] = value;
}

int main(void)
{
    int i;
    printf("\n\n*****Simple Calculator*****\n\nOperators available: + - * / %\nVariables available: A-Z a-z\nPrint variable values: print <variable_name>\nExit: exit_now\nEnd statement with: ?\n\n");
    for(i=0;i<52;i++) variable[i] = 0;
    return yyparse();
}

void yyerror (char *s) { fprintf (stderr, "%s\n", s); } 















