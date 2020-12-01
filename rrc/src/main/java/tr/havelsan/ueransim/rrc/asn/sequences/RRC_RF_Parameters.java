/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqBandList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RF_Parameters__supportedBandListNR;

public class RRC_RF_Parameters extends RRC_Sequence {

    public RRC_RF_Parameters__supportedBandListNR supportedBandListNR;
    public RRC_BandCombinationList supportedBandCombinationList;
    public RRC_FreqBandList appliedFreqBandListFilter;
    public RRC_RF_Parameters__ext1 ext1;
    public RRC_RF_Parameters__ext2 ext2;
    public RRC_RF_Parameters__ext3 ext3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedBandListNR","supportedBandCombinationList","appliedFreqBandListFilter","ext1","ext2","ext3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedBandListNR","supportedBandCombinationList","appliedFreqBandListFilter","ext1","ext2","ext3" };
    }

    @Override
    public String getAsnName() {
        return "RF-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "RF-Parameters";
    }

}
