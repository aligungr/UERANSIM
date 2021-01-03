/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_CA_BandwidthClassEUTRA extends AsnEnumerated {
    public static final RRC_CA_BandwidthClassEUTRA A = new RRC_CA_BandwidthClassEUTRA(0);
    public static final RRC_CA_BandwidthClassEUTRA B = new RRC_CA_BandwidthClassEUTRA(1);
    public static final RRC_CA_BandwidthClassEUTRA C = new RRC_CA_BandwidthClassEUTRA(2);
    public static final RRC_CA_BandwidthClassEUTRA D = new RRC_CA_BandwidthClassEUTRA(3);
    public static final RRC_CA_BandwidthClassEUTRA E = new RRC_CA_BandwidthClassEUTRA(4);
    public static final RRC_CA_BandwidthClassEUTRA F = new RRC_CA_BandwidthClassEUTRA(5);

    private RRC_CA_BandwidthClassEUTRA(long value) {
        super(value);
    }
}

