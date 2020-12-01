/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.core;

public class RRC_Null extends RRC_Value {

    @Override
    public String getAsnName() {
        return "NULL";
    }

    @Override
    public String getXmlTagName() {
        return "NULL";
    }
}
