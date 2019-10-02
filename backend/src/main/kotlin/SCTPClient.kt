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
        val incomingBuffer = ByteBuffer.allocate(1073741824)

        val outgoingMessage = MessageInfo.createOutgoing(null, streamNumber)
        outgoingMessage.payloadProtocolID(protocolId)

        channel.send(outgoingBuffer, outgoingMessage)

        var messageInfo: MessageInfo?
        do {
            messageInfo = channel.receive(incomingBuffer, System.out, associationHandler)
            if (messageInfo == null || messageInfo.bytes() == -1) {
                break
            }

            val receivedBytes = ByteArray(messageInfo.bytes())
            for (i in 0 until messageInfo.bytes()) {
                receivedBytes[i] = outgoingBuffer[i]
            }

            handleMessage(channel, receivedBytes)
        } while (messageInfo != null)
    }

    private fun handleMessage(channel: SctpChannel, data: ByteArray) {
        val msg = MessageInfo.createOutgoing(null, 0)
        msg.payloadProtocolID(60)
        channel.send(ByteBuffer.wrap(byteArrayOf(1, 2, 3, 4)), msg)
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