/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CGI_InfoEUTRA__cgi_info_5GC;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiBandInfoListEUTRA;

public class RRC_CGI_InfoEUTRA extends RRC_Sequence {

    public RRC_CGI_InfoEUTRA__cgi_info_EPC cgi_info_EPC;
    public RRC_CGI_InfoEUTRA__cgi_info_5GC cgi_info_5GC;
    public RRC_FreqBandIndicatorEUTRA freqBandIndicator;
    public RRC_MultiBandInfoListEUTRA multiBandInfoList;
    public RRC_Integer freqBandIndicatorPriority;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cgi-info-EPC","cgi-info-5GC","freqBandIndicator","multiBandInfoList","freqBandIndicatorPriority" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cgi_info_EPC","cgi_info_5GC","freqBandIndicator","multiBandInfoList","freqBandIndicatorPriority" };
    }

    @Override
    public String getAsnName() {
        return "CGI-InfoEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CGI-InfoEUTRA";
    }

}
