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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DRB_ToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DRB_ToReleaseList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRB_ToAddModList;

public class RRC_RadioBearerConfig extends AsnSequence {
    public RRC_SRB_ToAddModList srb_ToAddModList; // optional
    public RRC_srb3_ToRelease srb3_ToRelease; // optional
    public RRC_DRB_ToAddModList drb_ToAddModList; // optional
    public RRC_DRB_ToReleaseList drb_ToReleaseList; // optional
    public RRC_SecurityConfig securityConfig; // optional

    public static class RRC_srb3_ToRelease extends AsnEnumerated {
        public static final RRC_srb3_ToRelease TRUE = new RRC_srb3_ToRelease(0);
    
        private RRC_srb3_ToRelease(long value) {
            super(value);
        }
    }
}

