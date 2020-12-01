/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_Parameters__supportedBandListEUTRA;

public class RRC_EUTRA_Parameters extends RRC_Sequence {

    public RRC_EUTRA_Parameters__supportedBandListEUTRA supportedBandListEUTRA;
    public RRC_EUTRA_ParametersCommon eutra_ParametersCommon;
    public RRC_EUTRA_ParametersXDD_Diff eutra_ParametersXDD_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "supportedBandListEUTRA","eutra-ParametersCommon","eutra-ParametersXDD-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "supportedBandListEUTRA","eutra_ParametersCommon","eutra_ParametersXDD_Diff" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-Parameters";
    }

}
