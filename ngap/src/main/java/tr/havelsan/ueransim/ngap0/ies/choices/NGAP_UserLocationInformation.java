/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UserLocationInformationEUTRA;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UserLocationInformationN3IWF;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UserLocationInformationNR;

public class NGAP_UserLocationInformation extends NGAP_Choice {

    public NGAP_UserLocationInformationEUTRA userLocationInformationEUTRA;
    public NGAP_UserLocationInformationNR userLocationInformationNR;
    public NGAP_UserLocationInformationN3IWF userLocationInformationN3IWF;

    @Override
    public String getAsnName() {
        return "UserLocationInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UserLocationInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"userLocationInformationEUTRA", "userLocationInformationNR", "userLocationInformationN3IWF"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"userLocationInformationEUTRA", "userLocationInformationNR", "userLocationInformationN3IWF"};
    }
}
