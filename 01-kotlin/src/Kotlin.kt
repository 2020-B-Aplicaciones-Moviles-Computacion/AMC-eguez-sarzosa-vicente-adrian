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

    // Any All -> Condicion -> Boolean
    // OR <-> AND
    // OR = Any
    // OR (FALSO - TODOS SON FALSOS ES FALSO)
    // OR (TRUE - UNO ES TRUE YA ES TRUE)
    // AND = All
    // AND (FALSO - UNO ES FALSO YA ES FALSO)
    // AND (TRUE - TODOS SON TRUE ES TRUE)

    val respuestaAny: Boolean = arregloDinamico
            .any { valorActualIteracion ->
                return@any (valorActualIteracion > 5)
            }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
            .all { valorActualIteracion ->
                return@all valorActualIteracion > 5
            }
    println(respuestaAll) // false

    // REDUCE
    // 1) Devuelve el acumulado
    // 2) En que valor empieza
    // [1,2,3,4,5]
    //  0 = 0 + 1
    //  1 = 1 + 2
    //  3 = 3 + 3
    //  6 = 6 + 4
    //  10 = 10 + 5
    //  15

    val respuestaFilter: Int = arregloDinamico
            .reduce { // acumulado = 0
                acumulado, valorActualIteracion ->
                return@reduce acumulado + valorActualIteracion
            }
    println(respuestaFilter) // 78

    val respuestaReduceFold = arregloDinamico
            .fold(
                    100,
                    { acumulado, valorActualIteracion ->
                        return@fold acumulado - valorActualIteracion
                    }
            )
    println(respuestaReduceFold) // 22
    // arregloMutable.fold (empieza desde el principio
    // arregloMutable.foldRight (empieza desde el final
    // arregloMutable.reduce (empieza desde el final
    // arregloMutable.reduceRight (empieza desde el final

    // OPERADORES
    // forEach -> Unit (void)
    // map -> Arreglo
    // filter -> Arreglo
    // all -> Booleano
    // any -> Booleano
    // reduce -> Valor
    // fold -> Valor

    val vidaActual: Double = arregloDinamico
            .map { it * 2.3 } // arreglo
            .filter { it > 20 } // arreglo
            .fold(100.00, { acc, i -> acc - i }) // valor
            .also { println(it) } // ejecutar codigo extra
    println("Valor vida actual ${vidaActual}")  // 3.4

    val ejemploUno = Suma(1, 2)
    // val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null, 2)
    // val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1, null)
    // val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma(null, null)
    // val ejemploCuatro = Suma(null,null)
    println(ejemploUno.sumar())
    println(Suma.historialSumas)
    println(ejemploDos.sumar())
    println(Suma.historialSumas)
    println(ejemploTres.sumar())
    println(Suma.historialSumas)
    println(ejemploCuatro.sumar())
    println(Suma.historialSumas)


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


abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor( // Constructor primario
            uno: Int,
            dos: Int
    ) { // Bloque de codigo del constructor primario
        // this.numeroUno
        numeroUno = uno
        // this.numeroDos
        numeroDos = dos
        println("inicializar algunas cosas dentro de la clase")
    }
}

// instancia.numeroUno
// instancia.numeroDos
abstract class Numeros( // Construtor primario
        protected var numeroUno: Int,
        protected var numeroDos: Int,
) {
    init { // bloque de codigo del constructor primario
        println("inicializar algunas cosas dentro de la clase")
    }
}

class Suma(
        uno: Int, // parametros
        dos: Int // parametros
) : Numeros(uno, dos) {
    init {
        // this.numeroUno
        // this.numeroDos
        // X -> this.uno -> NO EXISTEN
        // X -> this.dos -> NO EXISTEN
    }

    constructor( //  Segundo constructor
            uno: Int?, // parametros
            dos: Int // parametros
    ) : this( // llamada constructor primario
            if (uno == null) 0 else uno,
            dos
    ) {

    }

    constructor( //  Tercer constructor
            uno: Int, // parametros
            dos: Int? // parametros
    ) : this( // llamada constructor primario
            uno,
            if (dos == null) 0 else dos
    ) {

    }

    constructor( //  Cuarto constructor
            uno: Int?, // parametros
            dos: Int? // parametros
    ) : this( // llamada constructor primario
            if (uno == null) 0 else uno,
            if (dos == null) 0 else dos
    ) {

    }

    public fun sumar(): Int {
        // this.numeroUno
        // this.numeroDos
        val total: Int = numeroUno + numeroDos
        Suma.agregarHistorial(total)
        return total
    }

    // SINGLETON
    companion object { // Metodos y Propiedades
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(nuevaSuma: Int) {
            this.historialSumas.add(nuevaSuma)
        }
    }
}

class BaseDeDatos() {
    companion object {
        val datos = arrayListOf<Int>()
    }
}
// BaseDeDatos.datos























