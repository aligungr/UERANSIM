package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

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

public class NGAP_NR_CGIList extends NGAP_SequenceOf<NGAP_NR_CGI> {

    public NGAP_NR_CGIList() {
        super();
    }

    public NGAP_NR_CGIList(List<NGAP_NR_CGI> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "NR-CGIList";
    }

    @Override
    public String getXmlTagName() {
        return "NR-CGIList";
    }

    @Override
    public Class<NGAP_NR_CGI> getItemType() {
        return NGAP_NR_CGI.class;
    }
}
