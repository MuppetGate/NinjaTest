package controllers

import com.google.inject.Singleton
import etc.GreetingService
import com.google.inject.Inject
import ninja.Result
import ninja.Context
import ninja.Results

/**
 * Created by raymond on 28/10/2013.
 */
[Singleton]
public class InjectionExampleController {

    private var greeter: GreetingService? = null
    [Inject] set(value) {$greeter = value}


    fun injection(context: Context): Result? {

        val map  = hashMapOf("greeting" to greeter?.hello())
        return Results.html()?.render(map)
    }
}