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
import ucsp.compiladores.mini0.lexico_sintactico.TablaDeSimbolos.TipoMini;

public class MiniSemantico extends MiniBaseVisitor<Void>{
    
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
        TipoMini tipoVar = TipoMini.INVALIDO;
        switch (strTipoVar) {
            case "int":
                tipoVar = TipoMini.ENTERO;
                break;
            case "bool":
                tipoVar = TipoMini.BOOL;
                break;
            case "char":
                tipoVar = TipoMini.CHAR;
                break;
            case "string":
                tipoVar = TipoMini.STRING;
                break;
            default:
                break;
        }
        
        if (tabla.existe(nombreVar)) {
            MiniSemanticoUtils.adicionarErrorSemantico(ctx.ID().getSymbol(), "Variable" + nombreVar + " ya existe");
        } else {
            tabla.adicionar(nombreVar, tipoVar);
        }
        return super.visitDeclvar(ctx);
    }
    
    @Override
    public Void visitCmdasign(MiniParser.CmdasignContext ctx) {
        TipoMini tipoExpresion = MiniSemanticoUtils.verificarTipo(tabla, ctx.expArit());
        if (tipoExpresion != TipoMini.INVALIDO) {
            String nombreVar = ctx.var().ID().getText();
            MiniSemanticoUtils.adicionarErrorSemantico(ctx.var().ID().getSymbol(),
               "Variable " + nombreVar + " INGRESEEE");
            if (!tabla.existe(nombreVar)) {
                MiniSemanticoUtils.adicionarErrorSemantico(ctx.var().ID().getSymbol(),
               "Variable " + nombreVar + " no fue declarada antes de uso");
            } else {
                TipoMini tipoVariable = MiniSemanticoUtils.verificarTipo(tabla,
               nombreVar);
                if (tipoVariable != tipoExpresion && tipoExpresion != TipoMini.INVALIDO) {
                    MiniSemanticoUtils.adicionarErrorSemantico(ctx.var().ID().getSymbol(),
               "Tipo de la variable " + nombreVar + " no es compatible con el tipo de la expresión");
                }
            }
        }
        return super.visitCmdasign(ctx);
    }
    /*
    // verificación adicional visitComandoEntrada que es de la lectura
    @Override
    public Void visitComandoEntrada(MiniParser.ComandoEntradaContext ctx) {
        // si tiene la variable, el lexema
        String nombreVar = ctx.VARIABLE().getText();
        if (!tabla.existe(nombreVar)) {
            MiniSemanticoUtils.adicionarErrorSemantico(ctx.VARIABLE().getSymbol(), "Variable" + nombreVar + " no fue declarada antes de uso");
        }
        return super.visitComandoEntrada(ctx);
    }
    */
    @Override
    public Void visitExpArit(MiniParser.ExpAritContext ctx) {
        MiniSemanticoUtils.verificarTipo(tabla, ctx);
        return super.visitExpArit(ctx);
    }
    
}
