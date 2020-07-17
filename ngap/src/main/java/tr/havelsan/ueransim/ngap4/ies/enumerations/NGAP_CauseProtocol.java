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

public class NGAP_CauseProtocol extends NgapEnumerated {

    public static final NGAP_CauseProtocol TRANSFER_SYNTAX_ERROR = new NGAP_CauseProtocol("transfer-syntax-error");
    public static final NGAP_CauseProtocol ABSTRACT_SYNTAX_ERROR_REJECT = new NGAP_CauseProtocol("abstract-syntax-error-reject");
    public static final NGAP_CauseProtocol ABSTRACT_SYNTAX_ERROR_IGNORE_AND_NOTIFY = new NGAP_CauseProtocol("abstract-syntax-error-ignore-and-notify");
    public static final NGAP_CauseProtocol MESSAGE_NOT_COMPATIBLE_WITH_RECEIVER_STATE = new NGAP_CauseProtocol("message-not-compatible-with-receiver-state");
    public static final NGAP_CauseProtocol SEMANTIC_ERROR = new NGAP_CauseProtocol("semantic-error");
    public static final NGAP_CauseProtocol ABSTRACT_SYNTAX_ERROR_FALSELY_CONSTRUCTED_MESSAGE = new NGAP_CauseProtocol("abstract-syntax-error-falsely-constructed-message");
    public static final NGAP_CauseProtocol UNSPECIFIED = new NGAP_CauseProtocol("unspecified");

    public NGAP_CauseProtocol(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseProtocol";
    }

    @Override
    public String getXmlTagName() {
        return "CauseProtocol";
    }
}
