package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEExtendedProtocolConfigurationOptions extends InformationElement6 {
    public IEProtocolConfigurationOptions protocolConfigurationOptions;

    public IEExtendedProtocolConfigurationOptions() {
    }

    public IEExtendedProtocolConfigurationOptions(IEProtocolConfigurationOptions protocolConfigurationOptions) {
        this.protocolConfigurationOptions = protocolConfigurationOptions;
    }

    @Override
    protected IEExtendedProtocolConfigurationOptions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEExtendedProtocolConfigurationOptions();
        res.protocolConfigurationOptions = NasDecoder.ie2346(stream, IEProtocolConfigurationOptions.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        NasEncoder.ie2346(stream, protocolConfigurationOptions);
    }
}
