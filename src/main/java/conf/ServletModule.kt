package conf

import com.google.inject.servlet.ServletModule as GoogleServletModule
import ninja.servlet.NinjaServletDispatcher
import filters.DemoServletFilter

/**
 * Created on 22/10/2013.
 */
/**
 * This module is optional.
 *
 * If Ninja is running inside a servlet container you can
 * specify additional filters and servlets to be loaded.
 *
 * The cool thing is that you can use all goodies from the servlet
 * world this way.
 * The bad thing is that you might loose a lot of stuff Ninja provides.
 * For instance scalability via a stateless architecture.
 *
 * In short:
 * If you know what you are doing feel free to use ServletModule.
 * If not - just skip it and enjoy Ninja pure.
 *
 */
public class ServletModule: GoogleServletModule() {


    override fun configureServlets() {

        super<GoogleServletModule>.configureServlets()
        bind(javaClass<DemoServletFilter?>())?.asEagerSingleton()
        bind(javaClass<NinjaServletDispatcher?>())?.asEagerSingleton()
        filterRegex("^(?!/assets/).*$")?.through(javaClass<DemoServletFilter?>())
        serve("/*")?.with(javaClass<NinjaServletDispatcher?>())


    }
}