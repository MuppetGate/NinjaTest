package conf

import ninja.application.ApplicationRoutes
import ninja.Router
import controllers.ApplicationController
import controllers.PersonController
import controllers.UdpPingController
import controllers.FilterController
import controllers.InjectionExampleController
import controllers.AsyncController
import controllers.I18nController
import controllers.UploadController
import ninja.AssetsController
import ninja.utils.NinjaProperties
import com.google.inject.Inject
import controllers.ApplicationController

/**
 * Created by raymond on 08/10/2013.
 */
class Routes [Inject] (var ninjaProperties : NinjaProperties) : ApplicationRoutes {

    override fun init(router: Router?) {

        // /////////////////////////////////////////////////////////////////////
        // some default functions
        // /////////////////////////////////////////////////////////////////////
        // simply render a page:
        router?.GET()?.route("/")?.with(javaClass<ApplicationController>(), "index")
        router?.GET()?.route("/examples")?.with(javaClass<ApplicationController>(), "examples")

        // render a page with variable route parts:
        router?.GET()?.route("/user/{id}/{email}/userDashboard")?.with(javaClass<ApplicationController>(), "userDashboard")
        router?.GET()?.route("/validation")?.with(javaClass<ApplicationController>(), "validation")

        // redirect back to /
        router?.GET()?.route("/redirect")?.with(javaClass<ApplicationController>(), "redirect")
        router?.GET()?.route("/session")?.with(javaClass<ApplicationController>(), "session")
        router?.GET()?.route("/htmlEscaping")?.with(javaClass<ApplicationController>(), "htmlEscaping")

        // /////////////////////////////////////////////////////////////////////
        // Json support
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/person")?.with(javaClass<PersonController>(), "getPerson")
        router?.POST()?.route("/person")?.with(javaClass<PersonController>(), "postPerson")

        // /////////////////////////////////////////////////////////////////////
        // Form parsing support
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/contactForm")?.with(javaClass<ApplicationController>(), "contactForm")
        router?.POST()?.route("/contactForm")?.with(javaClass<ApplicationController>(), "postContactForm")

        // /////////////////////////////////////////////////////////////////////
        // Lifecycle support
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/udpcount")?.with(javaClass<UdpPingController>(), "getCount")

        // /////////////////////////////////////////////////////////////////////
        // Route filtering example:
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/filter")?.with(javaClass<FilterController>(), "filter")
        router?.GET()?.route("/teapot")?.with(javaClass<FilterController>(), "teapot")

        // /////////////////////////////////////////////////////////////////////
        // Route filtering example:
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/injection")?.with(javaClass<InjectionExampleController>(), "injection")

        // /////////////////////////////////////////////////////////////////////
        // Async example:
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/async")?.with(javaClass<AsyncController>(), "asyncEcho")

        // /////////////////////////////////////////////////////////////////////
        // I18n:
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/i18n")?.with(javaClass<I18nController>(), "index")
        router?.GET()?.route("/i18n/{language}")?.with(javaClass<I18nController>(), "indexWithLanguage")

        // /////////////////////////////////////////////////////////////////////
        // Upload showcase
        // /////////////////////////////////////////////////////////////////////
        router?.GET()?.route("/upload")?.with(javaClass<UploadController>(), "upload")
        router?.POST()?.route("/uploadFinish")?.with(javaClass<UploadController>(), "uploadFinish")

        //this is a route that should only be accessible when NOT in production
        // this is tested in RoutesTest
        if (!ninjaProperties.isProd())
        {
            router?.GET()?.route("/_test/testPage")?.with(javaClass<ApplicationController>(), "testPage")
        }

        router?.GET()?.route("/assets/.*")?.with(javaClass<AssetsController>(), "serve")
        
        
    }
}