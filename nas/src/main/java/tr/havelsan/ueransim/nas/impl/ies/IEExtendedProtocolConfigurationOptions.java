/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.impl.enums.EConfigurationProtocol;
import tr.havelsan.ueransim.nas.impl.others.ProtocolConfigurationOptions;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEExtendedProtocolConfigurationOptions extends InformationElement6 {
    public EConfigurationProtocol configurationProtocol;
    public boolean extension;
    public OctetString options;

    public IEExtendedProtocolConfigurationOptions() {
    }

    @Override
    protected IEExtendedProtocolConfigurationOptions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEExtendedProtocolConfigurationOptions();

        var octet = stream.readOctet();
        res.configurationProtocol = EConfigurationProtocol.fromValue(octet.getBitRangeI(0, 2));
        res.extension = octet.getBitB(7);
        res.options = stream.readOctetString(length - 1);

        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(configurationProtocol.intValue() | ((extension ? 1 : 0) << 7));
        stream.writeOctetString(options);
    }

    public ProtocolConfigurationOptions getAsOptions(boolean isUplink) {
        return ProtocolConfigurationOptions.decode(options, isUplink);
    }
}
