package controllers

import com.google.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ninja.i18n.Lang
import ninja.Result
import com.google.inject.Singleton
import ninja.Context
import ninja.Results
import ninja.params.PathParam
import ninja.validation.Validation
import ninja.params.Param
import ninja.validation.Required
import models.Contact


/**
 * Created by raymond on 22/10/2013.
 */
[Singleton]
public class ApplicationController {

    /**
     * System-wide logger
     */

    private var logger: Logger? = LoggerFactory.getLogger(javaClass<ApplicationController>())
    [Inject] set(value) { $logger = value }

    var lang: Lang? = null
    [Inject] set(value) { $lang = value }



    fun examples(context: Context): Result? {

        logger?.info("In example")

        // Default rendering is simple by convention
        // This renders the page in views/ApplicationController/index.ftl.html
        return Results.html()

    }

    fun testPage(): Result? {
        return Results.html()
    }

    fun index(context : Context): Result? {
        return Results.html()
    }


    fun userDashboard([PathParam("email")] email: String,
                      [PathParam("id")] id: Int,
                      context: Context): Result? {

        val map: Map<String, Any> = hashMapOf("id" to id,
                                              "email" to email)

        return Results.html()?.render(map)
    }

    fun validation(validation: Validation,
                   [Param("email")] [Required] email: String): Result? {

        if (validation.hasViolations()) {
            return Results.json()?.render(validation.getFieldViolations("email"))
        }
        else {
            return Results.json()?.render(email)
        }

    }

    fun redirect(context: Context): Result? {

        return Results.redirect("/")

    }

    fun session(context: Context) : Result? {

        context.getSessionCookie()?.put("username", "kevin")

        return Results.html()?.render(context.getSessionCookie()?.getData())
    }

    fun contactForm(context: Context): Result? {
        return Results.html()
    }

    fun postContactForm(context: Context, contact: Contact): Result? {

        return Results.html()?.render(contact)

    }

    fun htmlEscaping(context: Context): Result? {

        val maliciousJavascript = "<script>alert('Hello');</script>"

        val renderMap: Map<String, String> = hashMapOf("maliciousJavascript" to maliciousJavascript)
        return Results.html()?.render(renderMap)
    }
}