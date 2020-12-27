/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.test;

import tr.havelsan.ueransim.app.link.rlc.entity.AmEntity;
import tr.havelsan.ueransim.app.link.rlc.entity.RlcEntity;
import tr.havelsan.ueransim.app.link.rlc.entity.TmEntity;
import tr.havelsan.ueransim.app.link.rlc.entity.UmEntity;
import tr.havelsan.ueransim.app.link.rlc.interfaces.IRlcConsumer;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Random;

public class TestRlc {

    // RLC
    private static final int SN_LENGTH = 12;
    private static final int TX_MAX_SIZE = 1024 * 32;
    private static final int RX_MAX_SIZE = 1024 * 32;
    private static final int MAX_RET = 8;
    private static final int POLL_RETRANSMIT_PERIOD = 400;
    private static final int REASSEMBLY_PERIOD = 400;
    private static final int STATUS_PROHIBIT_PERIOD = 400;

    // UPPER LAYER
    private static final int TRANSMISSION_SIZE = 2000;

    // LOWER LAYER
    private static final int OPPORTUNITY_SIZE_MIN = 10000;
    private static final int OPPORTUNITY_SIZE_MAX = 30000;

    // GENERAL
    private static final int MAX_PACKET_SEND_COUNT = 60000;
    private static final Class<?> ENTITY = TmEntity.class;

    public static void main(String[] args) {
        var ueRlc = new RlcTask();
        var gnbRlc = new RlcTask();

        ueRlc.pair = gnbRlc;
        gnbRlc.pair = ueRlc;

        ueRlc.tag = "UE";
        gnbRlc.tag = "GNB";

        ueRlc.start();
        gnbRlc.start();
    }

    private static class RlcTask extends NtsTask implements IRlcConsumer {
        private final Random random = new Random();

        public RlcEntity entity;
        public String tag;
        public RlcTask pair;

        private int packetId;
        private int receivedCounter;

        public RlcTask() {
            if (ENTITY == AmEntity.class)
                entity = AmEntity.newInstance(this, SN_LENGTH, TX_MAX_SIZE, RX_MAX_SIZE, -1,
                        -1, MAX_RET, POLL_RETRANSMIT_PERIOD, REASSEMBLY_PERIOD, STATUS_PROHIBIT_PERIOD);
            if (ENTITY == UmEntity.class)
                entity = UmEntity.newInstance(this, SN_LENGTH, REASSEMBLY_PERIOD, TX_MAX_SIZE, RX_MAX_SIZE);
            if (ENTITY == TmEntity.class)
                entity = TmEntity.newInstance(this, TX_MAX_SIZE);
        }

        @Override
        protected void main() {
            while (true) {
                var msg = poll();
                if (msg != null) {
                    if (msg instanceof IwRadioUplink) {
                        entity.receivePdu(((IwRadioUplink) msg).data);
                    }
                }

                entity.timerCycle(System.currentTimeMillis());

                var pdu = entity.createPdu(random.nextInt(OPPORTUNITY_SIZE_MAX - OPPORTUNITY_SIZE_MIN) + OPPORTUNITY_SIZE_MIN);
                if (pdu != null) {
                    pair.push(new IwRadioUplink(pdu));
                }

                upperTransmission();
            }
        }

        private void upperTransmission() {
            packetId++;

            if (packetId >= MAX_PACKET_SEND_COUNT) {
                Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "ENOUGH TESTING TODAY");
                System.exit(0);
                return;
            }

            var packet = new OctetOutputStream();
            for (int i = 0; i < TRANSMISSION_SIZE / 4; i++) {
                packet.writeOctet4(packetId);
            }

            entity.receiveSdu(packet.toOctetString(), packetId);
        }

        @Override
        public void deliverSdu(RlcEntity entity, OctetString sdu) {
            receivedCounter++;

            var s = new OctetInputStream(sdu);
            int pi = s.readOctet4().intValue();
            Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "[%s] PDU %s RECEIVED, TOTAL COUNT %s B", tag, pi, receivedCounter * TRANSMISSION_SIZE);
        }

        @Override
        public void maxRetransmissionReached(RlcEntity entity) {
            Console.println(AnsiPalette.PAINT_LOG_ERROR, "[%s] RADIO LINK FAILURE", tag);
        }

        @Override
        public void sduSuccessfulDelivery(RlcEntity entity, int sduId) {
            Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "[%s] SDU %s DELIVERED", tag, sduId);
        }
    }
}
