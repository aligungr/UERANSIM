package tr.havelsan.ueransim.mocked;

import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationResponse;
import tr.havelsan.ueransim.nas.impl.messages.IdentityResponse;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupRequest;
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
        if (message.ngapMessage instanceof NGSetupRequest) {
            queue.add("20150055000004000100170a00756e646566696e6564302e76616c6964382e636f6d0060001f00400011000100400a00756e646566696e6564302e76616c6964382e636f6d00564001640050000b000011000000100809afaf");
            return;
        }
        if (message.nasMessage instanceof RegistrationRequest) {
            queue.add("00044019000003000a00020000005500034003e800260005047e005b03");
            return;
        }
        if (message.nasMessage instanceof IdentityResponse) {
            queue.add("0004403f000003000a00020000005500034003e80026002b2a7e00560702000021cb096fbdbdd4e5a0f18a224c7c0c065f2010f14210f31982800013912ddc0463d355");
            return;
        }
        if (message.nasMessage instanceof AuthenticationResponse) {
            queue.add("00044027000003000a00020000005500034003e800260013127e0300000000007e005d0007040102ffffe1");
            return;
        }
    }
}