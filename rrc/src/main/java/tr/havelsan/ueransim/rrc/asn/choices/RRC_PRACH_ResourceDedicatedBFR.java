/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BFR_CSIRS_Resource;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BFR_SSB_Resource;

public class RRC_PRACH_ResourceDedicatedBFR extends AsnChoice {
    public RRC_BFR_SSB_Resource ssb;
    public RRC_BFR_CSIRS_Resource csi_RS;
}

