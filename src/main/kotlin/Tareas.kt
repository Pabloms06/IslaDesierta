import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex1 = Mutex()
val mutex2 = Mutex()
val mutex3 = Mutex()
fun main(){

    comenzar()
    Thread.sleep(80000)

}

fun comenzar() {
    for (i in 1..4){
        GlobalScope.launch {
            amigoA(i)
        }

    }
    Thread.sleep(20000)
}
GlobalScope.launch{
    val descansando = async(start = CoroutineStart.LAZY)
}


suspend fun amigoA(i : Int){
    mutex1.withLock{
        println("Amigo A-Estoy yendo a por agua")
        delay(0)
        "Ya puedes descansar"
        println(descansando.await())
        delay(1000)
        println("Amiga A-Ya he descansado")
    }


}


