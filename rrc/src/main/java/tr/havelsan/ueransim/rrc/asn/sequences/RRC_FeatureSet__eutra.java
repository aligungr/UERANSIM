/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEUTRA_DownlinkId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEUTRA_UplinkId;

public class RRC_FeatureSet__eutra extends RRC_Sequence {

    public RRC_FeatureSetEUTRA_DownlinkId downlinkSetEUTRA;
    public RRC_FeatureSetEUTRA_UplinkId uplinkSetEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "downlinkSetEUTRA","uplinkSetEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "downlinkSetEUTRA","uplinkSetEUTRA" };
    }

}
