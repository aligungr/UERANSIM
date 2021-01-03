/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_UAC_AccessCategory1_SelectionAssistanceInfo extends AsnEnumerated {
    public static final RRC_UAC_AccessCategory1_SelectionAssistanceInfo A = new RRC_UAC_AccessCategory1_SelectionAssistanceInfo(0);
    public static final RRC_UAC_AccessCategory1_SelectionAssistanceInfo B = new RRC_UAC_AccessCategory1_SelectionAssistanceInfo(1);
    public static final RRC_UAC_AccessCategory1_SelectionAssistanceInfo C = new RRC_UAC_AccessCategory1_SelectionAssistanceInfo(2);

    private RRC_UAC_AccessCategory1_SelectionAssistanceInfo(long value) {
        super(value);
    }
}

