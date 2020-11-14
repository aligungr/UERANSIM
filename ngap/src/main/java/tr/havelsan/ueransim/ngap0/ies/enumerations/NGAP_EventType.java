/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_EventType extends NGAP_Enumerated {

    public static final NGAP_EventType DIRECT = new NGAP_EventType("direct");
    public static final NGAP_EventType CHANGE_OF_SERVE_CELL = new NGAP_EventType("change-of-serve-cell");
    public static final NGAP_EventType UE_PRESENCE_IN_AREA_OF_INTEREST = new NGAP_EventType("ue-presence-in-area-of-interest");
    public static final NGAP_EventType STOP_CHANGE_OF_SERVE_CELL = new NGAP_EventType("stop-change-of-serve-cell");
    public static final NGAP_EventType STOP_UE_PRESENCE_IN_AREA_OF_INTEREST = new NGAP_EventType("stop-ue-presence-in-area-of-interest");
    public static final NGAP_EventType CANCEL_LOCATION_REPORTING_FOR_THE_UE = new NGAP_EventType("cancel-location-reporting-for-the-ue");

    protected NGAP_EventType(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "EventType";
    }

    @Override
    public String getXmlTagName() {
        return "EventType";
    }
}
