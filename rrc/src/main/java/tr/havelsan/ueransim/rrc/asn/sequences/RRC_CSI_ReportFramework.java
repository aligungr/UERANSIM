/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_ReportFramework extends RRC_Sequence {

    public RRC_Integer maxNumberPeriodicCSI_PerBWP_ForCSI_Report;
    public RRC_Integer maxNumberAperiodicCSI_PerBWP_ForCSI_Report;
    public RRC_Integer maxNumberSemiPersistentCSI_PerBWP_ForCSI_Report;
    public RRC_Integer maxNumberPeriodicCSI_PerBWP_ForBeamReport;
    public RRC_Integer maxNumberAperiodicCSI_PerBWP_ForBeamReport;
    public RRC_Integer maxNumberAperiodicCSI_triggeringStatePerCC;
    public RRC_Integer maxNumberSemiPersistentCSI_PerBWP_ForBeamReport;
    public RRC_Integer simultaneousCSI_ReportsPerCC;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberPeriodicCSI-PerBWP-ForCSI-Report","maxNumberAperiodicCSI-PerBWP-ForCSI-Report","maxNumberSemiPersistentCSI-PerBWP-ForCSI-Report","maxNumberPeriodicCSI-PerBWP-ForBeamReport","maxNumberAperiodicCSI-PerBWP-ForBeamReport","maxNumberAperiodicCSI-triggeringStatePerCC","maxNumberSemiPersistentCSI-PerBWP-ForBeamReport","simultaneousCSI-ReportsPerCC" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberPeriodicCSI_PerBWP_ForCSI_Report","maxNumberAperiodicCSI_PerBWP_ForCSI_Report","maxNumberSemiPersistentCSI_PerBWP_ForCSI_Report","maxNumberPeriodicCSI_PerBWP_ForBeamReport","maxNumberAperiodicCSI_PerBWP_ForBeamReport","maxNumberAperiodicCSI_triggeringStatePerCC","maxNumberSemiPersistentCSI_PerBWP_ForBeamReport","simultaneousCSI_ReportsPerCC" };
    }

    @Override
    public String getAsnName() {
        return "CSI-ReportFramework";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-ReportFramework";
    }

}
