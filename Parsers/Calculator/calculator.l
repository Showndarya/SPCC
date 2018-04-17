%{

#include "y.tab.h"

%}

%%

"print"                             { return print; }
"exit_now"                          { return exit_now;}
[a-zA-Z]                            { yylval.id = yytext[0];/*printf("sym");*/ return sym; }
[0-9]+                              { yylval.num = atoi(yytext); /*printf("number");*/ return number; }
[ \t\n]                             { ; }
[-+*/%=()?]                          { /*printf("\n%d\n",yytext[0]);*/return yytext[0]; }
.                                   { yyerror("Error in lexical analysis!"); }

%%

int yywrap(void) { return 1; }
