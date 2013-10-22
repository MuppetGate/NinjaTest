package conf

import com.google.inject.AbstractModule
import etc.GreetingService
import etc.GreetingServiceImpl

/**
 * Created by raymond on 22/10/2013.
 */
public class Module: AbstractModule() {


    override fun configure() {

        bind(javaClass<GreetingService?>())?.to(javaClass<GreetingServiceImpl?>())

    }
}