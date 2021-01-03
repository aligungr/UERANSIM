/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MAC_ParametersXDD_Diff extends AsnSequence {
    public RRC_skipUplinkTxDynamic skipUplinkTxDynamic; // optional
    public RRC_logicalChannelSR_DelayTimer_2 logicalChannelSR_DelayTimer; // optional
    public RRC_longDRX_Cycle longDRX_Cycle; // optional
    public RRC_shortDRX_Cycle shortDRX_Cycle; // optional
    public RRC_multipleSR_Configurations multipleSR_Configurations; // optional
    public RRC_multipleConfiguredGrants multipleConfiguredGrants; // optional

    public static class RRC_multipleConfiguredGrants extends AsnEnumerated {
        public static final RRC_multipleConfiguredGrants SUPPORTED = new RRC_multipleConfiguredGrants(0);
    
        private RRC_multipleConfiguredGrants(long value) {
            super(value);
        }
    }

    public static class RRC_shortDRX_Cycle extends AsnEnumerated {
        public static final RRC_shortDRX_Cycle SUPPORTED = new RRC_shortDRX_Cycle(0);
    
        private RRC_shortDRX_Cycle(long value) {
            super(value);
        }
    }

    public static class RRC_skipUplinkTxDynamic extends AsnEnumerated {
        public static final RRC_skipUplinkTxDynamic SUPPORTED = new RRC_skipUplinkTxDynamic(0);
    
        private RRC_skipUplinkTxDynamic(long value) {
            super(value);
        }
    }

    public static class RRC_multipleSR_Configurations extends AsnEnumerated {
        public static final RRC_multipleSR_Configurations SUPPORTED = new RRC_multipleSR_Configurations(0);
    
        private RRC_multipleSR_Configurations(long value) {
            super(value);
        }
    }

    public static class RRC_logicalChannelSR_DelayTimer_2 extends AsnEnumerated {
        public static final RRC_logicalChannelSR_DelayTimer_2 SUPPORTED = new RRC_logicalChannelSR_DelayTimer_2(0);
    
        private RRC_logicalChannelSR_DelayTimer_2(long value) {
            super(value);
        }
    }

    public static class RRC_longDRX_Cycle extends AsnEnumerated {
        public static final RRC_longDRX_Cycle SUPPORTED = new RRC_longDRX_Cycle(0);
    
        private RRC_longDRX_Cycle(long value) {
            super(value);
        }
    }
}

