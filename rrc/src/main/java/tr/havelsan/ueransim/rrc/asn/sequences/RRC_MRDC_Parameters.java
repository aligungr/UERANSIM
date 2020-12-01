/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MRDC_Parameters extends RRC_Sequence {

    public RRC_Integer singleUL_Transmission;
    public RRC_Integer dynamicPowerSharing;
    public RRC_Integer tdm_Pattern;
    public RRC_Integer ul_SharingEUTRA_NR;
    public RRC_Integer ul_SwitchingTimeEUTRA_NR;
    public RRC_Integer simultaneousRxTxInterBandENDC;
    public RRC_Integer asyncIntraBandENDC;
    public RRC_MRDC_Parameters__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "singleUL-Transmission","dynamicPowerSharing","tdm-Pattern","ul-SharingEUTRA-NR","ul-SwitchingTimeEUTRA-NR","simultaneousRxTxInterBandENDC","asyncIntraBandENDC","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "singleUL_Transmission","dynamicPowerSharing","tdm_Pattern","ul_SharingEUTRA_NR","ul_SwitchingTimeEUTRA_NR","simultaneousRxTxInterBandENDC","asyncIntraBandENDC","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MRDC-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "MRDC-Parameters";
    }

}
