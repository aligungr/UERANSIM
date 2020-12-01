/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_DRX_Config__drx_LongCycleStartOffset;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_DRX_Config__drx_onDurationTimer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DRX_Config extends RRC_Sequence {

    public RRC_DRX_Config__drx_onDurationTimer drx_onDurationTimer;
    public RRC_Integer drx_InactivityTimer;
    public RRC_Integer drx_HARQ_RTT_TimerDL;
    public RRC_Integer drx_HARQ_RTT_TimerUL;
    public RRC_Integer drx_RetransmissionTimerDL;
    public RRC_Integer drx_RetransmissionTimerUL;
    public RRC_DRX_Config__drx_LongCycleStartOffset drx_LongCycleStartOffset;
    public RRC_DRX_Config__shortDRX shortDRX;
    public RRC_Integer drx_SlotOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "drx-onDurationTimer","drx-InactivityTimer","drx-HARQ-RTT-TimerDL","drx-HARQ-RTT-TimerUL","drx-RetransmissionTimerDL","drx-RetransmissionTimerUL","drx-LongCycleStartOffset","shortDRX","drx-SlotOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "drx_onDurationTimer","drx_InactivityTimer","drx_HARQ_RTT_TimerDL","drx_HARQ_RTT_TimerUL","drx_RetransmissionTimerDL","drx_RetransmissionTimerUL","drx_LongCycleStartOffset","shortDRX","drx_SlotOffset" };
    }

    @Override
    public String getAsnName() {
        return "DRX-Config";
    }

    @Override
    public String getXmlTagName() {
        return "DRX-Config";
    }

}
