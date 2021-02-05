/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucsp.compiladores.mini0.lexico_sintactico;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ucsp.compiladores.mini0.lexico_sintactico.MiniParser.ProgramaContext;

/**
 *
 * @author renzoalesandro
 */
public class Principal {
    
    public static void main(String args[]) throws IOException {
        System.out.println("Inicio del Compilador.");
        CharStream cs = CharStreams.fromFileName(args[0]);
        MiniLexer lexer = new MiniLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniParser parser = new MiniParser(tokens);
        ProgramaContext arbol = parser.programa();
        MiniSemantico as = new MiniSemantico();
        as.visitPrograma(arbol);
        MiniSemanticoUtils.erroresSemanticos.forEach((s) -> System.out.println(s));
        System.out.println("Finalizo el Compilador.");
        
    }
    
}
