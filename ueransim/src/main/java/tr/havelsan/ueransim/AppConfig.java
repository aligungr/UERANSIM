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

import tr.havelsan.ueransim.api.gnb.ngap.NgapSctpAssociationHandler;
import tr.havelsan.ueransim.api.sys.SimulationContext;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.sctp.ISctpClient;
import tr.havelsan.ueransim.sctp.MockedSctpClient;
import tr.havelsan.ueransim.sctp.SctpClient;
import tr.havelsan.ueransim.structs.GnbAmfContext;
import tr.havelsan.ueransim.structs.GnbConfig;
import tr.havelsan.ueransim.structs.UeConfig;
import tr.havelsan.ueransim.utils.*;

import java.util.ArrayDeque;
import java.util.Queue;

public class AppConfig {

    public static String PROFILE;

    public static void initialize() {
        var root = (ImplicitTypedObject) MtsDecoder.decode("config/root.yaml");
        var profile = root.getString("selected-profile");
        PROFILE = "config/" + profile + "/";
        Console.println(Color.BLUE_BOLD_BRIGHT, "INFO: Selected profile: \"%s\"", profile);

        var general = (ImplicitTypedObject) MtsDecoder.decode(PROFILE + "general.yaml");
        Constants.USE_LONG_MNC = general.getBool("use-long-mnc");
    }

    public static SimulationContext createSimContext() {
        return new SimulationContext();
    }

    public static GnbSimContext createGnbSimContext(SimulationContext simCtx, ImplicitTypedObject config) {
        var ctx = new GnbSimContext(simCtx);
        ctx.config = MtsConstruct.construct(GnbConfig.class, config, true);

        // Create AMF gNB contexts and SCTP Clients
        {
            for (var amfConfig : ctx.config.amfConfigs) {
                var gnbSctpAssociationHandler = new NgapSctpAssociationHandler(ctx, amfConfig.guami);

                ISctpClient sctpClient;
                if (amfConfig.isMocked) {
                    Logging.warning(Tag.CONNECTION, "Mocked Remote is enabled.");
                    sctpClient = newMockedClient(amfConfig.mockingFile, gnbSctpAssociationHandler);
                } else {
                    sctpClient = new SctpClient(amfConfig.host, amfConfig.port,
                            Constants.NGAP_PROTOCOL_ID, gnbSctpAssociationHandler);
                }

                if (amfConfig.guami == null) {
                    throw new RuntimeException("amfConfig.guami == null");
                }

                var amfGnbCtx = new GnbAmfContext(amfConfig.guami);
                amfGnbCtx.sctpClient = sctpClient;
                amfGnbCtx.streamNumber = Constants.DEFAULT_STREAM_NUMBER;

                ctx.amfContexts.put(amfGnbCtx.guami, amfGnbCtx);
            }
        }

        return ctx;
    }

    public static UeSimContext createUeSimContext(SimulationContext simCtx, ImplicitTypedObject config) {
        var ctx = new UeSimContext(simCtx);

        // Parse UE Config
        ctx.ueConfig = config.asConstructed(UeConfig.class);

        return ctx;
    }

    private static MockedSctpClient newMockedClient(String mockedRemoteFile, NgapSctpAssociationHandler ngapSctpAssociationHandler) {
        var mockedRemote = ((ImplicitTypedObject) MtsDecoder.decode(mockedRemoteFile)).getParameters();

        return new MockedSctpClient(new MockedSctpClient.IMockedRemote() {
            int messageIndex = 0;

            @Override
            public void onMessage(byte[] data, Queue<Byte[]> queue) {
                var ngapPdu = NgapEncoding.decodeAper(data);
                var ngapMessage = Ngap.getMessageFromPdu(ngapPdu);
                var nasMessage = ngapMessage.getNasMessage();
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
        }, ngapSctpAssociationHandler);
    }
}
