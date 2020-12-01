/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB2 extends RRC_Sequence {

    public RRC_SIB2__cellReselectionInfoCommon cellReselectionInfoCommon;
    public RRC_SIB2__cellReselectionServingFreqInfo cellReselectionServingFreqInfo;
    public RRC_SIB2__intraFreqCellReselectionInfo intraFreqCellReselectionInfo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellReselectionInfoCommon","cellReselectionServingFreqInfo","intraFreqCellReselectionInfo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellReselectionInfoCommon","cellReselectionServingFreqInfo","intraFreqCellReselectionInfo" };
    }

    @Override
    public String getAsnName() {
        return "SIB2";
    }

    @Override
    public String getXmlTagName() {
        return "SIB2";
    }

}
