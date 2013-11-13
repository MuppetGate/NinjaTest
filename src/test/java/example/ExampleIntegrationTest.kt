package example

import ninja.NinjaFluentLeniumTest
import kotlin.test.assertTrue
import org.junit.Test

/**
 * Created on 22/10/2013.
 */
public class ExampleIntegrationTest: NinjaFluentLeniumTest() {

    [Test]
    fun testThatStaticAssetsWork(): Unit {

        goTo("${getServerAddress()}/assets/css/bootstrap.css")
        assertTrue((pageSource()?.contains("Bootstrap"))!!)

    }

    [Test]
    fun testThatInvalidStaticAssetsAreNotFound(): Unit {

        goTo("${getServerAddress()}/assets/css/INVALID_FILE")
        assertTrue((pageSource()?.isEmpty())!!)

    }

    [Test]
    fun testIndexRoute(): Unit {

        goTo(getServerAddress())
        assertTrue((pageSource()?.contains("Ninja web framework"))!!)

    }

    [Test]
    fun testExamples(): Unit {

        goTo("${getServerAddress()}/examples")
        assertTrue((pageSource()?.contains("Examples"))!!)


    }
}