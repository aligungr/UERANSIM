/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CA_BandwidthClassEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;

public class RRC_BandParameters__eutra extends RRC_Sequence {

    public RRC_FreqBandIndicatorEUTRA bandEUTRA;
    public RRC_CA_BandwidthClassEUTRA ca_BandwidthClassDL_EUTRA;
    public RRC_CA_BandwidthClassEUTRA ca_BandwidthClassUL_EUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandEUTRA","ca-BandwidthClassDL-EUTRA","ca-BandwidthClassUL-EUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandEUTRA","ca_BandwidthClassDL_EUTRA","ca_BandwidthClassUL_EUTRA" };
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
