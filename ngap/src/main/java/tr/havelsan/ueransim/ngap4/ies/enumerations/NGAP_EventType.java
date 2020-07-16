package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_EventType extends NgapEnumerated {

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
    protected String getAsnName() {
        return "EventType";
    }

    @Override
    protected String getXmlTagName() {
        return "EventType";
    }
}
