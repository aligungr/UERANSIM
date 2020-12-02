/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MIMO_ParametersPerBand__uplinkBeamManagement extends RRC_Sequence {

    public RRC_Integer maxNumberSRS_ResourcePerSet_BM;
    public RRC_Integer maxNumberSRS_ResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberSRS-ResourcePerSet-BM","maxNumberSRS-ResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberSRS_ResourcePerSet_BM","maxNumberSRS_ResourceSet" };
    }

}
