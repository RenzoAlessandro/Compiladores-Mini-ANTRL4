lexer grammar MiniLexer;

PALABRA_CLAVE :     'DECLARACIONES' | 'ALGORITMO' | 'INT' | 'REAL' | 'ASIGNAR' |
                    'A' | 'LEER' | 'IMPRIMIR' | 'SI' | 'ENTONCES'
                    | 'MIENTRAS' | 'INICIO' | 'FIN' | 'Y' | 'O'
;
NUMINT : ('+'|'-')?('0'..'9')+
;
NUMREAL : ('+'|'-')?('0'..'9')+ ('.' ('0'..'9')+)?
;
VARIABLE : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*
;
fragment
ESC_SEQ :  '\\\'';
COMENTARIO :   '%' ~('\n'|'\r')* '\r'? '\n' ->skip
;
WS : ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) ->skip
;
OP_REL : '>' | '>=' | '<' | '<=' | '<>' | '='
;
OP_ARIT : '+' | '-' | '*' | '/'
;
DELIM : ':'
;
ABREPAR : '('
;
CIERRAPAR : ')'
;