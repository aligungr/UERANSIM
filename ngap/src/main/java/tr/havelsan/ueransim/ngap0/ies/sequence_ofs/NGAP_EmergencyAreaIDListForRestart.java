/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_EmergencyAreaID;

import java.util.List;

public class NGAP_EmergencyAreaIDListForRestart extends NGAP_SequenceOf<NGAP_EmergencyAreaID> {

    public NGAP_EmergencyAreaIDListForRestart() {
        super();
    }

    public NGAP_EmergencyAreaIDListForRestart(List<NGAP_EmergencyAreaID> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDListForRestart";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDListForRestart";
    }

    @Override
    public Class<NGAP_EmergencyAreaID> getItemType() {
        return NGAP_EmergencyAreaID.class;
    }
}
