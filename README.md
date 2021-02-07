# COMPILADORES: ANALISADOR LÉXICO, SINTÁCTICO & SEMÁNTICO

## Creditos 
Este proyecto es programado por:
* [Renzo Sucari](https://github.com/RenzoAlessandro)
* [Anabel Paredes](https://github.com/anabeL19)
* [Gino Liza](https://github.com/gino-lr)

Docente: 
* [Marcela Quispe Cruz]

## Contribución
Analizador léxico, sintáctico y semántico para el Lenguaje Mini-0 

## Tecnologías Usados
1. Apache NetBeans 12.2
2. ANTLR (ANother Tool for Language Recognition)

## IMPLEMENTACIÓN DE UN ANALIZADOR EN EL LENGUAJE MINI-0
#### ENTRADA: LENGUAJE MINI-0 (Programa1.txt)
```
/* 
    PRUEBA DEL EJEMPLO ALGUMA - IFs
*/
numero1:int
numero2:int
numero3:int
numero3:string
// numero4:int
aux:int
// Coloca 3 números en orden creciente

fun Main():string
    if numero1 > numero2
        aux = 2+3-4+5-6*5-1
        numero2 = numero1
        numero1 = aux
    end
    
    if numero1 > numero3 and numero2 <= numero4 and numero1 > 3 or numero2 <> numero4
        aux = (numero3)
        numero3 = numero1
        numero1 = aux
    end

    if numero2 > numero3
        aux = numero3
        numero3 = numero2
        numero2 = aux
    end

    return "FIN" + 1

end
```
#### SALIDA: ANÁLISIS DEL LENGUAJE MINI-0 (Programa1.txt)
<img src= "ImgMiniAnalizador.PNG">

