package controllers

import ninja.NinjaTest
import org.junit.Test
import com.google.common.collect.Maps
import java.util.HashMap
import org.apache.http.HttpResponse
import kotlin.test.assertEquals

/**
 * Created on 13/10/2013.
 */
public class AssetControllerTest: NinjaTest() {

    [Test]
    fun testThatSettingOfMimeTypeWorks() {

        // Create headers
        val headers: HashMap<String, String>? = Maps.newHashMap()

        // redirect will send a location: redirect in headers
        val httpResponse = ninjaTestBrowser?.makeRequestAndGetResponse("${getServerAddress()}/assets/files/test_for_mimetypes.dxf", headers)

        //this is a mimetype nobody knows of...
        //but it is listetd in the ninja mimetypes... therefore it will be found:
        //default charset is always utf-8 by convention.

        assertEquals("application/dxf; charset=utf-8", httpResponse?.getHeaders("Content-Type")!![0].getValue())

    }

}