package com.runsim.backend;

import com.runsim.backend.ngap.RuntimeConfiguration;
import com.runsim.backend.ngap.ValueFactory;
import com.runsim.backend.utils.Utils;
import fr.marben.asnsdk.japi.Context;
import fr.marben.asnsdk.japi.Loader;
import fr.marben.asnsdk.japi.vi.IAbstractSyntax;

import java.io.IOException;
import java.io.PrintWriter;

public class NGAP {
    private static Context context;

    static {
        RuntimeConfiguration.initialize();
    }

    public synchronized static Context getContext() {
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

        context.setTraceWriter(new PrintWriter(System.out, true));
        context.setEncodingTraceWhileDecoding(true);
        context.setEncodingTraceWhileEncoding(true);
        context.setValueTraceWhileDecoding(true);
        context.setValueTraceWhileEncoding(true);
        context.setIndentedXerValueTrace(true);

        return NGAP.context = context;
    }
}
