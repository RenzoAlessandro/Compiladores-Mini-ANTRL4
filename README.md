# COMPILADORES: ANALISADOR LÉXICO + SINTÁCTICO

## Creditos 
Este proyecto es programado por:
* [Renzo Sucari](https://github.com/RenzoAlessandro)
* [Anabel Paredes](https://github.com/anabeL19)

Docente: 
* [Marcela Quispe Cruz]

## Contribución
Analizador léxico + sintáctico para el Lenguaje Mini-0 

## Tecnologías Usados
1. Apache NetBeans 12.2
2. ANTLR (ANother Tool for Language Recognition)

#### IMPLEMENTACIÓN DE UN ANALIZADOR LÉXICO + SINTÁCTICO
```
:DECLARACIONES
argumento:INT
factorial:INT

:ALGORITMO
% Calcula el factorial de un número entero
LEER argumento
ASIGNAR argumento A factorial
SI argumento = 0 ENTONCES ASIGNAR 1 A factorial
MIENTRAS argumento>=10.0
    INICIO
        ASIGNAR factorial * (argumento - 1) A factorial 
        ASIGNAR argumento - 1 A argumento
    FIN
IMPRIMIR factorial

```
