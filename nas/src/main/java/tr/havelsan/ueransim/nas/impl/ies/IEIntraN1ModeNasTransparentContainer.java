/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.enums.EKeyAmfChangeFlag;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class IEIntraN1ModeNasTransparentContainer extends InformationElement4 {
    public Octet4 mac;
    public ETypeOfCipheringAlgorithm cipheringAlg;
    public ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg;
    public Bit3 keySetIdentifierIn5g;
    public ETypeOfSecurityContext tsc;
    public EKeyAmfChangeFlag kacf;
    public Octet sequenceNumber;

    public IEIntraN1ModeNasTransparentContainer() {
    }

    public IEIntraN1ModeNasTransparentContainer(Octet4 mac, ETypeOfCipheringAlgorithm cipheringAlg, ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg, Bit3 keySetIdentifierIn5g, ETypeOfSecurityContext tsc, EKeyAmfChangeFlag kacf, Octet sequenceNumber) {
        this.mac = mac;
        this.cipheringAlg = cipheringAlg;
        this.integrityProtectionAlg = integrityProtectionAlg;
        this.keySetIdentifierIn5g = keySetIdentifierIn5g;
        this.tsc = tsc;
        this.kacf = kacf;
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    protected IEIntraN1ModeNasTransparentContainer decodeIE4(OctetInputStream stream, int length) {
        var res = new IEIntraN1ModeNasTransparentContainer();
        res.mac = stream.readOctet4();

        var octet = stream.readOctet();

        res.integrityProtectionAlg = ETypeOfIntegrityProtectionAlgorithm.fromValue(octet.getBitRangeI(0, 3));
        res.cipheringAlg = ETypeOfCipheringAlgorithm.fromValue(octet.getBitRangeI(4, 7));

        octet = stream.readOctet();

        res.keySetIdentifierIn5g = new Bit3(octet.getBitRangeI(0, 2));
        res.tsc = ETypeOfSecurityContext.fromValue(octet.getBitI(3));
        res.kacf = EKeyAmfChangeFlag.fromValue(octet.getBitI(4));

        res.sequenceNumber = stream.readOctet();
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet4(mac);
        stream.writeOctet((cipheringAlg.intValue() << 4) | integrityProtectionAlg.intValue());
        stream.writeOctet(new Octet().setBitRange(0, 2, keySetIdentifierIn5g.intValue())
                .setBit(3, tsc.intValue()).setBit(4, kacf.intValue()));
        stream.writeOctet(sequenceNumber);
    }
}
