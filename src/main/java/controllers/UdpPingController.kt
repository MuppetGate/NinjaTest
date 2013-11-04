package controllers

import com.google.inject.Singleton
import java.util.concurrent.atomic.AtomicInteger
import java.net.DatagramPacket
import java.net.DatagramSocket
import org.slf4j.LoggerFactory
import ninja.lifecycle.Start
import ninja.lifecycle.Dispose
import ninja.Result
import ninja.Context
import ninja.Results
import kotlin.properties.Delegates

/**
 * Created by raymond on 03/11/2013.
 */
[Singleton]
public class UdpPingController {

    private val logger = LoggerFactory.getLogger(javaClass<UdpPingController>())!!

    private val count = AtomicInteger()
    private var serverSocket: DatagramSocket? = DatagramSocket()

    private  var receiveThread: Thread? = Thread()

    [Start(order = 90)]
    fun startReceiving(): Unit {

        logger.info("Starting UDP listener on port 19876")
        serverSocket = DatagramSocket(19876)


        receiveThread = Thread {

            val receiveData = ByteArray(1024)

            while (true) {

                val receivePacket = DatagramPacket(receiveData, 1024)
                serverSocket?.receive(receivePacket)
                logger.info("Received UDP packet from ${receivePacket.getAddress()}")
                count.incrementAndGet()
            }
        }

        receiveThread!!.start()

    }

    [Dispose(order = 90)]
    fun stopReceiving(): Unit {

        logger.info("Stopping UDP listener")
        receiveThread!!.interrupt()
        serverSocket?.close()
        receiveThread = null
        serverSocket = null

    }

    fun getCount(context: Context): Result? {

        return Results.json()!!.render(count.get())

    }
}