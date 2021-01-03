/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_I_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_NG_5G_S_TMSI;

public class RRC_PagingUE_Identity extends AsnChoice {
    public RRC_NG_5G_S_TMSI ng_5G_S_TMSI;
    public RRC_I_RNTI_Value fullI_RNTI;
}

