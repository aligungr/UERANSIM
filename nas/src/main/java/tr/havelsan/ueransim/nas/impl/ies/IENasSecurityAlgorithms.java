/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IENasSecurityAlgorithms extends InformationElement3 {
    public ETypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm;
    public ETypeOfCipheringAlgorithm typeOfCipheringAlgorithm;

    public IENasSecurityAlgorithms() {
    }

    public IENasSecurityAlgorithms(ETypeOfIntegrityProtectionAlgorithm typeOfIntegrityProtectionAlgorithm, ETypeOfCipheringAlgorithm typeOfCipheringAlgorithm) {
        this.typeOfIntegrityProtectionAlgorithm = typeOfIntegrityProtectionAlgorithm;
        this.typeOfCipheringAlgorithm = typeOfCipheringAlgorithm;
    }

    @Override
    protected IENasSecurityAlgorithms decodeIE3(OctetInputStream stream) {
        var res = new IENasSecurityAlgorithms();
        res.typeOfIntegrityProtectionAlgorithm = ETypeOfIntegrityProtectionAlgorithm.fromValue(stream.peekOctetI() & 0xF);
        res.typeOfCipheringAlgorithm = ETypeOfCipheringAlgorithm.fromValue(stream.readOctetI() >> 4 & 0xF);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(typeOfCipheringAlgorithm.intValue(), typeOfIntegrityProtectionAlgorithm.intValue());
    }
}
