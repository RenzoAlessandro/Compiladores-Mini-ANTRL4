grammar Mini;

/*------------------------------------------------------------------
 * REGLAS LÉXICOS
 *------------------------------------------------------------------*/

TRUE  : 'true';
FALSE : 'false';

PALABRAS_RESERVADAS :  'if' | 'else' | 'end' | 'while' | 'loop' | 'fun' |
                       'return' | 'new' | 'string' | 'int' | 'char' |
                       'bool' | 'true' | 'false' | 'not'
                    ;

LITNUMERAL :  ( ('+'|'-')?('0'..'9') | (('0x') ('0'..'9'|'a'..'f'|'A'..'F')+) )+
        ;

OP_LOG  : 'and' | 'or' | 'not' | '-'
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

OP_ARIT1 
        : '+' | '-' 
        ;

OP_ARIT2 
        : '*' | '/'
        ;

PUNTUACION : '(' | ')' | ',' | ':' | '[' | ']'
        ;

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

cmdif     : 'if' expRel nl
            bloque
            ('else' 'if' expRel nl bloque)*
            ('else' nl bloque)?
            'end'
          ;

cmdwhile  : 'while' expRel nl
            bloque
            'loop' 
          ;

cmdasign  : var '=' expArit
            { System.out.println("       "+$var.text+" = "+$expArit.text); }
          ;

llamada   :  ID '(' listaexp? ')'
          ;

listaexp  : expArit (',' expArit)*
          ;

cmdreturn : 'return' expArit | 'return'
          ;

var       : ID | var '[' expArit ']'
          ;

expArit   : termArit (OP_ARIT1 termArit)*
          ;

termArit  : factorArit (OP_ARIT2 factorArit)*
          ;

factorArit: LITNUMERAL 
          | LITSTRING 
          | TRUE | FALSE 
          | var 
          | 'new' '[' expArit ']' tipo 
          | llamada
          | '(' expArit ')'
          ; 

expRel    : termRel (OP_LOG termRel)*
            { System.out.println("    EXPR REL"); }
          ;

termRel   : expArit OP_REL expArit 
          | '(' expRel ')'
          ;        