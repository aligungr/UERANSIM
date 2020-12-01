/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BandCombination_v1560 extends RRC_Sequence {

    public RRC_Integer ne_DC_BC;
    public RRC_CA_ParametersNRDC ca_ParametersNRDC;
    public RRC_CA_ParametersEUTRA_v1560 ca_ParametersEUTRA_v1560;
    public RRC_CA_ParametersNR_v1560 ca_ParametersNR_v1560;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ne-DC-BC","ca-ParametersNRDC","ca-ParametersEUTRA-v1560","ca-ParametersNR-v1560" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ne_DC_BC","ca_ParametersNRDC","ca_ParametersEUTRA_v1560","ca_ParametersNR_v1560" };
    }

    @Override
    public String getAsnName() {
        return "BandCombination-v1560";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombination-v1560";
    }

}
