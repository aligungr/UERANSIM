/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetDownlinkId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEUTRA_DownlinkId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetEUTRA_UplinkId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FeatureSetUplinkId;

public class RRC_FeatureSet extends AsnChoice {
    public RRC_eutra_2 eutra;
    public RRC_nr_3 nr;

    public static class RRC_eutra_2 extends AsnSequence {
        public RRC_FeatureSetEUTRA_DownlinkId downlinkSetEUTRA; // mandatory
        public RRC_FeatureSetEUTRA_UplinkId uplinkSetEUTRA; // mandatory
    }

    public static class RRC_nr_3 extends AsnSequence {
        public RRC_FeatureSetDownlinkId downlinkSetNR; // mandatory
        public RRC_FeatureSetUplinkId uplinkSetNR; // mandatory
    }
}

