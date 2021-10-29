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
        return when (peso/(altura*altura)){
            in 1.0..20.0 -> -1
            in 20.0..25.0 -> 0
            else -> 1
        }
    }

    fun esMayorDeEdad():Boolean= (edad >= 18)

    private fun comprobarSexo(sexo:Char): Char {
        return when (sexo){
            HOMBRE, MUJER -> sexo
            else -> HOMBRE
        }
    }

    override fun toString() = "Nombre = $nombre, edad = $edad, DNI = $dni, sexo = $sexo, peso = $peso, altura = $altura"


}

fun generaLetraDNI(numeroDNI:Int): Char {
    val letra:String = "TRWAGMYFPDXBNJZSQVHLCKE"
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
    return (readLine() ?: "0").toInt()
}

fun pedirPeso():Double {
    print("Introduzca el peso: ")
    return (readLine() ?: "0").toDouble()
}

fun pedirAltura():Double {
    print("Introduzca la altura: ")
    return (readLine() ?: "0").toDouble()
}

fun pedirSexo():Char {
    print("Introduzca el sexo: ")
    return (readLine()?: "H")[0]
}


fun main() {
    val persona1 = Persona(generarDNI(), pedirNombre(), pedirEdad(), pedirSexo(), pedirPeso(), pedirAltura())
    println(persona1.sexo)
    val persona2 = Persona(generarDNI(), "Sandra", 27, 'M')
    println(persona2.dni)
    val persona3 = Persona(generarDNI(), "Juan", 35, 'H',100.5, 1.80)
    println(persona2.dni)
}