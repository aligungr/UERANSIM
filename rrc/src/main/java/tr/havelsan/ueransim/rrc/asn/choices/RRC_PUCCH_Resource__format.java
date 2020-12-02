/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_PUCCH_Resource__format extends RRC_Choice {

    public RRC_PUCCH_format0 format0;
    public RRC_PUCCH_format1 format1;
    public RRC_PUCCH_format2 format2;
    public RRC_PUCCH_format3 format3;
    public RRC_PUCCH_format4 format4;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "format0","format1","format2","format3","format4" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "format0","format1","format2","format3","format4" };
    }

}
