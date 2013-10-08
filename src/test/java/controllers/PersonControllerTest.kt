package controllers

import ninja.NinjaTest
import org.junit.Test
import models.Person
import com.google.gson.Gson
import kotlin.test.assertEquals

/**
 * Created by raymond on 07/10/2013.
 */
public class PersonControllerTest: NinjaTest() {

    [Test]
    fun testPostPerson(): Unit  {

        val person = Person("test")
        person.name = "zeeess name - and some utf8 => öäü"

        val response = ninjaTestBrowser?.postJson("${getServerAddress()}person", person)

        val result = Gson().fromJson(response, person.javaClass)
        assertEquals(person.name, result?.name)
    }
}