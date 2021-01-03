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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RadioLinkMonitoringRS_Id;

public class RRC_RadioLinkMonitoringConfig extends AsnSequence {
    public RRC_failureDetectionResourcesToAddModList failureDetectionResourcesToAddModList; // optional, SIZE(1..10)
    public RRC_failureDetectionResourcesToReleaseList failureDetectionResourcesToReleaseList; // optional, SIZE(1..10)
    public RRC_beamFailureInstanceMaxCount beamFailureInstanceMaxCount; // optional
    public RRC_beamFailureDetectionTimer beamFailureDetectionTimer; // optional

    // SIZE(1..10)
    public static class RRC_failureDetectionResourcesToReleaseList extends AsnSequenceOf<RRC_RadioLinkMonitoringRS_Id> {
    }

    // SIZE(1..10)
    public static class RRC_failureDetectionResourcesToAddModList extends AsnSequenceOf<RRC_RadioLinkMonitoringRS> {
    }

    public static class RRC_beamFailureInstanceMaxCount extends AsnEnumerated {
        public static final RRC_beamFailureInstanceMaxCount N1 = new RRC_beamFailureInstanceMaxCount(0);
        public static final RRC_beamFailureInstanceMaxCount N2 = new RRC_beamFailureInstanceMaxCount(1);
        public static final RRC_beamFailureInstanceMaxCount N3 = new RRC_beamFailureInstanceMaxCount(2);
        public static final RRC_beamFailureInstanceMaxCount N4 = new RRC_beamFailureInstanceMaxCount(3);
        public static final RRC_beamFailureInstanceMaxCount N5 = new RRC_beamFailureInstanceMaxCount(4);
        public static final RRC_beamFailureInstanceMaxCount N6 = new RRC_beamFailureInstanceMaxCount(5);
        public static final RRC_beamFailureInstanceMaxCount N8 = new RRC_beamFailureInstanceMaxCount(6);
        public static final RRC_beamFailureInstanceMaxCount N10 = new RRC_beamFailureInstanceMaxCount(7);
    
        private RRC_beamFailureInstanceMaxCount(long value) {
            super(value);
        }
    }

    public static class RRC_beamFailureDetectionTimer extends AsnEnumerated {
        public static final RRC_beamFailureDetectionTimer PBFD1 = new RRC_beamFailureDetectionTimer(0);
        public static final RRC_beamFailureDetectionTimer PBFD2 = new RRC_beamFailureDetectionTimer(1);
        public static final RRC_beamFailureDetectionTimer PBFD3 = new RRC_beamFailureDetectionTimer(2);
        public static final RRC_beamFailureDetectionTimer PBFD4 = new RRC_beamFailureDetectionTimer(3);
        public static final RRC_beamFailureDetectionTimer PBFD5 = new RRC_beamFailureDetectionTimer(4);
        public static final RRC_beamFailureDetectionTimer PBFD6 = new RRC_beamFailureDetectionTimer(5);
        public static final RRC_beamFailureDetectionTimer PBFD8 = new RRC_beamFailureDetectionTimer(6);
        public static final RRC_beamFailureDetectionTimer PBFD10 = new RRC_beamFailureDetectionTimer(7);
    
        private RRC_beamFailureDetectionTimer(long value) {
            super(value);
        }
    }
}

