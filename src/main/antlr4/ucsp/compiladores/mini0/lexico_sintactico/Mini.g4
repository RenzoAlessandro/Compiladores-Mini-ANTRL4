grammar Mini;

/*------------------------------------------------------------------

 * REGLAS DE SINTÁXIS

 *------------------------------------------------------------------*/

programa  : NL* decl (decl)* EOF
          ;

decl      : funcion | global
          ;

nl        : NL NL*
          ;

global    : declvar nl
          ; 

funcion   : 'fun' ID '(' params? ')' (':' tipo)? nl
             { System.out.println("    FUNCION: Fun="+$ID.text+", Tipo="+$tipo.text); }
            bloque
            'end' NL
          ;

bloque    : (declvar nl)*
            (cmd=comando { System.out.println("    COMANDO DE TIPO: "+$cmd.tipoComando); } nl)*
          ;
 
params    : parametro (',' parametro)*
          ;

parametro : ID ':' tipo
          ;

tipo      : tipobase | '[' ']' tipo
          ;

tipobase  : 'int' | 'bool' | 'char' | 'string'
          ;

declvar   : ID ':' tipo
            { System.out.println("    DECLARACION: Var="+$ID.text+", Tipo="+$tipo.text); }
          ;

comando   returns [ String tipoComando ]
          : cmdif       { $tipoComando = "IF"; }
            | cmdwhile  { $tipoComando = "WHILE"; }
            | cmdasign  { $tipoComando = "ASIGNACION"; }
            | cmdreturn { $tipoComando = "RETURN"; }
            | llamada   { $tipoComando = "LLAMADA"; }
          ;

cmdif     : 'if' exp nl
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
            { System.out.println("       "+$var.text+" = "+$exp.text); }
          ;

llamada   :  ID '(' listaexp? ')'
          ;

listaexp  : exp (',' exp)*
          ;

cmdreturn : 'return' exp | 'return'
          ;

var       : ID | var '[' exp ']'
          ;

/*  prioridad de los operadores   */
exp       : LITNUMERAL 
          | LITSTRING 
          | TRUE | FALSE 
          | var 
          | 'new' '[' exp ']' tipo 
          | '(' exp ')' 
          | llamada
          | exp '+' exp 
          | exp '-' exp 
          | exp '*' exp 
          | exp '/' exp 
          | exp '>' exp 
          | exp '<' exp 
          | exp '>=' exp 
          | exp '<=' exp 
          | exp '=' exp 
          | exp '<>' exp 
          | exp 'and' exp 
          | exp 'or' exp 
          | 'not' exp | '-' exp
          ;

expArit   : termArit (OP_ARIT1 termArit)*;
termArit  : factorArit (OP_ARIT2 factorArit)*;
factorArit: LITNUMERAL 
          | LITSTRING 
          | TRUE | FALSE 
          | var 
          | 'new' '[' exp ']' tipo 
          | llamada
          | '(' expArit ')'
          ; 

/*  corto - circuito  */
expRel    : OP_LOG termRel | termRel (OP_LOG termRel)*;
termRel   : expArit OP_REL expArit | '(' expRel ')'
          ;        

/*------------------------------------------------------------------

 * REGLAS LÉXICOS

 *------------------------------------------------------------------*/

TRUE  : 'true';
FALSE : 'false';

PALABRAS_RESERVADAS :  'if' | 'else' | 'end' | 'while' | 'loop' | 'fun' |
                       'return' | 'new' | 'string' | 'int' | 'char' |
                       'bool' | TRUE | FALSE | 'and' | 'or' | 'not'
                    ;

LITNUMERAL :  ( ('+'|'-')?('0'..'9') | (('0x') ('0'..'9'|'a'..'f'|'A'..'F')+) )+
        ;

ID      : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
        ;

LITSTRING : '"' ( ('\\'|'\n'|'\r'|'\t') | ~('\'') )* '"'
        ;

BLOCKCOMMENT
        : '/*' .*? '*/' WS {skip();} 
        ;

LINECOMMENT
        : '//' ~('\n'|'\r')* '\r'? '\n' {skip();}
        ;

NL      : ('\n')+
        ;

WS      : ( ' ' |'\t' | '\r' | NL) {skip();} 
        ;

OP_REL  : '>' | '>=' | '<' | '<=' | '=' | '<>' 
        ;

OP_LOG  : 'and' | 'or' | 'not' | '-'
        ;

OP_ARIT1 
        : '+' | '-' 
        ;

OP_ARIT2 
        : '*' | '/'
        ;

PUNTUACION : '(' | ')' | ',' | ':' | '[' | ']'
        ;