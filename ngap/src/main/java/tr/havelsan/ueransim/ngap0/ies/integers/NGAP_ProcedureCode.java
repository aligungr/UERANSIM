package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_ProcedureCode extends NGAP_Integer {

    public NGAP_ProcedureCode(long value) {
        super(value);
    }

    public NGAP_ProcedureCode(Octet value) {
        super(value);
    }

    public NGAP_ProcedureCode(Octet2 value) {
        super(value);
    }

    public NGAP_ProcedureCode(Octet3 value) {
        super(value);
    }

    public NGAP_ProcedureCode(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ProcedureCode";
    }

    @Override
    public String getXmlTagName() {
        return "ProcedureCode";
    }
}
