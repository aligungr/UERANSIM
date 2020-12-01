/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SI_RequestConfig__si_RequestResources;

public class RRC_SI_RequestConfig extends RRC_Sequence {

    public RRC_SI_RequestConfig__rach_OccasionsSI rach_OccasionsSI;
    public RRC_Integer si_RequestPeriod;
    public RRC_SI_RequestConfig__si_RequestResources si_RequestResources;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rach-OccasionsSI","si-RequestPeriod","si-RequestResources" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rach_OccasionsSI","si_RequestPeriod","si_RequestResources" };
    }

    @Override
    public String getAsnName() {
        return "SI-RequestConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SI-RequestConfig";
    }

}
