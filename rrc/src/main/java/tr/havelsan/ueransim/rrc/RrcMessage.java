/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RrcMessage {

    // RRC messages that may be sent from the network to the UE via BCH on the BCCH logical channel
    public RRC_BCCH_BCH_Message bcch_bch;

    // RRC messages that may be sent from the network to the UE via DL-SCH on the BCCH logical channel.
    public RRC_BCCH_DL_SCH_Message bcch_dl_sch;

    // RRC messages that may be sent from the Network to the UE on the downlink CCCH logical channel.
    public RRC_DL_CCCH_Message dl_ccch;

    // RRC messages that may be sent from the network to the UE on the downlink DCCH logical channel.
    public RRC_DL_DCCH_Message dl_dcch;

    // RRC messages that may be sent from the Network to the UE on the PCCH logical channel.
    public RRC_PCCH_Message pcch;

    // 48bit RRC messages that may be sent from the UE to the Network on the uplink CCCH logical channel.
    public RRC_UL_CCCH_Message ccch;

    // 64bit RRC messages that may be sent from the UE to the Network on the uplink CCCH1 logical channel.
    public RRC_UL_CCCH1_Message ccch1;

    // RRC messages that may be sent from the UE to the network on the uplink DCCH logical channel.
    public RRC_UL_DCCH_Message ul_dcch;


    public RrcMessage() {
    }

    public RrcMessage(RRC_BCCH_BCH_Message bcch_bch) {
        this.bcch_bch = bcch_bch;
    }

    public RrcMessage(RRC_BCCH_DL_SCH_Message bcch_dl_sch) {
        this.bcch_dl_sch = bcch_dl_sch;
    }

    public RrcMessage(RRC_DL_CCCH_Message dl_ccch) {
        this.dl_ccch = dl_ccch;
    }

    public RrcMessage(RRC_DL_DCCH_Message dl_dcch) {
        this.dl_dcch = dl_dcch;
    }

    public RrcMessage(RRC_PCCH_Message pcch) {
        this.pcch = pcch;
    }

    public RrcMessage(RRC_UL_CCCH_Message ccch) {
        this.ccch = ccch;
    }

    public RrcMessage(RRC_UL_CCCH1_Message ccch1) {
        this.ccch1 = ccch1;
    }

    public RrcMessage(RRC_UL_DCCH_Message ul_dcch) {
        this.ul_dcch = ul_dcch;
    }
}
