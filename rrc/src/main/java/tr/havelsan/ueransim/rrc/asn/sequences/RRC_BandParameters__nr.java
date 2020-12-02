/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CA_BandwidthClassNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;

public class RRC_BandParameters__nr extends RRC_Sequence {

    public RRC_FreqBandIndicatorNR bandNR;
    public RRC_CA_BandwidthClassNR ca_BandwidthClassDL_NR;
    public RRC_CA_BandwidthClassNR ca_BandwidthClassUL_NR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandNR","ca-BandwidthClassDL-NR","ca-BandwidthClassUL-NR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandNR","ca_BandwidthClassDL_NR","ca_BandwidthClassUL_NR" };
    }

}
