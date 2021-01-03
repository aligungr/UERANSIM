/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_TNLAssociationUsage extends NGAP_Enumerated {

    public static final NGAP_TNLAssociationUsage UE = new NGAP_TNLAssociationUsage("ue");
    public static final NGAP_TNLAssociationUsage NON_UE = new NGAP_TNLAssociationUsage("non-ue");
    public static final NGAP_TNLAssociationUsage BOTH = new NGAP_TNLAssociationUsage("both");

    protected NGAP_TNLAssociationUsage(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TNLAssociationUsage";
    }

    @Override
    public String getXmlTagName() {
        return "TNLAssociationUsage";
    }
}
