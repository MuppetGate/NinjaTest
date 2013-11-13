package controllers

import ninja.NinjaTest
import org.junit.Test
import com.google.common.collect.Maps
import java.util.HashMap
import org.apache.http.HttpResponse
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created on 08/10/2013.
 */
public class NotFoundTest : NinjaTest() {

    [Test]
    fun testThatNotFoundWorks() {

        // Create headers
        val headers: HashMap<String, String>? = Maps.newHashMap()

        // Get raw response
        val httpResponse = ninjaTestBrowser?.makeRequestAndGetResponse("${getServerAddress()}/_non_existing_url", headers)

        // make sure the status code is correct:
        assertEquals(404, httpResponse?.getStatusLine()?.getStatusCode())

        val content = ninjaTestBrowser?.makeRequest("${getServerAddress()}/_non_existing_url", headers)

        // Check that we get a working "not found" template from views/system/404notFound.ftl.html

        assertTrue(content?.contains("Oops. Not found.")!!)

    }
}