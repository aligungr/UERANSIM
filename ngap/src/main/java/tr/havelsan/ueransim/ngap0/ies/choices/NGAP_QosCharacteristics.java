/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_Dynamic5QIDescriptor;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_NonDynamic5QIDescriptor;

public class NGAP_QosCharacteristics extends NGAP_Choice {

    public NGAP_NonDynamic5QIDescriptor nonDynamic5QI;
    public NGAP_Dynamic5QIDescriptor dynamic5QI;

    @Override
    public String getAsnName() {
        return "QosCharacteristics";
    }

    @Override
    public String getXmlTagName() {
        return "QosCharacteristics";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nonDynamic5QI", "dynamic5QI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nonDynamic5QI", "dynamic5QI"};
    }
}
