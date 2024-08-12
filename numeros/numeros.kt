//Interfaz
interface INumeroBase {
    val valor: Int
    fun imprimirValor()
}

//Clase números primos
class NumeroPrimo(override val valor: Int) : INumeroBase {
    override fun imprimirValor() {
        println("Número Primo: $valor")
    }
}

//Clase números impares
class NumeroImpar(override val valor: Int) : INumeroBase {
    val divisores: List<Int> = calcularDivisores(valor)
    
    override fun imprimirValor() {
        println("Número Impar: $valor, Divisores: $divisores")
    }

    private fun calcularDivisores(numero: Int): List<Int> {
        return (1..numero).filter { numero % it == 0 }
    }
}

//Clase números pares
class NumeroPar(override val valor: Int) : INumeroBase {
    val divisores: List<Int> = calcularDivisores(valor)

    override fun imprimirValor() {
        println("Número Par: $valor, Divisores: $divisores")
    }

    private fun calcularDivisores(numero: Int): List<Int> {
        return (1..numero).filter { numero % it == 0 }
    }
}

//Clase para procesar y clasificar números
class ProcesadorNumerosPrimos(private val rango: IntRange) {
    private val primos = mutableListOf<NumeroPrimo>()
    private val pares = mutableListOf<NumeroPar>()
    private val impares = mutableListOf<NumeroImpar>()

    fun procesarNumeros() {
        for (numero in rango) {
            when {
                esPrimo(numero) -> primos.add(NumeroPrimo(numero))
                numero % 2 == 0 -> pares.add(NumeroPar(numero))
                else -> impares.add(NumeroImpar(numero))
            }
        }
    }

    fun imprimirResultados() {
        println("Primos:")
        primos.forEach { it.imprimirValor() }

        println("\nPares:")
        pares.forEach { it.imprimirValor() }

        println("\nImpares:")
        impares.forEach { it.imprimirValor() }
    }

    private fun esPrimo(numero: Int): Boolean {
        if (numero < 2) return false
        for (i in 2..Math.sqrt(numero.toDouble()).toInt()) {
            if (numero % i == 0) return false
        }
        return true
    }
}

//Resultado
fun main() {
    val procesador = ProcesadorNumerosPrimos(1..20)
    procesador.procesarNumeros()
    procesador.imprimirResultados()
}
