/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_PollByte extends RRC_Enumerated {

    public static final RRC_PollByte KB1 = new RRC_PollByte("kB1");
    public static final RRC_PollByte KB2 = new RRC_PollByte("kB2");
    public static final RRC_PollByte KB5 = new RRC_PollByte("kB5");
    public static final RRC_PollByte KB8 = new RRC_PollByte("kB8");
    public static final RRC_PollByte KB10 = new RRC_PollByte("kB10");
    public static final RRC_PollByte KB15 = new RRC_PollByte("kB15");
    public static final RRC_PollByte KB25 = new RRC_PollByte("kB25");
    public static final RRC_PollByte KB50 = new RRC_PollByte("kB50");
    public static final RRC_PollByte KB75 = new RRC_PollByte("kB75");
    public static final RRC_PollByte KB100 = new RRC_PollByte("kB100");
    public static final RRC_PollByte KB125 = new RRC_PollByte("kB125");
    public static final RRC_PollByte KB250 = new RRC_PollByte("kB250");
    public static final RRC_PollByte KB375 = new RRC_PollByte("kB375");
    public static final RRC_PollByte KB500 = new RRC_PollByte("kB500");
    public static final RRC_PollByte KB750 = new RRC_PollByte("kB750");
    public static final RRC_PollByte KB1000 = new RRC_PollByte("kB1000");
    public static final RRC_PollByte KB1250 = new RRC_PollByte("kB1250");
    public static final RRC_PollByte KB1500 = new RRC_PollByte("kB1500");
    public static final RRC_PollByte KB2000 = new RRC_PollByte("kB2000");
    public static final RRC_PollByte KB3000 = new RRC_PollByte("kB3000");
    public static final RRC_PollByte KB4000 = new RRC_PollByte("kB4000");
    public static final RRC_PollByte KB4500 = new RRC_PollByte("kB4500");
    public static final RRC_PollByte KB5000 = new RRC_PollByte("kB5000");
    public static final RRC_PollByte KB5500 = new RRC_PollByte("kB5500");
    public static final RRC_PollByte KB6000 = new RRC_PollByte("kB6000");
    public static final RRC_PollByte KB6500 = new RRC_PollByte("kB6500");
    public static final RRC_PollByte KB7000 = new RRC_PollByte("kB7000");
    public static final RRC_PollByte KB7500 = new RRC_PollByte("kB7500");
    public static final RRC_PollByte MB8 = new RRC_PollByte("mB8");
    public static final RRC_PollByte MB9 = new RRC_PollByte("mB9");
    public static final RRC_PollByte MB10 = new RRC_PollByte("mB10");
    public static final RRC_PollByte MB11 = new RRC_PollByte("mB11");
    public static final RRC_PollByte MB12 = new RRC_PollByte("mB12");
    public static final RRC_PollByte MB13 = new RRC_PollByte("mB13");
    public static final RRC_PollByte MB14 = new RRC_PollByte("mB14");
    public static final RRC_PollByte MB15 = new RRC_PollByte("mB15");
    public static final RRC_PollByte MB16 = new RRC_PollByte("mB16");
    public static final RRC_PollByte MB17 = new RRC_PollByte("mB17");
    public static final RRC_PollByte MB18 = new RRC_PollByte("mB18");
    public static final RRC_PollByte MB20 = new RRC_PollByte("mB20");
    public static final RRC_PollByte MB25 = new RRC_PollByte("mB25");
    public static final RRC_PollByte MB30 = new RRC_PollByte("mB30");
    public static final RRC_PollByte MB40 = new RRC_PollByte("mB40");
    public static final RRC_PollByte INFINITY = new RRC_PollByte("infinity");
    public static final RRC_PollByte SPARE20 = new RRC_PollByte("spare20");
    public static final RRC_PollByte SPARE19 = new RRC_PollByte("spare19");
    public static final RRC_PollByte SPARE18 = new RRC_PollByte("spare18");
    public static final RRC_PollByte SPARE17 = new RRC_PollByte("spare17");
    public static final RRC_PollByte SPARE16 = new RRC_PollByte("spare16");
    public static final RRC_PollByte SPARE15 = new RRC_PollByte("spare15");
    public static final RRC_PollByte SPARE14 = new RRC_PollByte("spare14");
    public static final RRC_PollByte SPARE13 = new RRC_PollByte("spare13");
    public static final RRC_PollByte SPARE12 = new RRC_PollByte("spare12");
    public static final RRC_PollByte SPARE11 = new RRC_PollByte("spare11");
    public static final RRC_PollByte SPARE10 = new RRC_PollByte("spare10");
    public static final RRC_PollByte SPARE9 = new RRC_PollByte("spare9");
    public static final RRC_PollByte SPARE8 = new RRC_PollByte("spare8");
    public static final RRC_PollByte SPARE7 = new RRC_PollByte("spare7");
    public static final RRC_PollByte SPARE6 = new RRC_PollByte("spare6");
    public static final RRC_PollByte SPARE5 = new RRC_PollByte("spare5");
    public static final RRC_PollByte SPARE4 = new RRC_PollByte("spare4");
    public static final RRC_PollByte SPARE3 = new RRC_PollByte("spare3");
    public static final RRC_PollByte SPARE2 = new RRC_PollByte("spare2");
    public static final RRC_PollByte SPARE1 = new RRC_PollByte("spare1");

    protected RRC_PollByte(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PollByte";
    }

    @Override
    public String getXmlTagName() {
        return "PollByte";
    }

}
