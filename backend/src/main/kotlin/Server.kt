import com.sun.nio.sctp.*

import java.*
import java.io.*
import java.net.*
import java.nio.*
import java.nio.charset.*
import java.text.*
import java.util.*

object DaytimeServer {
    private var SERVER_PORT = 3457
    private var US_STREAM = 0
    private var FR_STREAM = 1

    private var USformatter = SimpleDateFormat(
        "h:mm:ss a EEE d MMM yy, zzzz", Locale.US
    )
    private var FRformatter = SimpleDateFormat(
        "h:mm:ss a EEE d MMM yy, zzzz", Locale.FRENCH
    )

    val ngapMsg = {
        val msg = "00000400550002000500260021207e004171000d010011000000000099898877f71001002e04804080402f0201010079000f400001100000011000000110000075005a400118"
        val hex = mutableListOf<Byte>()
        for (i in 0..msg.length-2 step 2) {
            hex.add(Integer.parseInt(msg, i, i + 2, 16).toByte())
        }
        hex
    }()



        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val ssc = SctpServerChannel.open()
            val serverAddr = InetSocketAddress(SERVER_PORT)
            ssc.bind(serverAddr)

            val buf = ByteBuffer.allocateDirect(60)

            val cbuf = CharBuffer.allocate(60)
            val charset = Charset.forName("ISO-8859-1")
            val encoder = charset.newEncoder()
            val recvbuf = ByteBuffer.allocateDirect(255)
            while (true) {
                val sc = ssc.accept()

                /* get the current date */
//                val today = Date()
//                cbuf.put(USformatter.format(today)).flip()
//                encoder.encode(cbuf, buf, true)
//                buf.flip()

                /* send the message on the US stream */
//                val outMessageInfo = MessageInfo.createOutgoing(
//                    null,
//                    US_STREAM
//                )
//                sc.send(buf, outMessageInfo)

                /* update the buffer with French format */
//                cbuf.clear()
//                cbuf.put(FRformatter.format(today)).flip()
//                buf.clear()
//                encoder.encode(cbuf, buf, true)
//                buf.flip()

                /* send the message on the French stream */
//                outMessageInfo.streamNumber(FR_STREAM)
//                sc.send(buf, outMessageInfo)

//                cbuf.clear()
//                buf.clear()
                val outMsgInfo = MessageInfo.createOutgoing(null, US_STREAM)
                sc.send(ByteBuffer.wrap(ngapMsg.toByteArray()), outMsgInfo)
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

        internal class AssociationHandler : AbstractNotificationHandler<PrintStream>() {
            override fun handleNotification(
                not: AssociationChangeNotification?,
                stream: PrintStream
            ): HandlerResult {
                stream.println("AssociationChangeNotification received: " + not!!)
                return HandlerResult.CONTINUE
            }

            override fun handleNotification(
                not: ShutdownNotification?,
                stream: PrintStream
            ): HandlerResult {
                stream.println("ShutdownNotification received: " + not!!)
                return HandlerResult.RETURN
            }
        }
    }
