import com.sun.nio.sctp.*

import java.*
import java.io.*
import java.net.*
import java.nio.*
import java.nio.charset.*
import java.text.*
import java.util.*

object SCTPTestServer {

    @JvmStatic
    fun main(args: Array<String>) {
        val serverAddr = InetSocketAddress(3457)
        val ssc = SctpServerChannel.open()
        ssc.bind(serverAddr)

        val recvbuf = ByteBuffer.allocateDirect(255)
        while (true) {
            val sc = ssc.accept()

            val outMsgInfo = MessageInfo.createOutgoing(null, 0)
            outMsgInfo.payloadProtocolID(60)
            sc.send(ByteBuffer.wrap(byteArrayOf(45, 45, 45, 45)), outMsgInfo)
            // shutdown and receive all pending messages/notifications
            sc.shutdown()
            val assocHandler = AssociationHandler()
            var inMessageInfo: MessageInfo? = null
            while (true) {
                inMessageInfo = sc.receive(recvbuf, System.out, assocHandler)
                if (inMessageInfo == null || inMessageInfo.bytes() == -1) {
                    break
                }
            }
            sc.close()
        }
    }

    private class AssociationHandler : AbstractNotificationHandler<PrintStream>() {
        override fun handleNotification(not: AssociationChangeNotification, stream: PrintStream?): HandlerResult {
            if (not.event() == AssociationChangeNotification.AssocChangeEvent.COMM_UP) {
                val outbound = not.association().maxOutboundStreams()
                val inbound = not.association().maxInboundStreams()
                stream!!.printf(
                    "New association setup with %d outbound streams" + ", and %d inbound streams.\n",
                    outbound, inbound
                )
            }
            return HandlerResult.CONTINUE
        }
        override fun handleNotification(not: ShutdownNotification?, stream: PrintStream): HandlerResult {
            stream.printf("The association has been shutdown.\n")
            return HandlerResult.RETURN
        }
    }
}
