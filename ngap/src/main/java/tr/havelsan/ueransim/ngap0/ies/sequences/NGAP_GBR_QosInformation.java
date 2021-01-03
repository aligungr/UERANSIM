/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_NotificationControl;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_BitRate;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_PacketLossRate;

public class NGAP_GBR_QosInformation extends NGAP_Sequence {

    public NGAP_BitRate maximumFlowBitRateDL;
    public NGAP_BitRate maximumFlowBitRateUL;
    public NGAP_BitRate guaranteedFlowBitRateDL;
    public NGAP_BitRate guaranteedFlowBitRateUL;
    public NGAP_NotificationControl notificationControl;
    public NGAP_PacketLossRate maximumPacketLossRateDL;
    public NGAP_PacketLossRate maximumPacketLossRateUL;

    @Override
    public String getAsnName() {
        return "GBR-QosInformation";
    }

    @Override
    public String getXmlTagName() {
        return "GBR-QosInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"maximumFlowBitRateDL", "maximumFlowBitRateUL", "guaranteedFlowBitRateDL", "guaranteedFlowBitRateUL", "notificationControl", "maximumPacketLossRateDL", "maximumPacketLossRateUL"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"maximumFlowBitRateDL", "maximumFlowBitRateUL", "guaranteedFlowBitRateDL", "guaranteedFlowBitRateUL", "notificationControl", "maximumPacketLossRateDL", "maximumPacketLossRateUL"};
    }
}
