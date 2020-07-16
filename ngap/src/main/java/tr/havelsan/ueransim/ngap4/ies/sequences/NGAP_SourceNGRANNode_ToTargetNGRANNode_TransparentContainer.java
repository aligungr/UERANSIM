package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_SourceNGRANNode_ToTargetNGRANNode_TransparentContainer extends NgapSequence {

    public NGAP_RRCContainer rRCContainer;
    public NGAP_PDUSessionResourceInformationList pDUSessionResourceInformationList;
    public NGAP_E_RABInformationList e_RABInformationList;
    public NGAP_NGRAN_CGI targetCell_ID;
    public NGAP_IndexToRFSP indexToRFSP;
    public NGAP_UEHistoryInformation uEHistoryInformation;

    @Override
    protected String getAsnName() {
        return "SourceNGRANNode-ToTargetNGRANNode-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "SourceNGRANNode-ToTargetNGRANNode-TransparentContainer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"rRCContainer", "pDUSessionResourceInformationList", "e-RABInformationList", "targetCell-ID", "indexToRFSP", "uEHistoryInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"rRCContainer", "pDUSessionResourceInformationList", "e_RABInformationList", "targetCell_ID", "indexToRFSP", "uEHistoryInformation"};
    }
}
