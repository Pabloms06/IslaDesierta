import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex1 = Mutex()
val mutex2 = Mutex()
val mutex3 = Mutex()

//No se como conseguir que se lanzen los 3 al mismo tiempo


suspend fun main(){

    comenzar()
    comenzar2()
    comenzar3()
    Thread.sleep(80000)

}

suspend fun comenzar() {
    for (i in 1..4){
        GlobalScope.launch {
            amigoA(i)
        }

    }
    Thread.sleep(20000)
}

suspend fun comenzar2(){
    for (i in 1..2){
        GlobalScope.launch {
            amigoB(i)
        }

    }
    Thread.sleep(23000)
}

fun comenzar3(){
    for (i in 1..1){
        GlobalScope.launch {
            amigoC(i)
        }

    }
    Thread.sleep(14000)
}

suspend fun amigoA(i : Int){
    GlobalScope.launch {
    mutex1.withLock {
        println("Amigo A-Estoy yendo a por agua")
        delay(0)
        "Ya puedes descansar"
        descansando()
        delay(1000)
        println("Amigo A-Ya he descansado")

    }
    }


}

suspend fun amigoB(i : Int) {
    GlobalScope.launch {
        mutex2.withLock {
            hacha()
            delay(0)
            println("Amigo B-Estoy cortando un arbol")
            delay(5000)
            println("Amigo B-Ya he descansado")
            println("Voy a descansar")
            descansando()
            delay(3000)

        }
    }

}
    suspend fun amigoC(i : Int) {

        GlobalScope.launch {
            mutex3.withLock {
                println("Amigo C-Cogiendo ramas sueltas")
                delay(3000)
                hacha()
                println("Amigo C-Cazando..")
                delay(4000)
                println("Amigo C-Ya terminé, voy a descansar")
                descansando()

            }
        }

    }

fun descansando(){
GlobalScope.launch {
    val descansando = async(start = CoroutineStart.LAZY) {  // Similar al anterior pero en este caso los 1000 no se empiezan
        // hasta que hacemos el await.
        delay(1000)
        "Estoy descansando"
    }
    println(descansando.await())
    println("Listo!")

}
}

fun hacha(){
    GlobalScope.launch {
        val descansando = async(start = CoroutineStart.LAZY) {
            delay(1000)
            "Tengo el hacha"
        }
        println(descansando.await())  //espera a que la variable contenido se lanze
        println("Hacha-Ya no lo necesito!")

    }
}



