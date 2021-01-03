/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;

import java.util.List;

public class NGAP_EquivalentPLMNs extends NGAP_SequenceOf<NGAP_PLMNIdentity> {

    public NGAP_EquivalentPLMNs() {
        super();
    }

    public NGAP_EquivalentPLMNs(List<NGAP_PLMNIdentity> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EquivalentPLMNs";
    }

    @Override
    public String getXmlTagName() {
        return "EquivalentPLMNs";
    }

    @Override
    public Class<NGAP_PLMNIdentity> getItemType() {
        return NGAP_PLMNIdentity.class;
    }
}
