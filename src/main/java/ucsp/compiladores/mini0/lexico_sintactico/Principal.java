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

/**
 *
 * @authors Anabel Paredes & Renzo Alesandro
 */
public class Principal {
    public static void main(String[] args){
        try {
            // args[0] es el primer argumento de la linea de comando 
            CharStream cs = CharStreams.fromFileName(args[0]);
            System.out.println("Inicio del Programa.");
            MiniLexer lexer = new MiniLexer(cs);
            
            /*
            Token t = null;
            while ((t = lexer.nextToken()).getType() != Token.EOF) {
                System.out.println("<" +MiniLexer.VOCABULARY.getDisplayName(t.getType()) + "," + t.getText() + ">");
            }
            */
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MiniParser parser = new MiniParser(tokens);
            parser.programa();
            
        } catch (IOException ex) {
        }
        System.out.println("Finalizo el Programa con éxito.");
    }
}