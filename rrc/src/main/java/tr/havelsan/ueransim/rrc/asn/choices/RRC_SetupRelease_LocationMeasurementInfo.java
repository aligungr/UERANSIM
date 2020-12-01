/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;

public class RRC_SetupRelease_LocationMeasurementInfo extends RRC_Choice {

    public RRC_Null release;
    public RRC_LocationMeasurementInfo setup;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "release","setup" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "release","setup" };
    }

    @Override
    public String getAsnName() {
        return "SetupRelease_LocationMeasurementInfo";
    }

    @Override
    public String getXmlTagName() {
        return "SetupRelease_LocationMeasurementInfo";
    }

}
