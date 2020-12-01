/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_RSTD_InfoList;

public class RRC_LocationMeasurementInfo extends RRC_Choice {

    public RRC_EUTRA_RSTD_InfoList eutra_RSTD;
    public RRC_Null eutra_FineTimingDetection;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra-RSTD","eutra-FineTimingDetection" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra_RSTD","eutra_FineTimingDetection" };
    }

    @Override
    public String getAsnName() {
        return "LocationMeasurementInfo";
    }

    @Override
    public String getXmlTagName() {
        return "LocationMeasurementInfo";
    }

}
