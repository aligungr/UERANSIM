import com.sun.nio.sctp.*

import java.*
import java.io.*
import java.net.*
import java.nio.*
import java.nio.charset.*

object DaytimeClient {
    internal var SERVER_PORT = 3456
    internal var US_STREAM = 0
    internal var FR_STREAM = 1

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val serverAddr = InetSocketAddress(
            "::1",
            SERVER_PORT
        )
        val buf = ByteBuffer.allocateDirect(60)
        val charset = Charset.forName( "ISO-8859-1")
        val decoder = charset.newDecoder()

        val sc = SctpChannel.open(serverAddr, 0, 0)

        /* handler to keep track of association setup and termination */
        val assocHandler = AssociationHandler()

        /* expect two messages and two notifications */
        var messageInfo: MessageInfo? = null
        do {
            messageInfo = sc.receive(buf, System.out, assocHandler)
            buf.flip()

            if (buf.remaining() > 0 && messageInfo!!.streamNumber() == US_STREAM) {

                println("(US) " + decoder.decode(buf).toString())
            } else if (buf.remaining() > 0 && messageInfo!!.streamNumber() == FR_STREAM) {

                println("(FR) " + decoder.decode(buf).toString())
            }
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