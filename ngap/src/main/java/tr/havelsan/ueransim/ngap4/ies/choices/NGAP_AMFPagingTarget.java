package tr.havelsan.ueransim.ngap4.ies.choices;

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

public class NGAP_AMFPagingTarget extends NgapChoice {

    public NGAP_GlobalRANNodeID globalRANNodeID;
    public NGAP_TAI tAI;

    @Override
    protected String getAsnName() {
        return "AMFPagingTarget";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFPagingTarget";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalRANNodeID", "tAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID", "tAI"};
    }
}
