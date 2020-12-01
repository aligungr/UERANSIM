/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SRS_Resources extends RRC_Sequence {

    public RRC_Integer maxNumberAperiodicSRS_PerBWP;
    public RRC_Integer maxNumberAperiodicSRS_PerBWP_PerSlot;
    public RRC_Integer maxNumberPeriodicSRS_PerBWP;
    public RRC_Integer maxNumberPeriodicSRS_PerBWP_PerSlot;
    public RRC_Integer maxNumberSemiPersistentSRS_PerBWP;
    public RRC_Integer maxNumberSemiPersistentSRS_PerBWP_PerSlot;
    public RRC_Integer maxNumberSRS_Ports_PerResource;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberAperiodicSRS-PerBWP","maxNumberAperiodicSRS-PerBWP-PerSlot","maxNumberPeriodicSRS-PerBWP","maxNumberPeriodicSRS-PerBWP-PerSlot","maxNumberSemiPersistentSRS-PerBWP","maxNumberSemiPersistentSRS-PerBWP-PerSlot","maxNumberSRS-Ports-PerResource" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberAperiodicSRS_PerBWP","maxNumberAperiodicSRS_PerBWP_PerSlot","maxNumberPeriodicSRS_PerBWP","maxNumberPeriodicSRS_PerBWP_PerSlot","maxNumberSemiPersistentSRS_PerBWP","maxNumberSemiPersistentSRS_PerBWP_PerSlot","maxNumberSRS_Ports_PerResource" };
    }

    @Override
    public String getAsnName() {
        return "SRS-Resources";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-Resources";
    }

}
