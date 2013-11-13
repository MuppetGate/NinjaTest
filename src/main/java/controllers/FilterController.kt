package controllers

import com.google.inject.Singleton
import ninja.Result
import ninja.Context
import ninja.FilterWith
import ninja.SecureFilter

import ninja.Results
import ninja.Cookie
import filters.LoggerFilter
import filters.TeaPotFilter

/**
 * Created on 01/11/2013.
 */
[Singleton]
public class FilterController {

    [FilterWith(javaClass<SecureFilter>())]
    fun filter(context: Context): Result? {

        context.getCookies()!!.forEach {

            println("cookie: ${it.getName()}" )
        }

        return Results.html()!!.addCookie(Cookie.builder("myname", "myvalue")!!.build())
    }

    [FilterWith(javaClass<LoggerFilter>(), javaClass<TeaPotFilter>())]
    fun teapot(context: Context): Result? {

        return Results.html()
    }

}