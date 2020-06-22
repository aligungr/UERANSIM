/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim;

import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;

public class FlowLogging {

    public static void logReceivedMessage(IncomingMessage incomingMessage) {
        Logging.debug(Tag.MESSAGING, "Received NGAP: %s", incomingMessage.ngapMessage.getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(Ngap.xerEncode(incomingMessage.ngapPdu)));
        if (incomingMessage.nasMessage != null) {
            Logging.debug(Tag.MESSAGING, "Received NAS: %s", incomingMessage.nasMessage.getClass().getSimpleName());
            Logging.debug(Tag.MESSAGING, Json.toJson(incomingMessage.nasMessage));
        }
    }

    public static void logSentMessage(OutgoingMessage message) {
        Logging.debug(Tag.MESSAGING, "Sent NGAP: %s", NgapInternal.extractNgapMessage(message.ngapPdu).getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(Ngap.xerEncode(message.ngapPdu)));

        if (message.plainNas != null) {
            Logging.debug(Tag.MESSAGING, "Sent Plain NAS: %s", message.plainNas.getClass().getSimpleName());
            Logging.debug(Tag.MESSAGING, Json.toJson(message.plainNas));
        }
        if (message.securedNas != null && message.plainNas != message.securedNas) {
            Logging.debug(Tag.MESSAGING, "Sent Secured NAS: %s", message.securedNas.getClass().getSimpleName());
            Logging.debug(Tag.MESSAGING, Json.toJson(message.securedNas));
        }
    }

    public static void logUnhandledMessage(String receivedMessageName, Class<?>... expectedType) {
        if (expectedType == null || expectedType.length == 0) {
            Logging.error(Tag.MESSAGING, "Unhandled message received: %s", receivedMessageName);
        } else {
            var sb = new StringBuilder();
            for (int i = 0; i < expectedType.length; i++) {
                sb.append(expectedType[i].getSimpleName());
                if (i != expectedType.length - 1) {
                    sb.append(',');
                }
            }

            var expectedMessages = sb.toString();
            Logging.error(Tag.MESSAGING, "Unhandled message received: %s, expected messages were: %s", receivedMessageName, expectedMessages);
        }
    }

    public static void logUnhandledMessage(IncomingMessage message, Class<?>... expectedType) {
        var incomingMessage = message.ngapMessage.getClass().getSimpleName();
        if (message.nasMessage != null) {
            incomingMessage += "/" + message.nasMessage.getClass().getSimpleName();
        }
        logUnhandledMessage(incomingMessage, expectedType);
    }

    public static void logUnhandledMessage(NasMessage message, Class<?>... expectedType) {
        logUnhandledMessage(message.getClass().getSimpleName(), expectedType);
    }

    public static void logFlowComplete(BaseFlow flow) {
        Logging.success(Tag.FLOWS, "%s completed", flow.getClass().getSimpleName());
    }

    public static void logFlowFailed(BaseFlow flow, String errorMessage) {
        if (errorMessage != null && errorMessage.length() > 0) {
            Logging.error(Tag.FLOWS, "%s failed: %s", flow.getClass().getSimpleName(), errorMessage);
        } else {
            Logging.error(Tag.FLOWS, "%s failed", flow.getClass().getSimpleName());
        }
    }
}
