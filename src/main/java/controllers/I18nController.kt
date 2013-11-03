package controllers

import com.google.inject.Singleton
import ninja.i18n.Lang
import com.google.inject.Inject
import kotlin.properties.Delegates
import ninja.Context
import ninja.Result
import ninja.Results
import ninja.params.PathParam
import ninja.i18n.LangImpl
import com.google.inject.Guice
import conf.Module


/**
 * Created by raymond on 01/11/2013.
 */
[Singleton]
public class I18nController {


    private val lang: Lang by Delegates.lazy {

        val injector = Guice.createInjector(Module())

        injector!!.getInstance(javaClass<Lang>())!!

    }


    fun index(context: Context) : Result? {

        val result = Results.html()
        lang.clearLanguage(result)
        return result

    }

    fun indexWithLanguage([PathParam("language")] language:String): Result? {

        val result = Results.ok()!!.html()!!.template("/views/I18nController/index.ftl.html")

        lang.setLanguage(language, result)
        return result
    }
}