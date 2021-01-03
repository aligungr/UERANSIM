/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CarrierInfoNR;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RedirectedCarrierInfo_EUTRA;

public class RRC_RedirectedCarrierInfo extends AsnChoice {
    public RRC_CarrierInfoNR nr;
    public RRC_RedirectedCarrierInfo_EUTRA eutra;
}

