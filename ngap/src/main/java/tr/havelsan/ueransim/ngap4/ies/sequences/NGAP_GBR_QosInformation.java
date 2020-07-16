package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_NotificationControl;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_BitRate;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PacketLossRate;

public class NGAP_GBR_QosInformation extends NgapSequence {

    public NGAP_BitRate maximumFlowBitRateDL;
    public NGAP_BitRate maximumFlowBitRateUL;
    public NGAP_BitRate guaranteedFlowBitRateDL;
    public NGAP_BitRate guaranteedFlowBitRateUL;
    public NGAP_NotificationControl notificationControl;
    public NGAP_PacketLossRate maximumPacketLossRateDL;
    public NGAP_PacketLossRate maximumPacketLossRateUL;

    @Override
    protected String getAsnName() {
        return "GBR-QosInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "GBR-QosInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"maximumFlowBitRateDL", "maximumFlowBitRateUL", "guaranteedFlowBitRateDL", "guaranteedFlowBitRateUL", "notificationControl", "maximumPacketLossRateDL", "maximumPacketLossRateUL"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"maximumFlowBitRateDL", "maximumFlowBitRateUL", "guaranteedFlowBitRateDL", "guaranteedFlowBitRateUL", "notificationControl", "maximumPacketLossRateDL", "maximumPacketLossRateUL"};
    }
}
