package controllers

import com.google.inject.Singleton
import java.util.concurrent.Executors
import ninja.Context
import ninja.Result
import ninja.Results
import java.util.concurrent.TimeUnit
import ninja.lifecycle.Dispose
import java.lang.Long.parseLong

/**
 * Created by raymond on 28/10/2013.
 */
[Singleton]
public class AsyncController {

    private  val executorService = Executors.newSingleThreadScheduledExecutor()

    fun asyncEcho(ctx: Context) : Result? {
        executorService.schedule( {

                ctx.returnResultAsync(Results.json()?.render(ctx.getParameter("message")))


        }, ctx.getParameter("timeout")!!.toLong(), TimeUnit.MILLISECONDS)

        return Results.async()
    }

    [Dispose]
    fun shutDownExecutor(): Unit {

        executorService.shutdown()

    }
}