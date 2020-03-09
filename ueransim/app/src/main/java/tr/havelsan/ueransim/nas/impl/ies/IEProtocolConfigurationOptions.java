package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEProtocolConfigurationOptions extends InformationElement4 {
    public EConfigurationProtocol configurationProtocol;
    public OctetString rawData;

    public IEProtocolConfigurationOptions() {
    }

    public IEProtocolConfigurationOptions(EConfigurationProtocol configurationProtocol, OctetString rawData) {
        this.configurationProtocol = configurationProtocol;
        this.rawData = rawData;
    }

    @Override
    protected IEProtocolConfigurationOptions decodeIE4(OctetInputStream stream, int length) {
        var res = new IEProtocolConfigurationOptions();
        res.configurationProtocol = EConfigurationProtocol.fromValue(stream.readOctetI() & 0b111);
        res.rawData = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(configurationProtocol.intValue());
        stream.writeOctetString(rawData);
    }

    public static class EConfigurationProtocol extends ProtocolEnum {
        public static final EConfigurationProtocol PPP
                = new EConfigurationProtocol(0b000, "PPP for use with IP PDP type or IP PDN type");

        private EConfigurationProtocol(int value, String name) {
            super(value, name);
        }

        public static EConfigurationProtocol fromValue(int value) {
            // All values are accepted as PPP for current version of the protocol.
            return PPP;
        }
    }
}
