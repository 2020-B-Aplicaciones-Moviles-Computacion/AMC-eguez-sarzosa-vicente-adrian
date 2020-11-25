import java.util.*

fun main() {
    println("Hola mundo")
    // JAVA Int edad = 12;
    var edadProfesor = 31
    // var edadProfesor: Int = 31
    var sueldoProsor = 12.34
    // var sueldoProsor: Double = 12.34
    // Duck Typing

    // MUTABLES / INMUTABLES
    // MUTABLES
    var edadCachorro: Int = 0
    edadCachorro = 1
    edadCachorro = 2
    edadCachorro = 3
    // INMUTABLES
    val numeroCedula = 1818181818
    // numeroCedula = 12

    // Tipos de variables
    val nombreProfesor: String = "AdrianEguez"
    val sueldo: Double = 12.2
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    // Condicionales

    if (true) {
        // Verdadera
    } else {
        // Falso
    }

    when (sueldo) {
        12.2 -> { // Inicio Bloque
            println("Sueldo normal")
        } // Fin bloque
        -12.2 -> println("Sueldo negativo")
        2.0 -> println("Sueldo negativo")
        3.0 -> println("Sueldo negativo")
        -12.2 -> println("Sueldo negativo")
        -12.2 -> println("Sueldo negativo")
        -12.2 -> println("Sueldo negativo")
        -12.2 -> println("Sueldo negativo")
        else -> println("Sueldo no reconocido")
    }

    val sueldoMayorAEstablecido = if (sueldo > 12.2) 500 else 0
    // condicion ? bloque-true : bloque-false
    imprimirNombre("Adrian")
    // imprimirNombre()
    calcularSueldo(1000.00)
    // calcularSueldo(1000.00)
    calcularSueldo(1000.00, 14.00)
    // calcularSueldo(1000.00, 14.00)
    calcularSueldo(1000.00, 12.00, 3)
    // calcularSueldo(1000.00, 12.00, 3)

    // Named Parameters
    calcularSueldo(
            calculoEspecial = 3,
//            12.00,
            sueldo = 1000.00
    )
    calcularSueldo(
            tasa = 14.00,
            calculoEspecial = 3,
            sueldo = 1000.00
    )


    // Arreglo Estáticos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    // arregloEstatico.add(12) -> NO TENEMOS AQUI, NO SE PUEDE MODIFICAR LOS
    //                            Elementos del arreglo
    // Arreglo Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // OPERADORES - Sirven para los arreglos Estaticos y Dinamicos
    arregloEstatico.forEach { }
    arregloDinamico.forEach { }
    // FOREACH
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
            .forEach { valorActualIteracion ->
                println("Valor ${valorActualIteracion}")
            }
    println(respuestaForEach)
    arregloDinamico
            .forEachIndexed { indice, valorActualIteracion ->
                println("Valor ${valorActualIteracion} Indice: ${indice}")
            }
    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados
    val respuestaMap: List<Int> = arregloDinamico
            .map { valorActualIteracion ->
                // Calculo 1
                // Calculo 2
                return@map valorActualIteracion * 10
            }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico
//            .map { i -> i + 15 }
            .map { it + 15 }
    println(respuestaMapDos)

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
            .filter { valorActualIteracion ->
                val mayoresACinco: Boolean = valorActualIteracion > 5
                return@filter mayoresACinco
            }
    println(respuestaFilter)
} // FIN bloque MAIN

fun imprimirNombre(nombre: String): Unit {
    println("Nombre ${nombre}") // Template Strings
}

fun calcularSueldo(
        sueldo: Double, // Requerido
        tasa: Double = 12.00, // Opcionales
        calculoEspecial: Int? = null // Variables que PUEDEN ser null
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?
    if (calculoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) * calculoEspecial
    }
}














