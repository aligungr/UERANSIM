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
    private static final int TRANSMISSION_PERIOD = 1; // ms'de bir random packet
    private static final int TRANSMISSION_SIZE = 2000;

    // LOWER LAYER
    private static final int OPPORTUNITY_PERIOD_MIN = 1;
    private static final int OPPORTUNITY_PERIOD_MAX = 3;
    private static final int OPPORTUNITY_SIZE_MIN = 1000;
    private static final int OPPORTUNITY_SIZE_MAX = 3000;

    // GENERAL
    private static final int MAX_PACKET_SEND_COUNT = 60000;
    private static final Class<?> ENTITY = TmEntity.class;

    public static void main(String[] args) {
        var ueLower = new LowerTask();
        var gnbLower = new LowerTask();
        var ueRlc = new RlcTask();
        var gnbRlc = new RlcTask();
        var ueUpper = new UpperTask();
        var gnbUpper = new UpperTask();

        ueLower.rlcTask = ueRlc;
        gnbLower.rlcTask = gnbRlc;
        ueRlc.lower = ueLower;
        ueRlc.upper = ueUpper;
        gnbRlc.lower = gnbLower;
        gnbRlc.upper = gnbUpper;
        ueUpper.rlcTask = ueRlc;
        gnbUpper.rlcTask = gnbRlc;
        ueLower.pair = gnbLower;
        gnbLower.pair = ueLower;

        ueLower.tag = "UE";
        ueRlc.tag = "UE";
        ueUpper.tag = "UE";
        gnbLower.tag = "GNB";
        gnbRlc.tag = "GNB";
        gnbUpper.tag = "GNB";

        ueLower.start();
        gnbLower.start();
        ueRlc.start();
        gnbRlc.start();
        ueUpper.start();
        gnbUpper.start();
    }

    private static class LowerTask extends NtsTask {
        public RlcTask rlcTask;
        public LowerTask pair;
        public String tag;

        public LowerTask() {
            super(true);
        }

        @Override
        protected void main() {
            pushDelayed(this::opportunityLoop, nextOpportunityDelay());
            while (true) {
                var msg = take();

                if (msg instanceof IwRadioDownlink) {
                    pair.push(new IwRadioUplink(((IwRadioDownlink) msg).data));
                } else if (msg instanceof IwRadioUplink) {
                    rlcTask.push(msg);
                }
            }
        }

        private int nextOpportunityDelay() {
            return new Random().nextInt(OPPORTUNITY_PERIOD_MAX - OPPORTUNITY_PERIOD_MIN) + OPPORTUNITY_PERIOD_MIN;
        }

        private int nextOpportunitySize() {
            return new Random().nextInt(OPPORTUNITY_SIZE_MAX - OPPORTUNITY_SIZE_MIN) + OPPORTUNITY_SIZE_MIN;
        }

        private void opportunityLoop() {
            pushDelayed(this::opportunityLoop, nextOpportunityDelay());
            rlcTask.push(new IwOpportunity(nextOpportunitySize()));
        }
    }

    private static class RlcTask extends NtsTask implements IRlcConsumer {
        public RlcEntity entity;
        public LowerTask lower;
        public UpperTask upper;
        public String tag;

        public RlcTask() {
            super(false);
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
                    if (msg instanceof IwReceiveSdu) {
                        entity.receiveSdu(((IwReceiveSdu) msg).data, ((IwReceiveSdu) msg).sduId);
                    } else if (msg instanceof IwOpportunity) {
                        var pdu = entity.createPdu(((IwOpportunity) msg).size);
                        if (pdu != null)
                            lower.push(new IwRadioDownlink(pdu));
                    } else if (msg instanceof IwRadioUplink) {
                        entity.receivePdu(((IwRadioUplink) msg).data);
                    }
                }

                entity.timerCycle(System.currentTimeMillis());
            }
        }

        @Override
        public void deliverSdu(RlcEntity entity, OctetString sdu) {
            upper.push(new IwRadioUplink(sdu));
        }

        @Override
        public void maxRetransmissionReached(RlcEntity entity) {
            upper.push(new IwRadioFailure());
        }

        @Override
        public void sduSuccessfulDelivery(RlcEntity entity, int sduId) {
            upper.push(new IwSentIndication(sduId));
        }
    }

    private static class UpperTask extends NtsTask {
        public RlcTask rlcTask;
        public int packetId;
        public String tag;

        private int receivedCounter = 0;

        public UpperTask() {
            super(true);
        }

        @Override
        protected void main() {
            pushDelayed(this::transmitLoop, TRANSMISSION_PERIOD);
            while (true) {
                var msg = take();
                if (msg instanceof IwRadioFailure) {
                    Console.println(AnsiPalette.PAINT_LOG_ERROR, "[%s] RADIO LINK FAILURE", tag);
                } else if (msg instanceof IwSentIndication) {
                    Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "[%s] SDU %s DELIVERED", tag, ((IwSentIndication) msg).sduId);
                } else if (msg instanceof IwRadioUplink) {
                    receivedCounter++;

                    var s = new OctetInputStream(((IwRadioUplink) msg).data);
                    int pi = s.readOctet4().intValue();
                    Console.println(AnsiPalette.PAINT_LOG_SUCCESS, "[%s] PDU %s RECEIVED, TOTAL COUNT %s", tag, pi, receivedCounter);
                }
            }
        }

        private void transmitLoop() {
            pushDelayed(this::transmitLoop, TRANSMISSION_PERIOD);

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

            rlcTask.push(new IwReceiveSdu(packet.toOctetString(), packetId));
        }
    }
}
