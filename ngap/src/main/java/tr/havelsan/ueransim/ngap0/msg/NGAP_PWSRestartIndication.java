/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.msg;

import tr.havelsan.ueransim.ngap0.NgapMessageType;
import tr.havelsan.ueransim.ngap0.NgapProtocolIeType;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_CellIDListForRestart;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_GlobalRANNodeID;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_EmergencyAreaIDListForRestart;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_TAIListForRestart;

public class NGAP_PWSRestartIndication extends NGAP_BaseMessage {

    public NGAP_PWSRestartIndication() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.PWSRestartIndication;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("PWSRestartIndication");
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 34;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 16, 27, 104, 23 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 0, 0 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_CellIDListForRestart.class, NGAP_GlobalRANNodeID.class, NGAP_TAIListForRestart.class, NGAP_EmergencyAreaIDListForRestart.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 2, 0 };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[] { "protocolIEs" };
    }

    @Override
    public String[] getMemberNames() {
        return new String[] { "protocolIEs" };
    }

    @Override
    public String getAsnName() {
        return "PWSRestartIndication";
    }

    @Override
    public String getXmlTagName() {
        return "PWSRestartIndication";
    }

}
