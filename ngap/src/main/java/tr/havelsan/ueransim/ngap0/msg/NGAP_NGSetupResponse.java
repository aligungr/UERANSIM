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
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_UERetentionInformation;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RelativeAMFCapacity;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.NGAP_AMFName;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PLMNSupportList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_ServedGUAMIList;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CriticalityDiagnostics;

public class NGAP_NGSetupResponse extends NGAP_BaseMessage {

    public NGAP_NGSetupResponse() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.NGSetupResponse;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("NGSetupResponse");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 21;
    }

    @Override
    public int getPduType() {
        return 1;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 1, 96, 86, 80, 19, 147 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 0, 0, 1, 0, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_AMFName.class, NGAP_ServedGUAMIList.class, NGAP_RelativeAMFCapacity.class, NGAP_PLMNSupportList.class, NGAP_CriticalityDiagnostics.class, NGAP_UERetentionInformation.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 2, 2, 2, 0, 0 };
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
        return "NGSetupResponse";
    }

    @Override
    public String getXmlTagName() {
        return "NGSetupResponse";
    }

}
