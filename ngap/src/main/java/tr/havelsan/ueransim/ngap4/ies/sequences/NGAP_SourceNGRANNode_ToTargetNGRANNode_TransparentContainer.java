package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_NGRAN_CGI;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_RRCContainer;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_E_RABInformationList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_PDUSessionResourceInformationList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_UEHistoryInformation;

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
