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
 * @author USER
 */
public class TablaDeSimbolos {
    public enum TipoMini {
        ENTERO,
        BOOL,
        CHAR,
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

    //creamos una sola tabla de simbolos( no maneja ambitos  - porque Algumar no maneja)
    private final Map<String, EntradaTablaDeSimbolos> tabla;

    public TablaDeSimbolos() {
       this.tabla = new HashMap<>();
    }

    public void adicionar(String nombre, TipoMini tipo) {
        //adiciona un elemento a la tabla de simbolos
       tabla.put(nombre, new EntradaTablaDeSimbolos(nombre, tipo));
    }

    public boolean existe(String nombre) {
        //verificar si existe este 'nombre' si exite el valor en la tabla de simbolos
       return tabla.containsKey(nombre);
    }

    public TipoMini verificar(String nombre) { //retorna tipo de variable
       return tabla.get(nombre).tipo;
    }
}
