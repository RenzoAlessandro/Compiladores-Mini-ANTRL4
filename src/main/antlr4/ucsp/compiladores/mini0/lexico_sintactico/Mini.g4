grammar Mini;



programa : NL* (decl) decl* 
         ;

decl     : funcion | global
         ;

nl       : NL NL*
         ;

global   : declvar nl
         ; 

funcion  : 'fun' ID '(' params ')' (':' tipo)? nl
           bloque
         'end' NL
         ;

bloque   : (declvar nl)*
          (comando nl)*
         ;
 
params   : /*vacio*/ | parametro (',' parametro)*
         ;

parametro: ID ':' tipo
         ;

tipo     : tipobase | '[' ']' tipo
         ;

tipobase : 'int' | 'bool' | 'char' | 'string'
         ;

declvar  : ID ':' tipo
         ;

comando : cmdif | cmdwhile | cmdasign | cmdreturn | llamada
        ;

cmdif :  'if' exp nl
          bloque
          ('else' 'if' exp nl bloque)*
          ('else' nl bloque)?
          'end'
         ;

cmdwhile  : 'while' exp nl
                bloque
              'loop' 
            ;

cmdasign  : var '=' exp
            ;
llamada   :  ID '(' listaexp ')';

listaexp  : /*vacio*/ | exp ( ',' exp )*;

cmdreturn : 'return' exp | 'return';

var       : ID | var '[' exp ']';

exp       :  LITNUMERAL | LITSTRING | 
            TRUE | FALSE | 
            var | 'new' '[' exp ']' tipo |
            '(' exp ')' | llamada | 
            exp '+' exp | exp '-' exp | 
            exp '*' exp | exp '/' exp | 
            exp '>' exp | exp '<' exp | 
            exp '>=' exp | exp '<=' exp |
            exp '=' exp | exp '<>' exp | 
            exp 'and' exp | exp 'or' exp | 
            'not' exp | '-' exp
          ;



PALABRAS_RESERVADAS :  'if' | 'else' | 'end' | 'while' | 'loop' | 'fun' |
                       'return' | 'new' | 'string' | 'int' | 'char' |
                       'bool' | 'true' | 'false' | 'and' | 'or' | 'not'
                    ;

LITNUMERAL :  ( ('+'|'-')?('0'..'9') | (('0x') ('0'..'9'|'a'..'f'|'A'..'F')+) )+
        ;

ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
         ;

LITSTRING : '"' ( ('\\'|'\n'|'\r'|'\t') | ~('\'') )* '"'
        ;

COMENTARIO_1  :   '//' (~('\n'|'\r'))*
              ;
COMENTARIO_2  : '/*' .*? '*'+ '/'
              ;

NL : ( ' '
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
