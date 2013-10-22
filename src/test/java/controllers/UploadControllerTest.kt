package controllers

import ninja.NinjaTest
import org.junit.Test
import com.google.common.collect.Maps
import kotlin.test.assertTrue
import java.io.File

/**
 * Created by raymond on 22/10/2013.
 */
public class UploadControllerTest: NinjaTest() {

    [Test]
    fun testHtmlEscapingInTemplateWorks(): Unit {

        // empty headers for now
        val headers: Map<String, String> = hashMapOf()

        // redirect will send a location: redirect in the headers.

        val result = ninjaTestBrowser?.makeRequest("${getServerAddress()}upload", headers)

        assertTrue(result?.contains("Please specify file to upload:")!!)
    }

    fun testThatUploadWorks(): Unit {

        val file: File = File("src/test/resources/test_for_upload.txt")

        val result = ninjaTestBrowser?.uploadFile("${getServerAddress()}uploadFinish", "file", file)

        assertTrue((result?.contains("test_for_upload.txt"))!!)


    }
}