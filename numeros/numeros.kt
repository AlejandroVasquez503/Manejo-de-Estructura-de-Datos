//Interfaz
interface INumeroBase {
    val valor: Int
    fun imprimirValor()
}

//Clase numeros primos
class NumeroPrimo(override val valor: Int) : INumeroBase {
    override fun imprimirValor() {
        println("Número Primo: $valor")
    }
}

//Clase numeros impares
class NumeroImpar(override val valor: Int) : INumeroBase {
    override fun imprimirValor() {
        println("Número Impar: $valor")
    }
}

//Clase numeros pares
class NumeroPar(override val valor: Int) : INumeroBase {
    override fun imprimirValor() {
        println("Número Par: $valor")
    }
}

//Clase para procesar y clasificar numeros
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

    fun obtenerResultados(): Triple<Array<NumeroPrimo>, Array<NumeroPar>, Array<NumeroImpar>> {
        return Triple(
            primos.toTypedArray(),
            pares.toTypedArray(),
            impares.toTypedArray()
        )
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
        for (i in 2 until numero) {
            if (numero % i == 0) return false
        }
        return true
    }
}

//Resultados
fun main() {
    val procesador = ProcesadorNumerosPrimos(1..20)
    procesador.procesarNumeros()
    val resultados = procesador.obtenerResultados()
    procesador.imprimirResultados()
}
