lexer grammar MiniLexer;

PALABRAS_RESERVADAS :  'if' | 'else' | 'end' | 'while' | 'loop' | 'fun' |
                       'return' | 'new' | 'string' | 'int' | 'char' |
                       'bool' | 'true' | 'false' | 'and' | 'or' | 'not'
                    ;

NUMERAL :  ( ('+'|'-')?('0'..'9') | (('0x') ('0'..'9'|'a'..'z'|'A'..'Z')) )+
        ;

IDENTIFICADORES : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
         ;

STRINGS : '"' ( ('\\'|'\n'|'\r'|'\t') | ~('\'') )* '"'
        ;

fragment
ESC_SEQ  :  '\\\''
         ;
COMENTARIO_1  :   '//' (~('\n'|'\r'))*
              ;
COMENTARIO_2  : '/*' .*? '*'+ '/'
              ;

WS : ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) ->skip
   ;
OP_REL  : '>' | '>=' | '<' | '<=' | '=' | '<>' 
        ;
OP_ARIT : '+' | '-' | '*' | '/'
        ;

PUNTUACION : '(' | ')' | ',' | ':' | '[' | ']'
        ;
