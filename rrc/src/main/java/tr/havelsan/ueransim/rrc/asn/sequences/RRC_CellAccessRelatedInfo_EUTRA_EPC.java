/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityList_EUTRA_EPC;

public class RRC_CellAccessRelatedInfo_EUTRA_EPC extends AsnSequence {
    public RRC_PLMN_IdentityList_EUTRA_EPC plmn_IdentityList_eutra_epc; // mandatory
    public AsnBitString trackingAreaCode_eutra_epc; // mandatory, SIZE(16)
    public AsnBitString cellIdentity_eutra_epc; // mandatory, SIZE(28)
}

