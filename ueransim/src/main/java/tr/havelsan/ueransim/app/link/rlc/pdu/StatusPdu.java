package tr.havelsan.ueransim.app.link.rlc.pdu;

import tr.havelsan.ueransim.app.link.utils.BitInputStream;

import java.util.ArrayList;
import java.util.List;

public class StatusPdu {
    public int ackSn;
    public List<NackBlock> nackBlocks;

    public static StatusPdu decode(BitInputStream stream, boolean isShortSn) {
        // consume first 4 bits which are D/C and CPT
        stream.readRange(4);

        var pdu = new StatusPdu();
        pdu.ackSn = stream.readRange(isShortSn ? 12 : 18);
        pdu.nackBlocks = new ArrayList<>();

        var e1 = stream.read();

        // consume reserved
        stream.readRange(isShortSn ? 7 : 1);

        while (e1) {
            var nackSn = stream.readRange(isShortSn ? 12 : 18);
            e1 = stream.read();
            var e2 = stream.read();
            var e3 = stream.read();

            // consume reserved
            stream.readRange(isShortSn ? 1 : 3);

            var soStart = -1;
            var soEnd = -1;
            var nackRange = -1;

            if (e2) {
                soStart = stream.readRange(16);
                soEnd = stream.readRange(16);
            }

            if (e3) {
                nackRange = stream.readRange(8);
            }

            var nackBlock = new NackBlock();
            nackBlock.nackSn = nackSn;
            nackBlock.soStart = soStart;
            nackBlock.soEnd = soEnd;
            nackBlock.nackRange = nackRange;

            pdu.nackBlocks.add(nackBlock);
        }

        return pdu;
    }
}
