package conf

import ninja.NinjaRouterTest
import org.junit.Test
import controllers.ApplicationController

/**
 * Created on 04/11/2013.
 */
public class RoutesTest : NinjaRouterTest() {

    [Test]
    fun testRoutesAreHiddenFromProduction(): Unit {

        startServerInProdMode()

        // Make sure you can't see the routing stuff in production
        aRequestLike("GET", "/_test/testPage")?.isNotHandledByRoutesInRouter()
    }

    [Test]
    fun testRoutes(): Unit {

        startServer()

        aRequestLike("GET", "/")?.isHandledBy(javaClass<ApplicationController>(), "index")
        aRequestLike("GET", "/examples")?.isHandledBy(javaClass<ApplicationController>(), "examples")
    }
}