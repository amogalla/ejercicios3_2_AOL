import kotlin.math.pow

class Persona(val dni:String) {
    var nombre: String = ""
    var edad: Int = 0
    var sexo: Char = HOMBRE
        set(value) {
            field = comprobarSexo(value)
        }
    var peso: Double = 0.0
    var altura: Double = 0.0

    companion object {
        const val HOMBRE = 'H'
        const val MUJER = 'M'
        const val INFRAPESO = -1
        const val PESO_IDEAL = 0
        const val SOBREPESO = 1
        const val PESO_ERRONEO = 2

        fun infoPesoIdeal(persona: Persona){
            when (persona.calcularIMC()){
                INFRAPESO -> println(" está por debajo de su peso")
                PESO_IDEAL -> println(" está en su peso ideal")
                SOBREPESO -> println(" tiene sobrepeso")
                PESO_ERRONEO -> println(" --> Imposible calcular el IMC. Introduzca la altura.")
            }
        }

        fun infoMayoriaDeEdad(persona:Persona){
            print(" tiene " + persona.edad + " años: ")
            if(persona.esMayorDeEdad()) println("es mayor de edad.")
            else println("es menor de edad.")
        }
    }

    constructor(dni:String, nombre:String, edad:Int, sexo:Char): this(dni){
        this.nombre = nombre
        this.edad = edad
        this.sexo = sexo
    }

    constructor(dni:String, nombre:String, edad:Int, sexo:Char, peso:Double, altura:Double): this(dni, nombre, edad, sexo){
        this.peso = peso
        this.altura = altura
    }


    fun calcularIMC(): Int {
        return when (peso/(altura.pow(2))){
            in 1.0..20.0 -> INFRAPESO
            in 20.0..25.0 -> PESO_IDEAL
            in 25.0..Double.MAX_VALUE -> SOBREPESO
            else -> PESO_ERRONEO
        }
    }

    fun esMayorDeEdad():Boolean= (edad >= 18)

    private fun comprobarSexo(sexo:Char): Char {
        return when (sexo.uppercase()[0]){  //Permitimos como válido tanto mayúsculas como minúsculas
            HOMBRE, MUJER -> sexo.uppercase()[0]
            else -> HOMBRE
        }
    }

    override fun toString() = "Nombre = $nombre, edad = $edad, DNI = $dni, sexo = $sexo, peso = $peso, altura = $altura"
}

fun generaLetraDNI(numeroDNI:Int): Char {
    val letra = "TRWAGMYFPDXBNJZSQVHLCKE"
    val resto:Int = numeroDNI%23
    return letra.get(resto)
}

fun generaNumerosDNI():Int{
    var numero = 0
    for(i in 1..8)
        numero = numero*10 + (0..10).random()
    return numero
}

fun generarDNI():String{
    val numero = generaNumerosDNI()
    return numero.toString().plus(generaLetraDNI(numero).toString())
}

fun pedirNombre():String{
    print("Introduzca el nombre: ")
    return readLine()?:""
}

fun pedirEdad():Int {
    print("Introduzca la edad: ")
    return try {
        (readLine() ?: "0").toInt()
    }catch(excep: NumberFormatException){0}
}

fun pedirDouble(tipo:String):Double {
    print("Introduzca $tipo: ")
    return try {
        (readLine() ?: "0").toDouble()
    }catch(excep: NumberFormatException){0.0}
}

fun pedirSexo():Char {
    print("Introduzca el sexo: ")
    return (readLine()?: "H")[0]
}

fun main() {
    val persona1 = Persona(generarDNI(), pedirNombre(), pedirEdad(), pedirSexo(), pedirDouble("peso"), pedirDouble("altura"))
    val persona2 = Persona(generarDNI(), "Sandra", 27, 'M')
    val persona3 = Persona(generarDNI())

    //INFO SOBRE EL PESO
    print(persona1.nombre)
    Persona.infoPesoIdeal(persona1)
    print(persona2.nombre)
    Persona.infoPesoIdeal(persona2)
    print(persona3.nombre)
    Persona.infoPesoIdeal(persona3)

    //INFO SOBRE MAYORÍA DE EDAD
    print(persona1.nombre)
    Persona.infoMayoriaDeEdad(persona1)
    print(persona2.nombre)
    Persona.infoMayoriaDeEdad(persona2)
    print(persona3.nombre)
    Persona.infoMayoriaDeEdad(persona3)

    //INFO COMPLETA DE CADA PERSONA
    println(persona1.toString())
    println(persona2.toString())
    println(persona3.toString())
}