package example

import ninja.NinjaTest
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created on 22/10/2013.
 */
public class ExampleApiTest: NinjaTest() {

    [Test]
    fun testThatStaticAssetsWork() {

        val apiCallResult = ninjaTestBrowser?.makeJsonRequest("${getServerAddress()}/person")
        println("apicallresult: " + apiCallResult)
        assertTrue((apiCallResult?.startsWith("{\"name\":\"zeeess name -"))!!)

    }
}