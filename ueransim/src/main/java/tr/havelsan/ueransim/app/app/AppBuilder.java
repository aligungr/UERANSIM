/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.app.listeners.INodeMessagingListener;
import tr.havelsan.ueransim.app.app.listeners.LoadTestMessagingListener;
import tr.havelsan.ueransim.app.common.configs.LoadTestConfig;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.console.BaseConsole;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppBuilder {

    private static final AtomicBoolean isBuilt = new AtomicBoolean(false);
    private final List<INodeMessagingListener> messagingListeners;

    public AppBuilder() {
        this.messagingListeners = new ArrayList<>();
    }

    //======================================================================================================
    //                                          MUTATORS
    //======================================================================================================

    public AppBuilder addMessagingListener(INodeMessagingListener listener) {
        this.messagingListeners.add(listener);
        return this;
    }

    //======================================================================================================
    //                                          BUILDER
    //======================================================================================================

    public UeRanSim build() {
        if (isBuilt.getAndSet(true))
            throw new RuntimeException("already built");

        new File("logs").mkdir();

        ConfigUtils.loggingToFile(Logger.GLOBAL, Logger.GLOBAL.getLoggerName(), true);
        Log.registerLogger(Thread.currentThread(), Logger.GLOBAL);

        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All global logs are written to: logs/%s.log", Logger.GLOBAL.getLoggerName());
        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All logs of UEs and gNBs are written to their own log files: logs/*");
        Console.println(AnsiPalette.PAINT_IMPORTANT_WARNING, "WARNING: All load testing logs are written to: logs/loadtest.log");

        var loadTestConsole = createLoadTestingConsole();
        var testCases = createTestCases();
        var loadTesting = createLoadTestingConfig();

        this.messagingListeners.add(new LoadTestMessagingListener(loadTestConsole));

        return new UeRanSim(messagingListeners, testCases, loadTesting);
    }

    private LoadTestConfig createLoadTestingConfig() {
        var testingMts = new MtsContext();
        MtsInitializer.initTestingMts(testingMts);
        testingMts.setTypeKeyword("@cmd");
        var testing = (ImplicitTypedObject) testingMts.decoder.decode("config/testing.yaml");

        return new LoadTestConfig(((ImplicitTypedObject) testing.get("load-testing")).getInt("number-of-UE"));
    }

    private LinkedHashMap<String, List<TestCmd>> createTestCases() {
        var testingMts = new MtsContext();
        MtsInitializer.initTestingMts(testingMts);
        testingMts.setTypeKeyword("@cmd");
        var testing = (ImplicitTypedObject) testingMts.decoder.decode("config/testing.yaml");

        var res = new LinkedHashMap<String, List<TestCmd>>();

        for (var entry : ((ImplicitTypedObject) testing.getParameters().get("test-cases")).getParameters().entrySet()) {
            String key = entry.getKey();
            Object[] value = (Object[]) entry.getValue();

            var list = new ArrayList<TestCmd>();
            for (Object o : value) list.add((TestCmd) o);

            res.put(key, list);
        }

        return res;
    }

    private BaseConsole createLoadTestingConsole() {
        var loadTestConsole = new BaseConsole();
        loadTestConsole.setStandardPrintEnabled(false);
        loadTestConsole.addPrintHandler(str -> {
            final Path path = Paths.get("logs/loadtest.log");
            try {
                Files.write(path, str.getBytes(StandardCharsets.UTF_8),
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        loadTestConsole.printDiv();
        return loadTestConsole;
    }
}
