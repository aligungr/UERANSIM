import com.sun.nio.sctp.*

import java.*
import java.io.*
import java.net.*
import java.nio.*
import java.nio.charset.*

object DaytimeClient {
    internal var SERVER_PORT = 3457
    internal var US_STREAM = 0
    internal var FR_STREAM = 1

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val ngapMsg = {
            val msg = "00000400550002000500260021207e004171000d010011000000000099898877f71001002e04804080402f0201010079000f400001100000011000000110000075005a400118"
            val hex = mutableListOf<Byte>()
            for (i in 0..msg.length-2 step 2) {
                hex.add(Integer.parseInt(msg, i, i + 2, 16).toByte())
            }
            hex
        }()

        val serverAddr = InetSocketAddress(
            "::1",
            SERVER_PORT
        )

        val buf = ByteBuffer.wrap(ngapMsg.toByteArray())
        val incBuf = ByteBuffer.allocate(0)
//
//        val buf = ByteBuffer.allocateDirect(60)
//        val charset = Charset.forName( "ISO-8859-1")
//        val decoder = charset.newDecoder()

        val sc = SctpChannel.open(serverAddr, 0, 0)

        /* handler to keep track of association setup and termination */
        val assocHandler = AssociationHandler()

        val outMsgInfo = MessageInfo.createOutgoing(null, 1)
        outMsgInfo.payloadProtocolID(60) // NGAP PPID
        sc.send(buf, outMsgInfo)

        /* expect two messages and two notifications */
        var messageInfo: MessageInfo? = null
        do {
            messageInfo = sc.receive(incBuf, System.out, assocHandler)
            buf.flip()
            println("RECEIVED: " + incBuf.toString())
            buf.clear()
        } while (messageInfo != null)

        sc.close()
    }

    internal class AssociationHandler : AbstractNotificationHandler<PrintStream>() {
        override fun handleNotification(
            not: AssociationChangeNotification,
            stream: PrintStream?
        ): HandlerResult {
            if (not.event() == AssociationChangeNotification.AssocChangeEvent.COMM_UP) {
                val outbound = not.association().maxOutboundStreams()
                val inbound = not.association().maxInboundStreams()
                stream!!.printf(
                    "New association setup with %d outbound streams" + ", and %d inbound streams.\n",
                    outbound,
                    inbound
                )
            }

            return HandlerResult.CONTINUE
        }

        override fun handleNotification(
            not: ShutdownNotification?,
            stream: PrintStream
        ): HandlerResult {
            stream.printf("The association has been shutdown.\n")
            return HandlerResult.RETURN
        }
    }
}