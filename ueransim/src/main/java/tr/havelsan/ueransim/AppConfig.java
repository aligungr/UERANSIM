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

import tr.havelsan.ueransim.api.sys.SimulationContext;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.sctp.ISctpClient;
import tr.havelsan.ueransim.sctp.MockedSCTPClient;
import tr.havelsan.ueransim.sctp.SCTPClient;
import tr.havelsan.ueransim.structs.GnbConfig;
import tr.havelsan.ueransim.structs.UeConfig;
import tr.havelsan.ueransim.utils.IncomingMessage;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;

import java.util.ArrayDeque;
import java.util.Queue;

public class AppConfig {

    public static SimulationContext createSimContext() {
        return new SimulationContext();
    }

    public static GnbSimContext createGnbSimContext(SimulationContext simCtx, ImplicitTypedObject config) {
        var ctx = new GnbSimContext(simCtx);
        ctx.config = MtsConstruct.construct(GnbConfig.class, config, true);

        // Create SCTP Client
        {
            boolean amfMocked = config.getBool("amfMocked");

            ISctpClient sctpClient = new SCTPClient(ctx.config.amfHost, ctx.config.amfPort, Constants.NGAP_PROTOCOL_ID);

            if (amfMocked) {
                Logging.warning(Tag.CONNECTION, "Mocked Remote is enabled.");
                sctpClient = newMockedClient(config.getString("amfMockedRemote"));
            }

            ctx.streamNumber = Constants.DEFAULT_STREAM_NUMBER;
            ctx.sctpClient = sctpClient;
        }

        return ctx;
    }

    public static UeSimContext createUeSimContext(SimulationContext simCtx, ImplicitTypedObject config) {
        var ctx = new UeSimContext(simCtx);

        // Parse UE Config
        ctx.ueConfig = config.asConstructed(UeConfig.class);

        return ctx;
    }

    private static MockedSCTPClient newMockedClient(String mockedRemoteFile) {
        var mockedRemote = ((ImplicitTypedObject) MtsDecoder.decode(mockedRemoteFile)).getParameters();

        return new MockedSCTPClient(new MockedSCTPClient.IMockedRemote() {
            int messageIndex = 0;

            @Override
            public void onMessage(byte[] data, Queue<Byte[]> queue) {
                var ngapPdu = Ngap.perDecode(NGAP_PDU.class, data);
                var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
                var nasMessage = NgapInternal.extractNasMessage(ngapPdu);
                var incomingMessage = new IncomingMessage(ngapPdu, ngapMessage, nasMessage);
                Queue<String> outs = new ArrayDeque<>();
                onMessage(incomingMessage, outs);
                while (!outs.isEmpty()) {
                    var out = outs.remove();
                    byte[] pdu = Utils.hexStringToByteArray(out);
                    Byte[] res = new Byte[pdu.length];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = pdu[i];
                    }
                    queue.add(res);
                }
            }

            private void onMessage(IncomingMessage message, Queue<String> queue) {
                var mockedValues = (Object[]) mockedRemote.get("messages-in-order");
                Object mockedValue = mockedValues[messageIndex];
                if (mockedValue != null) {
                    String str = mockedValue.toString();
                    if (str.length() > 0) {
                        queue.add(str);
                    }
                }
                messageIndex++;
            }
        });
    }
}
