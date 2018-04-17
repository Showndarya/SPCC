%{

#include "y.tab.h"

%}

%%

[ /t/n]                            { ; }
"select"                          { return Select; }
"from"                            { return from; }
"distinct"                        { return distinct; }
"where"                           { return where; }
"like"                            { return like; }
"and"                             { return and; }
"or"                              { return or; }
[0-9]+                          { return number; }
[A-Za-z]([A-Za-z]|[0-9])*       { return id; }
"<="                            { return le; }  
">="                            { return ge; }
"=="                            { return eq; }
"!="                            { return ne; }
.                               { return yytext[0]; }

%%

int yywrap(void) { return 1; }
