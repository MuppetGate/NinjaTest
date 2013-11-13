package controllers

import ninja.NinjaTest
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

/**
 * Created on 08/10/2013.
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


    }

    [Test]
    fun testThatExplicitLangSettingWorks() : Unit {

        // 1) test that overriding of accept-language header works
        var headers = hashMapOf("Accept-Language" to "de-DE")

        var result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n/en", headers)

        assertTrue(result?.contains(TEXT_EN)!!)

        headers = hashMapOf("Accept-Language" to "de-DE")

        result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n/tk", headers)

        assertTrue(result?.contains(TEXT_EN)!!)


        // 3) test when no accept-lanugage is present => fallback should be
        //    language on the root.
        headers.clear()

        result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n/tk", headers)

        assertTrue(result?.contains(TEXT_EN)!!)

        // 4) normal operation
        headers = hashMapOf("Accept-Language" to "de-DE")
        result = ninjaTestBrowser?.makeRequest("${getServerAddress()}/i18n/de", headers)

        assertTrue(result?.contains(TEXT_DE)!!)

    }

}