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
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_EN_DCSONConfigurationTransfer;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SONConfigurationTransfer;

public class NGAP_UplinkRANConfigurationTransfer extends NGAP_BaseMessage {

    public NGAP_UplinkRANConfigurationTransfer() {

    }

    @Override
    public NgapMessageType getMessageType() {
        return NgapMessageType.UplinkRANConfigurationTransfer;
    }

    @Override
    public NgapProtocolIeType getProtocolIeType() {
        return NgapProtocolIeType.forMessage("UplinkRANConfigurationTransfer");
    }

    @Override
    public int getCriticality() {
        return 1;
    }

    @Override
    public int getProcedureCode() {
        return 48;
    }

    @Override
    public int getPduType() {
        return 0;
    }

    @Override
    public int[] getIeId() {
        return new int[] { 99, 158 };
    }

    @Override
    public int[] getIeCriticality() {
        return new int[] { 1, 1 };
    }

    @Override
    public Class<? extends NGAP_Value>[] getIeTypes() {
        return new Class[] { NGAP_SONConfigurationTransfer.class, NGAP_EN_DCSONConfigurationTransfer.class };
    }

    @Override
    public int[] getIePresence() {
        return new int[] { 0, 0 };
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
        return "UplinkRANConfigurationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkRANConfigurationTransfer";
    }

}
