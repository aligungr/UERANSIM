/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_TrackingAreaCode;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CellIdentity_EUTRA_5GC;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RAN_AreaCode;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityList_EUTRA_5GC;

public class RRC_CellAccessRelatedInfo_EUTRA_5GC extends AsnSequence {
    public RRC_PLMN_IdentityList_EUTRA_5GC plmn_IdentityList_eutra_5gc; // mandatory
    public RRC_TrackingAreaCode trackingAreaCode_eutra_5gc; // mandatory
    public RRC_RAN_AreaCode ranac_5gc; // optional
    public RRC_CellIdentity_EUTRA_5GC cellIdentity_eutra_5gc; // mandatory
}

