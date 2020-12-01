/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersCommon__ext1 extends RRC_Sequence {

    public RRC_Integer eventB_MeasAndReport;
    public RRC_Integer handoverFDD_TDD;
    public RRC_Integer eutra_CGI_Reporting;
    public RRC_Integer nr_CGI_Reporting;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eventB-MeasAndReport","handoverFDD-TDD","eutra-CGI-Reporting","nr-CGI-Reporting" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eventB_MeasAndReport","handoverFDD_TDD","eutra_CGI_Reporting","nr_CGI_Reporting" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
