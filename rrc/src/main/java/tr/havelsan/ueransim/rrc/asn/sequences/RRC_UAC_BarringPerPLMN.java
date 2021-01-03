/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_UAC_BarringInfoSetIndex;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UAC_BarringPerCatList;

public class RRC_UAC_BarringPerPLMN extends AsnSequence {
    public AsnInteger plmn_IdentityIndex; // mandatory, VALUE(1..12)
    public RRC_uac_ACBarringListType uac_ACBarringListType; // optional

    public static class RRC_uac_ACBarringListType extends AsnChoice {
        public RRC_uac_ImplicitACBarringList uac_ImplicitACBarringList; // SIZE(63)
        public RRC_UAC_BarringPerCatList uac_ExplicitACBarringList;
    
        // SIZE(63)
        public static class RRC_uac_ImplicitACBarringList extends AsnSequenceOf<RRC_UAC_BarringInfoSetIndex> {
        }
    }
}

