/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_CSI_ReportConfig__reportFreqConfiguration__csi_ReportingBand extends RRC_Choice {

    public RRC_BitString subbands3;
    public RRC_BitString subbands4;
    public RRC_BitString subbands5;
    public RRC_BitString subbands6;
    public RRC_BitString subbands7;
    public RRC_BitString subbands8;
    public RRC_BitString subbands9;
    public RRC_BitString subbands10;
    public RRC_BitString subbands11;
    public RRC_BitString subbands12;
    public RRC_BitString subbands13;
    public RRC_BitString subbands14;
    public RRC_BitString subbands15;
    public RRC_BitString subbands16;
    public RRC_BitString subbands17;
    public RRC_BitString subbands18;
    public RRC_BitString subbands19_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "subbands3","subbands4","subbands5","subbands6","subbands7","subbands8","subbands9","subbands10","subbands11","subbands12","subbands13","subbands14","subbands15","subbands16","subbands17","subbands18","subbands19-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "subbands3","subbands4","subbands5","subbands6","subbands7","subbands8","subbands9","subbands10","subbands11","subbands12","subbands13","subbands14","subbands15","subbands16","subbands17","subbands18","subbands19_v1530" };
    }

}
