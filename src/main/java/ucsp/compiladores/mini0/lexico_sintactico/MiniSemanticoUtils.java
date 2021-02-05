/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucsp.compiladores.mini0.lexico_sintactico;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import ucsp.compiladores.mini0.lexico_sintactico.MiniParser;
import ucsp.compiladores.mini0.lexico_sintactico.MiniParser.ExpContext;
import ucsp.compiladores.mini0.lexico_sintactico.MiniParser.VarContext;
        

public class MiniSemanticoUtils {
    
    public static List<String> erroresSemanticos = new ArrayList<>();
    
    public static void adicionarErrorSemantico(Token t, String mensaje) {
        int linea = t.getLine();
        int columna = t.getCharPositionInLine(); 
        erroresSemanticos.add(String.format("Error Semántico %d:%d - %s", linea, columna, mensaje));
    }

    public static TablaDeSimbolos.TipoAlguma verificarTipo(TablaDeSimbolos tabla, MiniParser.ExpContext ctx) {
        if (ctx.LITNUMERAL() != null) {
            return TablaDeSimbolos.TipoAlguma.ENTERO;
        }
        if (ctx.LITSTRING() != null) {
            return TablaDeSimbolos.TipoAlguma.STRING; 
        }
        
        if (ctx.var().ID() != null) {
            String nombreVar = ctx.var().ID().getText(); 
            if (!tabla.existe(nombreVar)) {
                adicionarErrorSemantico(ctx.var().ID().getSymbol(), "Variable " + nombreVar + " no fue declarada antes de uso"); //getSymbol retorna el token que tiene línea y columna
                return TablaDeSimbolos.TipoAlguma.INVALIDO; 
            }
            return verificarTipo(tabla, nombreVar); 
        }
        
        return TablaDeSimbolos.TipoAlguma.INVALIDO; 
    }
    
    
    public static TablaDeSimbolos.TipoAlguma verificarTipo(TablaDeSimbolos tabla, String nombreVar) {
        return tabla.verificar(nombreVar); 
    }
    
}
