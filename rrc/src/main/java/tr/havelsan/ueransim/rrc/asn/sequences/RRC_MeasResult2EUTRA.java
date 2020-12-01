/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_MeasResult2EUTRA extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA carrierFreq;
    public RRC_MeasResultEUTRA measResultServingCell;
    public RRC_MeasResultEUTRA measResultBestNeighCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","measResultServingCell","measResultBestNeighCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","measResultServingCell","measResultBestNeighCell" };
    }

    @Override
    public String getAsnName() {
        return "MeasResult2EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResult2EUTRA";
    }

}
