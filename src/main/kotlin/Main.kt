var HOMBRE = 'H'
var MUJER = 'M'

class Persona(private val dni:String){
    protected var nombre:String = ""
    protected var edad:Int = 0
    protected var sexo:Char = HOMBRE
    protected var peso:Double = 0.0
    protected var altura:Double = 0.0

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

    fun comprobarSexo(sexo:Char): Char {
        return when (sexo){
            HOMBRE, MUJER -> sexo
            else -> HOMBRE
        }
    }

}


fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}