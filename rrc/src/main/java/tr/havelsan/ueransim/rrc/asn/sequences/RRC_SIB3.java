/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_IntraFreqBlackCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_IntraFreqNeighCellList;

public class RRC_SIB3 extends RRC_Sequence {

    public RRC_IntraFreqNeighCellList intraFreqNeighCellList;
    public RRC_IntraFreqBlackCellList intraFreqBlackCellList;
    public RRC_OctetString lateNonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "intraFreqNeighCellList","intraFreqBlackCellList","lateNonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "intraFreqNeighCellList","intraFreqBlackCellList","lateNonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SIB3";
    }

    @Override
    public String getXmlTagName() {
        return "SIB3";
    }

}
