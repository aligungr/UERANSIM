/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_DRX_Config__drx_LongCycleStartOffset extends RRC_Choice {

    public RRC_Integer ms10;
    public RRC_Integer ms20;
    public RRC_Integer ms32;
    public RRC_Integer ms40;
    public RRC_Integer ms60;
    public RRC_Integer ms64;
    public RRC_Integer ms70;
    public RRC_Integer ms80;
    public RRC_Integer ms128;
    public RRC_Integer ms160;
    public RRC_Integer ms256;
    public RRC_Integer ms320;
    public RRC_Integer ms512;
    public RRC_Integer ms640;
    public RRC_Integer ms1024;
    public RRC_Integer ms1280;
    public RRC_Integer ms2048;
    public RRC_Integer ms2560;
    public RRC_Integer ms5120;
    public RRC_Integer ms10240;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ms10","ms20","ms32","ms40","ms60","ms64","ms70","ms80","ms128","ms160","ms256","ms320","ms512","ms640","ms1024","ms1280","ms2048","ms2560","ms5120","ms10240" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ms10","ms20","ms32","ms40","ms60","ms64","ms70","ms80","ms128","ms160","ms256","ms320","ms512","ms640","ms1024","ms1280","ms2048","ms2560","ms5120","ms10240" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
