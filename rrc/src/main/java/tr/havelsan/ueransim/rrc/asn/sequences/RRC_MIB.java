/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MIB extends RRC_Sequence {

    public RRC_BitString systemFrameNumber;
    public RRC_Integer subCarrierSpacingCommon;
    public RRC_Integer ssb_SubcarrierOffset;
    public RRC_Integer dmrs_TypeA_Position;
    public RRC_PDCCH_ConfigSIB1 pdcch_ConfigSIB1;
    public RRC_Integer cellBarred;
    public RRC_Integer intraFreqReselection;
    public RRC_BitString spare;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "systemFrameNumber","subCarrierSpacingCommon","ssb-SubcarrierOffset","dmrs-TypeA-Position","pdcch-ConfigSIB1","cellBarred","intraFreqReselection","spare" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "systemFrameNumber","subCarrierSpacingCommon","ssb_SubcarrierOffset","dmrs_TypeA_Position","pdcch_ConfigSIB1","cellBarred","intraFreqReselection","spare" };
    }

    @Override
    public String getAsnName() {
        return "MIB";
    }

    @Override
    public String getXmlTagName() {
        return "MIB";
    }

}
