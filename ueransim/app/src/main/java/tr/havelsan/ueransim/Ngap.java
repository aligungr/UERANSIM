package tr.havelsan.ueransim;

import fr.marben.asnsdk.japi.Context;
import fr.marben.asnsdk.japi.Loader;
import fr.marben.asnsdk.japi.spe.Value;
import fr.marben.asnsdk.japi.vi.IAbstractSyntax;
import tr.havelsan.ueransim.app.Constants;
import tr.havelsan.ueransim.core.exceptions.EncodingException;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.ngap.RuntimeConfiguration;
import tr.havelsan.ueransim.ngap.ValueFactory;
import tr.havelsan.ueransim.ngap.ngap_ies.PLMNIdentity;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Ngap {
    private static final boolean TRACE = false;
    private static Context context;

    static {
        try {
            Class.forName("tr.havelsan.ueransim.utils.NgapFix").getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        RuntimeConfiguration.initialize();
    }

    private synchronized static Context getContext() {
        if (context != null)
            return context;

        IAbstractSyntax asn;

        var context = new Context();
        try {
            asn = Loader.load(context, Utils.getResourceStream("asntable.dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        context.setAbstractSyntax(asn);
        context.setValueFactory(new ValueFactory());

        if (TRACE) {
            context.setTraceWriter(new PrintWriter(System.out, true));
            context.setEncodingTraceWhileDecoding(true);
            context.setEncodingTraceWhileEncoding(true);
            context.setValueTraceWhileDecoding(true);
            context.setValueTraceWhileEncoding(true);
            context.setIndentedXerValueTrace(true);
        }

        context.setIndentationShift(2);

        return Ngap.context = context;
    }

    public static byte[] perEncode(Value value) {
        try (var stream = new ByteArrayOutputStream()) {
            value.perEncode(getContext(), stream);
            return stream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> T perDecode(Class<T> type, byte[] data) {
        try {
            var value = type.getConstructor().newInstance();
            value.perDecode(getContext(), new ByteArrayInputStream(data));
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> T perDecode(Class<T> type, String base16) {
        return perDecode(type, Utils.hexStringToByteArray(base16));
    }

    public static String xerEncode(Value value) {
        try (var stream = new ByteArrayOutputStream()) {
            value.xerEncode(getContext(), stream);
            var data = stream.toByteArray();
            var xml = new String(data, StandardCharsets.UTF_8);
            return Utils.indentXml(xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> T xerDecode(Class<T> type, byte[] xml) {
        try {
            var value = type.getConstructor().newInstance();
            value.xerDecode(getContext(), new ByteArrayInputStream(xml));
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> T xerDecode(Class<T> type, String xml) {
        xml = Utils.normalizeXml(xml);
        return xerDecode(type, xml.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * This method is different from NAS/5GS version
     */
    public static PLMNIdentity plmnEncode(VPlmn plmn) {
        int mcc = plmn.mcc.intValue();
        int mcc3 = mcc % 10;
        int mcc2 = (mcc % 100) / 10;
        int mcc1 = (mcc % 1000) / 100;

        if (plmn.mnc == null)
            throw new EncodingException("mnc is null");

        int mnc = plmn.mnc.intValue();
        boolean longMnc = plmn.mnc.isLongMnc();

        if (Constants.ALWAYS_LONG_MNC)
            longMnc = true;

        if (longMnc) {
            int mnc1 = mnc % 1000 / 100;
            int mnc2 = mnc % 100 / 10;
            int mnc3 = mnc % 10;

            int octet1 = mcc2 << 4 | mcc1;
            int octet2 = mnc1 << 4 | mcc3;
            int octet3 = mnc3 << 4 | mnc2;

            return new PLMNIdentity(new Octet3(octet1, octet2, octet3).toByteArray());
        } else {
            int mnc1 = mnc % 100 / 10;
            int mnc2 = mnc % 10;
            int mnc3 = 0xF;

            int octet1 = mcc2 << 4 | mcc1;
            int octet2 = mnc3 << 4 | mcc3;
            int octet3 = mnc2 << 4 | mnc1;

            return new PLMNIdentity(new Octet3(octet1, octet2, octet3).toByteArray());
        }
    }

    /**
     * This method is different from NAS/5GS version
     */
    public static VPlmn plmnDecode(PLMNIdentity plmn) {
        var bytes = plmn.getValue();
        var stream = new OctetInputStream(bytes);

        var res = new VPlmn();

        /* Decode MCC */
        int octet1 = stream.readOctetI();
        int mcc1 = octet1 & 0b1111;
        int mcc2 = (octet1 >> 4) & 0b1111;
        int octet2 = stream.readOctetI();
        int mcc3 = octet2 & 0b1111;
        int mcc = 100 * mcc1 + 10 * mcc2 + mcc3;
        res.mcc = EMccValue.fromValue(mcc);

        /* Decode MNC */
        int mnc3 = (octet2 >> 4) & 0b1111;
        int octet3 = stream.readOctetI();
        int mnc1 = octet3 & 0b1111;
        int mnc2 = (octet3 >> 4) & 0b1111;

        int mnc = 10 * mnc3 + mnc1;
        if ((mnc3 != 0xf) || (octet1 == 0xff && octet2 == 0xff && octet3 == 0xff)) {
            mnc = 10 * mnc + mnc2;
        }
        res.mnc = EMncValue.fromValue(mnc);
        return res;
    }
}
