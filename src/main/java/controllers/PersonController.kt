package controllers

import com.google.inject.Singleton
import ninja.Result
import models.Person
import ninja.Results

/**
 * Created by raymond on 07/10/2013.
 */
[Singleton]
public class PersonController {

    fun  getPerson(): Result? {

        val person = Person("test")
        person.name = "zeeess name - and some utf8 => öäü"

        return Results.json()?.render(person)

    }

    fun postPerson(person:Person) : Result? {

        return Results.json()?.render(person)
    }
}