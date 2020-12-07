/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersDL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersUL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReducedAggregatedBandwidth;

public class RRC_OverheatingAssistance extends AsnSequence {
    public RRC_reducedMaxCCs reducedMaxCCs; // optional
    public RRC_reducedMaxBW_FR1 reducedMaxBW_FR1; // optional
    public RRC_reducedMaxBW_FR2 reducedMaxBW_FR2; // optional
    public RRC_reducedMaxMIMO_LayersFR1 reducedMaxMIMO_LayersFR1; // optional
    public RRC_reducedMaxMIMO_LayersFR2 reducedMaxMIMO_LayersFR2; // optional

    public static class RRC_reducedMaxBW_FR2 extends AsnSequence {
        public RRC_ReducedAggregatedBandwidth reducedBW_FR2_DL; // mandatory
        public RRC_ReducedAggregatedBandwidth reducedBW_FR2_UL; // mandatory
    }

    public static class RRC_reducedMaxCCs extends AsnSequence {
        public AsnInteger reducedCCsDL; // mandatory, VALUE(0..31)
        public AsnInteger reducedCCsUL; // mandatory, VALUE(0..31)
    }

    public static class RRC_reducedMaxMIMO_LayersFR2 extends AsnSequence {
        public RRC_MIMO_LayersDL reducedMIMO_LayersFR2_DL; // mandatory
        public RRC_MIMO_LayersUL reducedMIMO_LayersFR2_UL; // mandatory
    }

    public static class RRC_reducedMaxBW_FR1 extends AsnSequence {
        public RRC_ReducedAggregatedBandwidth reducedBW_FR1_DL; // mandatory
        public RRC_ReducedAggregatedBandwidth reducedBW_FR1_UL; // mandatory
    }

    public static class RRC_reducedMaxMIMO_LayersFR1 extends AsnSequence {
        public RRC_MIMO_LayersDL reducedMIMO_LayersFR1_DL; // mandatory
        public RRC_MIMO_LayersUL reducedMIMO_LayersFR1_UL; // mandatory
    }
}

