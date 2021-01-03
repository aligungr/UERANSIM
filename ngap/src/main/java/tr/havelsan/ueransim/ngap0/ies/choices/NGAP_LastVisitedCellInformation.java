/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_LastVisitedEUTRANCellInformation;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_LastVisitedGERANCellInformation;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_LastVisitedUTRANCellInformation;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_LastVisitedNGRANCellInformation;

public class NGAP_LastVisitedCellInformation extends NGAP_Choice {

    public NGAP_LastVisitedNGRANCellInformation nGRANCell;
    public NGAP_LastVisitedEUTRANCellInformation eUTRANCell;
    public NGAP_LastVisitedUTRANCellInformation uTRANCell;
    public NGAP_LastVisitedGERANCellInformation gERANCell;

    @Override
    public String getAsnName() {
        return "LastVisitedCellInformation";
    }

    @Override
    public String getXmlTagName() {
        return "LastVisitedCellInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGRANCell", "eUTRANCell", "uTRANCell", "gERANCell"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGRANCell", "eUTRANCell", "uTRANCell", "gERANCell"};
    }
}
