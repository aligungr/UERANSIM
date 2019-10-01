import com.sun.nio.sctp.*

import java.io.*
import java.net.*
import java.nio.*

object SCTPClient {

    public fun send(hostname: String, port: Int, protocolId: Int, streamNumber: Int, data: ByteArray) {
        val serverAddress = InetSocketAddress(hostname, port)
        val channel = SctpChannel.open(serverAddress, 0, 0)
        val associationHandler = AssociationHandler()

        val outgoingBuffer = ByteBuffer.wrap(data)
        val incomingBuffer = ByteBuffer.allocate(0)

        val outgoingMessage = MessageInfo.createOutgoing(null, streamNumber)
        outgoingMessage.payloadProtocolID(protocolId)

        channel.send(outgoingBuffer, outgoingMessage)

        var messageInfo: MessageInfo?
        do {
            messageInfo = channel.receive(incomingBuffer, System.out, associationHandler)
            //
            val x = 21
        } while (messageInfo != null)
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