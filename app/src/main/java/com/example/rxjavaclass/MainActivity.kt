package com.example.rxjavaclass

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var value: Int = 18

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myGithubStarsRepos.setOnClickListener {
            startActivity(Intent(this, MyStarsRepos::class.java))
        }

        /*showJustJob()

        createFromArray().subscribe {
            arr -> println("received array is:" + Arrays.toString(arr))
        }

        createFromIterable().subscribe { a ->
            println("received data is: " + a)
        }

        createRange().subscribe() {
            it -> println("received data is: " + it)
        }

        createInterval().subscribe {
            a -> println("received data is: " + a)
        }

        createTimer().subscribe {
            a -> println("The food is ready after : " + a)
        }

        createFilter().subscribe {
            a -> println("the data received is: " + a)
        }

        createTakeLast().subscribe {
            a -> println("the data received is: " + a)
        }

        createTake().subscribe {
            a -> println("the data received is : " a)
        }

        createTimeOut("Irving").subscribe(
            {
                // when onNext method
                name -> println("Hello ${name}!!!!!!!!!!")
            },
            {
                // when onError called
                t: Throwable -> println("Error : " + t.javaClass.simpleName)
            }
        )

        createDistinct().subscribe {
            value -> println("values is: ${value}")
        }

        createStartWith().subscribe {
            value -> println("the value is : ${value}")
        }

        createMerge().subscribe {
            value -> println("the value is : ${value}")
        }

        createConcat().subscribe {
            value: Int -> println("the value is : ${value}")
        }

        createZip().subscribe (
            {
                value -> println("value is: ${value}")
            },
            {
                t: Throwable -> println("You have an error in:" + t.javaClass.simpleName)
            }
        )

        createScan().subscribe({
            value -> println(" values is: ${value}")
        })

        createFlatMap().subscribe { value -> println(" value: ${value}") }

        exampleFlatMap().subscribe { value -> println("value: ${value}") }

        createFlatMap2().subscribe { value -> println("value: ${value}") }
        */

        /*val professor = PublishSubject.create<String>()

        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("c++")

        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()*/

        /*val professor = ReplaySubject.create<String>()

        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("c++")

        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()*/

        // BehaviorSubject takes the last value emitted first.
        /*val professor = BehaviorSubject.create<String>()
        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("c++")

        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()*/

        //Async only return the last value emitted for each Asyncsubject subscribed
        /*val professor = AsyncSubject.create<String>()
        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("c++")

        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()*/

    }

    //First steps
    private fun showJustJob(){
        //Create an Observable that emits data
        //below there is an Observable that emits list of numbers. Just() operator is used to emit few data
        val dataStream = Observable.just(10,20, 30, 40)

        //Create an Observer that listen to Observable
        //Observer provides the below interfaces methos to know the state of the Observable.
        val dataObserver = object : Observer<Int>{
            override fun onComplete() {
                println("All data is received........")
            }

            override fun onSubscribe(d: Disposable) {
                println("On Subscribe")
            }

            override fun onNext(t: Int) {
                println("new data is received : " +t)
            }

            override fun onError(e: Throwable) {
                println("An ERROR is received" +e?.message)
            }

        }

        //Make Observer subscribe to Observable so that it can start receving the data.
        //Here you can notice two more methods, observeOn() and subscribeOn()
        dataStream.subscribe(dataObserver)

    }

    private fun createFromArray() : Observable<Array<Int>>{
        return Observable.fromArray(arrayOf(1, 5, 7, 9))
    }

    private fun createFromIterable() :Observable<Int> {
        return Observable.fromIterable(mutableListOf(2,5,7))
    }

    private fun createRange() :Observable<Int> {
        return Observable.range(1, 3).repeat(3)
    }

    private fun createInterval() :Observable<Long> {
        return Observable.interval(1,TimeUnit.SECONDS).takeWhile { value -> value < 20 }
    }

    private fun createTimer() :Observable<Long> {
        return Observable.timer(20, TimeUnit.SECONDS)
    }

    // Filter operators
    private fun createFilter() : Observable<Int> {
        return Observable.just(2,40,30,5)
            .filter { x -> x > 10 }
    }

    private fun createTakeLast() :Observable<Int> {
        return Observable.just(1,2,3,4).takeLast(2)
    }

    private fun createTake() :Observable<Int> {
        return Observable.just(1,2,3,4).take(2)
    }

    // Observable Utility Operator
    private fun createTimeOut(name: String) : Observable<String> {
        return Observable.fromCallable {
            if (name.equals("ramadan"))
                Thread.sleep(150)
            name
        }.timeout(100, TimeUnit.MILLISECONDS)
    }

    // distinct Filter operator
    private fun createDistinct(): Observable<Int> {
        return Observable.just(1,2,3,4,4,5,5,2)
            .distinct()
    }

    // combining Operators
    private fun createStartWith(): Observable<String> {
        return Observable.just("lion","dog","cat", "turtle").startWith("elephant")
    }

    private fun createMerge(): Observable<String> {
        val firstStream = Observable.just("d","a","b","e")
        val secondStream = Observable.just("a","b","c")
        return firstStream.mergeWith(secondStream)
    }

    private fun createConcat() : Observable<Int> {
        val firstStream = Observable.just(1,2,3,5)
        val secondStream = Observable.just(4,5,6)
        return firstStream.concatWith(secondStream)
    }

    private fun createZip() : Observable<String> {
        val firstNames = Observable.just("James", "Jean-Luc", "Benjamin")
        val lastNames = Observable.just("Kirk", "Picard", "Sisko")
        return firstNames.zipWith(lastNames, BiFunction{ first, last ->
            "${first} ${last}"
        })
    }

    private fun createScan() : Observable<Int> {
        return Observable.just(1,2,3,4,5)
            .scan({x1, x2-> x1 + x2})
    }

    // Transforming Observables
    // FlaMap example with unic type
    private fun createFlatMap() : Observable<String> {
        return Observable.just("A", "B", "C")
            .flatMap { getName() }
    }

    private fun getName(): Observable<String> {
        val names = arrayOf("irving", "elfo","hunter")
        val rand = Random().nextInt(3)
        return Observable.just(names[rand])
    }

    // FlatMap example Using Observable String and using in second function
    // Second function creates an Integer range but the return is an String
    private fun exampleFlatMap() : Observable<String> {
        return Observable.just("A","B","C")
            .flatMap { a -> createIntervalRange(a) }
    }

    private fun createIntervalRange(a: String) : Observable<String> {
        return Observable.intervalRange(1,3,0,10, TimeUnit.SECONDS)
            .map{ b -> "( ${a}, ${b} )" }
    }

    // pass id from function and return observable of another type
    private fun createFlatMap2(): Observable<String> {
        return Observable.just(0, 1, 2)
            .flatMap { id -> getName(id) }
    }

    private fun getName(id: Int): Observable<String> {
        val names = arrayOf("ramadan", "hashem", "Yousef")
        return Observable.just(names[id])
    }

    // subjects
    private fun getFirstStudent() : Observer<String> {
        return object: Observer<String> {
            override fun onComplete() {
                println("the class is over")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: String) {
                println("first student >> our prof is teaching about: ${t}")
            }

            override fun onError(e: Throwable) {
                println("error")
            }

        }
    }

    private fun getLateStudent() : Observer<String> {
        return object: Observer<String> {
            override fun onComplete() {
                println("the class is over")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                println("late student >> our prof is teaching about: ${t}")
            }

            override fun onError(e: Throwable) {
                println("error")
            }

        }
    }

    // Fibonacci
    private fun initFibonacci(){
        // Imprime de la posición 0 hasta la 18 el correspondiente
        // numero fibonacci de la posición.
        println("Fibonacchi")
        for (i in 0..value) {
            println("${fibonacchi(i)}, ")
        }
    }

    private fun fibonacchi(n : Int) : Int {
        when(n) {
            0,1 -> return n
            else -> return fibonacchi(n - 1) + fibonacchi(n - 2)
        }
    }

}
