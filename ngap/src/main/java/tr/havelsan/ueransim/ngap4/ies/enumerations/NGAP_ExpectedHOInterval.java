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

public class NGAP_ExpectedHOInterval extends NgapEnumerated {

    public static final NGAP_ExpectedHOInterval SEC15 = new NGAP_ExpectedHOInterval("sec15");
    public static final NGAP_ExpectedHOInterval SEC30 = new NGAP_ExpectedHOInterval("sec30");
    public static final NGAP_ExpectedHOInterval SEC60 = new NGAP_ExpectedHOInterval("sec60");
    public static final NGAP_ExpectedHOInterval SEC90 = new NGAP_ExpectedHOInterval("sec90");
    public static final NGAP_ExpectedHOInterval SEC120 = new NGAP_ExpectedHOInterval("sec120");
    public static final NGAP_ExpectedHOInterval SEC180 = new NGAP_ExpectedHOInterval("sec180");
    public static final NGAP_ExpectedHOInterval LONG_TIME = new NGAP_ExpectedHOInterval("long-time");

    protected NGAP_ExpectedHOInterval(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ExpectedHOInterval";
    }

    @Override
    protected String getXmlTagName() {
        return "ExpectedHOInterval";
    }
}
