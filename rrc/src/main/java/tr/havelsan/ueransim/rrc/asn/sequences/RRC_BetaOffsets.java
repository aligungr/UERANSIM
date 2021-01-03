/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_BetaOffsets extends AsnSequence {
    public AsnInteger betaOffsetACK_Index1; // optional, VALUE(0..31)
    public AsnInteger betaOffsetACK_Index2; // optional, VALUE(0..31)
    public AsnInteger betaOffsetACK_Index3; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part1_Index1; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part1_Index2; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part2_Index1; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part2_Index2; // optional, VALUE(0..31)
}

