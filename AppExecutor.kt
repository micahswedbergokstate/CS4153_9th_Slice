package com.example.cs4153pizzaapp

//Imports
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//Class to execute runnable tasks
open class AppExecutors(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {
    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    //Execute disk io processes
    fun diskIO(): Executor {
        return diskIO
    }
    //Execute network io processes
    fun networkIO(): Executor {
        return networkIO
    }

    //Function to execute on the main thread
    fun mainThread(): Executor {
        return mainThread
    }

    //Thread handler to execute on the main thread
    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}