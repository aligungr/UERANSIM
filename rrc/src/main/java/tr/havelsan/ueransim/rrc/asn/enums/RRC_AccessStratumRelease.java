/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_AccessStratumRelease extends RRC_Enumerated {

    public static final RRC_AccessStratumRelease REL15 = new RRC_AccessStratumRelease("rel15");
    public static final RRC_AccessStratumRelease SPARE7 = new RRC_AccessStratumRelease("spare7");
    public static final RRC_AccessStratumRelease SPARE6 = new RRC_AccessStratumRelease("spare6");
    public static final RRC_AccessStratumRelease SPARE5 = new RRC_AccessStratumRelease("spare5");
    public static final RRC_AccessStratumRelease SPARE4 = new RRC_AccessStratumRelease("spare4");
    public static final RRC_AccessStratumRelease SPARE3 = new RRC_AccessStratumRelease("spare3");
    public static final RRC_AccessStratumRelease SPARE2 = new RRC_AccessStratumRelease("spare2");
    public static final RRC_AccessStratumRelease SPARE1 = new RRC_AccessStratumRelease("spare1");

    protected RRC_AccessStratumRelease(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "AccessStratumRelease";
    }

    @Override
    public String getXmlTagName() {
        return "AccessStratumRelease";
    }

}
