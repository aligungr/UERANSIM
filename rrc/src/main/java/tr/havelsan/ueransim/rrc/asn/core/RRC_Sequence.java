/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.core;

public abstract class RRC_Sequence extends RRC_Value {

    public abstract String[] getMemberNames();

    public abstract String[] getMemberIdentifiers();

    @Override
    public String getAsnName() {
        return "SEQUENCE";
    }

    @Override
    public String getXmlTagName() {
        return "SEQUENCE";
    }
}
