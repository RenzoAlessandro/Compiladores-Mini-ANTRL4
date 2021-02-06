/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucsp.compiladores.mini0.lexico_sintactico;

/**
 *
 * @author USER
 */
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

import ucsp.compiladores.mini0.lexico_sintactico.MiniParser.TermAritContext;
import ucsp.compiladores.mini0.lexico_sintactico.MiniParser.FactorAritContext;

public class MiniSemanticoUtils {
    
    public static List<String> erroresSemanticos = new ArrayList<>();

    public static void adicionarErrorSemantico(Token t, String mensaje) {
    //dentro del token se tiene la linea y columna donde se encontró el error
        int linea = t.getLine();
        int columna = t.getCharPositionInLine();
        erroresSemanticos.add(String.format("Error %d:%d - %s", linea, columna, mensaje));
    }
    
    public static TablaDeSimbolos.TipoMini verificarTipo(TablaDeSimbolos tabla,
    MiniParser.ExpAritContext ctx) {
        TablaDeSimbolos.TipoMini ret = null;
        for (TermAritContext ta : ctx.termArit()) {
            TablaDeSimbolos.TipoMini aux = verificarTipo(tabla, ta);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TablaDeSimbolos.TipoMini.INVALIDO) {
                adicionarErrorSemantico(ctx.start, "Expresión " + ctx.getText() + " contiene tipos incompatibles");
                ret = TablaDeSimbolos.TipoMini.INVALIDO;
            }
        }
        return ret;
    }
    
    public static TablaDeSimbolos.TipoMini verificarTipo(TablaDeSimbolos tabla,
   MiniParser.TermAritContext ctx) {
        TablaDeSimbolos.TipoMini ret = null;
        for (FactorAritContext fa : ctx.factorArit()) {
            TablaDeSimbolos.TipoMini aux = verificarTipo(tabla, fa);
            if (ret == null) {
                //tipo del 1er o unico término aritmetico
                ret = aux;
            } else if (ret != aux && aux != TablaDeSimbolos.TipoMini.INVALIDO) {
                adicionarErrorSemantico(ctx.start, "Término " + ctx.getText() + " contiene tipos incompatibles");
                ret = TablaDeSimbolos.TipoMini.INVALIDO;
            }
        }
        return ret;
    }

    public static TablaDeSimbolos.TipoMini verificarTipo(TablaDeSimbolos tabla,
   MiniParser.FactorAritContext ctx) {
    //verifica el tipo de Factor Aritmetico     
        if (ctx.LITNUMERAL() != null) {
            return TablaDeSimbolos.TipoMini.ENTERO;
        }
        if (ctx.LITSTRING() != null) {
            return TablaDeSimbolos.TipoMini.STRING;
        }
        if (ctx.TRUE()!= null) {
            return TablaDeSimbolos.TipoMini.BOOL;
        }
        if (ctx.FALSE()!= null) {
            return TablaDeSimbolos.TipoMini.BOOL;
        }
        if (ctx.var() != null) {
            String nombreVar = ctx.var().getText();
            if (!tabla.existe(nombreVar)) {
                adicionarErrorSemantico(ctx.var().ID().getSymbol(), "Variable " + nombreVar +
                " no fue declarada antes de uso"); //getSymbol retorna el token que tiene línea y columna
                 return TablaDeSimbolos.TipoMini.INVALIDO;
            }
            return verificarTipo(tabla, nombreVar);
        }
        //si no fuera ninguno de los tipos de arriba, sólo puede ser una expresión
        // entre paréntesis
        return verificarTipo(tabla, ctx.expArit());
    }

    public static TablaDeSimbolos.TipoMini verificarTipo(TablaDeSimbolos tabla, String
   nombreVar) {
    // verificar tipo nombre-variable     
        return tabla.verificar(nombreVar);
    }
    
}
