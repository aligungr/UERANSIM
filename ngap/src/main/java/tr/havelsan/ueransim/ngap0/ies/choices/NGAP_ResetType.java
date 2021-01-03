/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_ResetAll;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_UE_associatedLogicalNG_connectionList;

public class NGAP_ResetType extends NGAP_Choice {

    public NGAP_ResetAll nG_Interface;
    public NGAP_UE_associatedLogicalNG_connectionList partOfNG_Interface;

    @Override
    public String getAsnName() {
        return "ResetType";
    }

    @Override
    public String getXmlTagName() {
        return "ResetType";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nG-Interface", "partOfNG-Interface"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nG_Interface", "partOfNG_Interface"};
    }
}
