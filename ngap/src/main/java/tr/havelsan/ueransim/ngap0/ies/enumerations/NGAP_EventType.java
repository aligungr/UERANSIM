/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

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
