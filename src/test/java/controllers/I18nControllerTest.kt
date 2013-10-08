package controllers

import ninja.NinjaTest
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

/**
 * Created by raymond on 08/10/2013.
 */
public class I18nControllerTest: NinjaTest() {

    val TEXT_EN  = "Hello - this is an i18n message in the templating engine"
    val TEXT_DE  = "Hallo - das ist eine internationalisierte Nachricht in der Templating Engine"

    [Test]
    fun testThatI18nWorksEn(): Unit {

        val headers = hashMapOf("Accept-Language" to "en-US")
        val result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n", headers)

        assertTrue(result?.contains(TEXT_EN)!!)

    }


    [Test]
    fun testThatI18nWorksDe(): Unit {

        val headers = hashMapOf("Accept-Language" to "de-DE")
        val result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n", headers)

        assertTrue(result?.contains(TEXT_DE)!!)

    }


    [Test]
    fun testThatImplicitParameterWorks(): Unit  {

        val headers = hashMapOf("Accept-Language" to "de-DE")
        val result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n", headers)

        assertTrue(result?.contains("Implicit language is: de-DE")!!)

        // Change the header and try again


    }

}