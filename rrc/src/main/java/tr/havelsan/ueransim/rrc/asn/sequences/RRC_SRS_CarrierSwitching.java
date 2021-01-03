/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_SRS_CarrierSwitching extends AsnSequence {
    public AsnInteger srs_SwitchFromServCellIndex; // optional, VALUE(0..31)
    public RRC_srs_SwitchFromCarrier srs_SwitchFromCarrier; // mandatory
    public RRC_srs_TPC_PDCCH_Group srs_TPC_PDCCH_Group; // optional
    public RRC_monitoringCells monitoringCells; // optional, SIZE(1..32)

    // SIZE(1..32)
    public static class RRC_monitoringCells extends AsnSequenceOf<RRC_ServCellIndex> {
    }

    public static class RRC_srs_SwitchFromCarrier extends AsnEnumerated {
        public static final RRC_srs_SwitchFromCarrier SUL = new RRC_srs_SwitchFromCarrier(0);
        public static final RRC_srs_SwitchFromCarrier NUL = new RRC_srs_SwitchFromCarrier(1);
    
        private RRC_srs_SwitchFromCarrier(long value) {
            super(value);
        }
    }

    public static class RRC_srs_TPC_PDCCH_Group extends AsnChoice {
        public RRC_typeA typeA; // SIZE(1..32)
        public RRC_SRS_TPC_PDCCH_Config typeB;
    
        // SIZE(1..32)
        public static class RRC_typeA extends AsnSequenceOf<RRC_SRS_TPC_PDCCH_Config> {
        }
    }
}

