/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_AMFPointer;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_AMFRegionID;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_AMFSetID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GUAMI extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_AMFRegionID aMFRegionID;
    public NGAP_AMFSetID aMFSetID;
    public NGAP_AMFPointer aMFPointer;

    @Override
    public String getAsnName() {
        return "GUAMI";
    }

    @Override
    public String getXmlTagName() {
        return "GUAMI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "aMFRegionID", "aMFSetID", "aMFPointer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "aMFRegionID", "aMFSetID", "aMFPointer"};
    }
}
