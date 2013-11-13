package controllers

import com.google.inject.Singleton
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import kotlin.properties.Delegates
import ninja.i18n.Lang
import conf.Module
import com.google.inject.Guice
import com.google.inject.Inject
import ninja.utils.MimeTypes
import ninja.Result
import ninja.Results
import ninja.Context
import ninja.Renderable
import org.apache.commons.fileupload.FileItemIterator
import org.apache.commons.fileupload.FileItemStream
import com.google.common.io.ByteStreams
import org.apache.commons.fileupload.util.Streams

/**
 * Created on 03/11/2013.
 */

[Singleton]
public class UploadController [Inject] (val mimeTypes: MimeTypes) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass<UploadController>())!!

    private val lang: Lang by Delegates.lazy {

        val injector = Guice.createInjector(Module())

        injector!!.getInstance(javaClass<Lang>())!!

    }

    fun upload() : Result? {
        return Results.html()
    }

    fun uploadFinish(context: Context): Result? {

        val renderable =  Renderable() {context, result ->

            if (context!!.isMultipart()) {

                // This is the iterator we can use to iterate over the
                // contents
                // of the request.

                val fileItemIterator = context.getFileItemIterator()!!

                while (fileItemIterator.hasNext()) {

                    val item = fileItemIterator.next()

                    val name = item?.getFieldName()
                    val stream = item?.openStream()
                    var contentType = item?.getContentType()

                    if (contentType != null) {
                        result?.contentType(contentType)
                    }
                    else {
                        contentType = mimeTypes.getMimeType(name)
                    }

                    val responseStreams = context.finalizeHeaders(result)

                    if (item!!.isFormField()) {
                        println("Form field ${name} with value ${Streams.asString(stream)} detected")
                    }
                    else {
                        println("File field ${name} with file name ${item.getName()} detected")
                    }

                    ByteStreams.copy(stream, responseStreams?.getOutputStream())

                }


            }

        }

        return Result(200).render(renderable)


    }

}