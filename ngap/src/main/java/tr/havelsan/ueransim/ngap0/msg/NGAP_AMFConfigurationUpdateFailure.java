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
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_TimeToWait;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CriticalityDiagnostics;

public class NGAP_AMFConfigurationUpdateFailure extends NGAP_BaseMessage {

    public NGAP_AMFConfigurationUpdateFailure() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.AMFConfigurationUpdateFailure;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("AMFConfigurationUpdateFailure");
    }

    @Override
    public int getCriticality() {
        return 0;
    }

    @Override
    public int getProcedureCode() {
        return 0;
    }

    @Override
    public int getPduType() {
        return 2;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 15, 107, 19 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 1, 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_Cause.class, NGAP_TimeToWait.class, NGAP_CriticalityDiagnostics.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 2, 0, 0 };
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
        return "AMFConfigurationUpdateFailure";
    }

    @Override
    public String getXmlTagName() {
        return "AMFConfigurationUpdateFailure";
    }

}
