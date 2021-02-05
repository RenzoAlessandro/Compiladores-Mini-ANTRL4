/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucsp.compiladores.mini0.lexico_sintactico;

import ucsp.compiladores.mini0.lexico_sintactico.MiniParser;
import ucsp.compiladores.mini0.lexico_sintactico.MiniBaseVisitor;
import ucsp.compiladores.mini0.lexico_sintactico.TablaDeSimbolos.TipoAlguma;


public class MiniSemantico extends MiniBaseVisitor<Void> {
    
    TablaDeSimbolos tabla;
    
    @Override
    public Void visitPrograma(MiniParser.ProgramaContext ctx) {
        tabla = new TablaDeSimbolos();
        return super.visitPrograma(ctx); 
    }
    
    @Override
    public Void visitDeclvar(MiniParser.DeclvarContext ctx) {
        String nombreVar = ctx.ID().getText(); 
        String strTipoVar = ctx.tipo().tipobase().getText(); 
        TipoAlguma tipoVar = TipoAlguma.INVALIDO; 
        switch (strTipoVar) {
            case "int":
                tipoVar = TipoAlguma.ENTERO;
                break;
            case "string":
                tipoVar = TipoAlguma.STRING;
                break;
            default:
                break;
        }
        // Verificar si la variable ya fue declarada 
        if (tabla.existe(nombreVar)) {
            MiniSemanticoUtils.adicionarErrorSemantico(ctx.ID().getSymbol(), "Variable " + nombreVar + " ya existe");
        } else {
            tabla.adicionar(nombreVar, tipoVar);
        }
        return super.visitDeclvar(ctx); 
    }
    
    @Override
    public Void visitCmdasign(MiniParser.CmdasignContext ctx) {
        TipoAlguma tipoExpresion = MiniSemanticoUtils.verificarTipo(tabla, ctx.exp());
        if (tipoExpresion != TipoAlguma.INVALIDO) {
            String nombreVar = ctx.var().ID().getText();
            if (!tabla.existe(nombreVar)) {
                MiniSemanticoUtils.adicionarErrorSemantico(ctx.var().ID().getSymbol(),"Variable "+ nombreVar + " no fue declarada antes de uso");
            } else {
                TipoAlguma tipoVariable = MiniSemanticoUtils.verificarTipo(tabla, nombreVar);
                if (tipoVariable != tipoExpresion && tipoExpresion != TipoAlguma.INVALIDO) {
                    MiniSemanticoUtils.adicionarErrorSemantico(ctx.var().ID().getSymbol(),"Tipo de la variable" + nombreVar + " no es compatible con el tipo de la expresión");
                }
            }
        }
        return super.visitCmdasign(ctx);
    }
    
    @Override
    public Void visitExp(MiniParser.ExpContext ctx) {
        MiniSemanticoUtils.verificarTipo(tabla, ctx);
        return super.visitExp(ctx); 
    }
    
}
