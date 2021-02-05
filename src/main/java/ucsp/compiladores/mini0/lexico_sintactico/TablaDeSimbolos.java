/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucsp.compiladores.mini0.lexico_sintactico;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author renzoalesandro
 */
public class TablaDeSimbolos {
    
    public enum TipoMini {
        ENTERO,
        STRING,
        INVALIDO
    }
    
    class EntradaTablaDeSimbolos { 
        String nombre;
        TipoMini tipo;
        
        private EntradaTablaDeSimbolos(String nombre, TipoMini tipo) { 
            this.nombre = nombre;
            this.tipo = tipo;
        } 
    }
    
    private final Map<String, EntradaTablaDeSimbolos> tabla;
    
    public TablaDeSimbolos() {
        this.tabla = new HashMap<>();
    }
    
    public void adicionar(String nombre, TipoMini tipo) { 
        tabla.put(nombre, new EntradaTablaDeSimbolos(nombre, tipo));
    }
    
    public boolean existe(String nombre) { 
        return tabla.containsKey(nombre);
    }
    
    public TipoMini verificar(String nombre) { //retorna tipo de variable 
        return tabla.get(nombre).tipo;
    } 
    
}
