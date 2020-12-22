/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.encoding;

import tr.havelsan.ueransim.app.link.rlc.RlcConstants;
import tr.havelsan.ueransim.app.link.rlc.pdu.StatusPdu;
import tr.havelsan.ueransim.app.link.rlc.utils.NackBlock;
import tr.havelsan.ueransim.utils.BitInputStream;
import tr.havelsan.ueransim.utils.BitOutputStream;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

import java.util.ArrayList;

public class StatusEncoder {

    public static StatusPdu decode(BitInputStream stream, boolean isShortSn) {
        if (stream.read() != RlcConstants.DC_CONTROL)
            throw new IncorrectImplementationException();
        if (stream.readRange(3) != 0) // 0b000: STATUS PDU
            throw new IncorrectImplementationException();

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

    public static void encode(BitOutputStream stream, StatusPdu pdu, boolean isShortSn) {
        stream.write(RlcConstants.DC_CONTROL);
        stream.writeBits(0, 3); // STATUS PDU
        stream.writeBits(pdu.ackSn, isShortSn ? 12 : 18);

        if (pdu.nackBlocks == null || pdu.nackBlocks.isEmpty()) {
            stream.write(false);

            // insert reserved bits
            stream.writeBits(0, isShortSn ? 7 : 1);

            return;
        }

        stream.write(true);

        // insert reserved bits
        stream.writeBits(0, isShortSn ? 7 : 1);

        for (int i = 0; i < pdu.nackBlocks.size(); i++) {
            var block = pdu.nackBlocks.get(i);

            stream.writeBits(block.nackSn, isShortSn ? 12 : 18);
            stream.write(i != pdu.nackBlocks.size() - 1); // E1

            boolean e2, e3;

            if (block.soEnd == -1 || block.soStart == -1) {
                if (block.soEnd != -1 || block.soStart != -1) {
                    // All -1 or no one -1
                    throw new IncorrectImplementationException();
                }

                e2 = false;
                stream.write(false);
            } else {
                e2 = true;
                stream.write(true);
            }

            e3 = block.nackRange != -1;
            stream.write(e3);

            // insert reserved bits
            stream.writeBits(0, isShortSn ? 1 : 3);

            if (e2) {
                stream.writeBits(block.soStart, 16);
                stream.writeBits(block.soEnd, 16);
            }

            if (e3) {
                stream.writeBits(block.nackRange, 16);
            }
        }
    }
}
