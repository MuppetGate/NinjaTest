package controllers

import ninja.NinjaTest
import kotlin.test.assertTrue
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Created by raymond on 04/11/2013.
 */
public class ApplicationControllerTest : NinjaTest() {

    [Test]
    fun testThatRedirectWorks(): Unit {

        val headers: Map<String, String> = hashMapOf()

        val result = ninjaTestBrowser!!.makeRequest("${getServerAddress()}/redirect", headers)
        assertTrue({result?.contains("And developing large web applications becomes fun again.")!!})

    }

    [Test]
    fun testHtmlEscapingInTemplateWorks(): Unit {

        val expectedContent = "&lt;script&gt;alert('Hello');&lt;/script&gt;"
        val headers: Map<String, String> = hashMapOf()

        val result = ninjaTestBrowser!!.makeRequest("${getServerAddress()}htmlEscaping", headers)

        assertTrue {

            result?.contains(expectedContent)!!
        }

    }

    [Test]
    fun makeSureSessionsGetSentToClient(): Unit {

        val headers: Map<String, String> = hashMapOf()

        val httpResponse = ninjaTestBrowser!!.makeRequestAndGetResponse("${getServerAddress()}session", headers)

        assertEquals(1, ninjaTestBrowser!!.getCookies()?.size)
        val cookie = ninjaTestBrowser!!.getCookieWithName("NINJA_SESSION")

        assertNotNull(cookie)

        assertTrue {cookie?.getValue()?.contains("___TS")!!}
        assertTrue {cookie?.getValue()?.contains("username")!!}
        assertTrue {cookie?.getValue()?.contains("kevin")!!}

    }

    [Test]
    fun testThatPathParamsParsingWorks() : Unit {

        val headers: Map<String, String> = hashMapOf()

        val response = ninjaTestBrowser!!.makeRequest("${getServerAddress()}user/12345/john@example.com/userDashboard", headers)

        assertTrue {response?.contains("john@example.com")!!}
        assertTrue {response?.contains("12345")!!}

    }

    [Test]
    fun testThatValidationWorks(): Unit {

        val headers: Map<String, String> = hashMapOf()

        var response = ninjaTestBrowser!!.makeRequest("${getServerAddress()}validation?email=john@example.com")

        assertEquals(response, "\"john@example.com\"")

        response = ninjaTestBrowser!!.makeRequest("${getServerAddress()}validation")

        assertEquals(response?.trim(), "[{\"field\":\"email\",\"constraintViolation\":{\"messageKey\":\"validation.required.violation\",\"fieldKey\":\"email\",\"defaultMessage\":\"email is required\",\"messageParams\":[]}}]"
)
    }

    [Test]
    fun testPostFormParsingWorks(): Unit {

        val headers: Map<String, String> = hashMapOf()

        val formParameters: Map<String, String> = hashMapOf (
                "description" to "test3",
                "email" to "test2@email.com",
                "name" to "test1")

        val response = ninjaTestBrowser!!.makePostRequestWithFormParameters("${getServerAddress()}/contactForm",
                headers, formParameters)

        assertTrue {response?.contains("test3")!!}
        assertTrue {response?.contains("test2@email.com")!!}
        assertTrue {response?.contains("test1")!!}


    }

}