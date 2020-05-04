package tr.havelsan.ueransim.mocked;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.utils.Utils;

import java.util.ArrayDeque;
import java.util.Queue;

public class MockedRemote implements MockedSCTPClient.IMockedRemote {

    @Override
    public void onMessage(byte[] data, Queue<Byte[]> queue) {
        NGAP_PDU ngapPdu = Ngap.perDecode(NGAP_PDU.class, data);
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
        //
    }
}