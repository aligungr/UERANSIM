package com.runsim.backend;

import com.runsim.backend.ngap.RuntimeConfiguration;
import com.runsim.backend.ngap.ValueFactory;
import com.runsim.backend.utils.Utils;
import fr.marben.asnsdk.japi.Context;
import fr.marben.asnsdk.japi.Loader;
import fr.marben.asnsdk.japi.spe.Value;
import fr.marben.asnsdk.japi.vi.IAbstractSyntax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class NGAP {
    private static final boolean TRACE = false;
    private static Context context;

    static {
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

        return NGAP.context = context;
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
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
