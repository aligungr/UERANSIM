/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.contexts.GnbAmfContext;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.console.Logger;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AppConfig {

    public final MtsContext mts;
    private final String profile;
    private final UeRanSim ueRanSim;

    public AppConfig(MtsContext mts, UeRanSim ueRanSim) {
        this.mts = mts;
        this.ueRanSim = ueRanSim;

        var root = (ImplicitTypedObject) mts.decoder.decode("config/profile.yaml");
        var profile = root.getString("selected-profile");
        this.profile = "config/" + profile + "/";
        Console.println(AnsiPalette.PAINT_IMPORTANT_INFO, "INFO: Selected profile: \"%s\"", profile);

        var general = (ImplicitTypedObject) mts.decoder.decode(this.profile + "general.yaml");
        Constants.USE_LONG_MNC = general.getBool("use-long-mnc");
        Constants.TREAT_ERRORS_AS_FATAL = general.getBool("treat-errors-as-fatal");
    }

    public static Logger createLoggerFor(String name) {
        var logger = new Logger(name);
        loggingToFile(logger, name, false);
        return logger;
    }

    public static void loggingToFile(Logger logger, String name, boolean standardPrint) {
        if (name.contains("."))
            throw new IllegalArgumentException("name contains '.'");

        logger.getConsole().setStandardPrintEnabled(standardPrint);
        logger.getConsole().addPrintHandler(str -> {
            final Path path = Paths.get("logs/" + name + ".log");
            try {
                Files.write(path, str.getBytes(StandardCharsets.UTF_8),
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static String generateNodeName(BaseSimContext ctx) {
        if (ctx instanceof UeSimContext)
            return "ue-" + ((UeSimContext) ctx).ueConfig.supi.toString();
        if (ctx instanceof GnbSimContext)
            return "gnb-" + ((GnbSimContext) ctx).config.gnbId;
        throw new RuntimeException();
    }

    public GnbSimContext createGnbSimContext(SimulationContext simCtx, ImplicitTypedObject config) {
        return createGnbSimContext(simCtx, mts.constructor.construct(GnbConfig.class, config, true));
    }

    public GnbSimContext createGnbSimContext(SimulationContext simCtx, GnbConfig config) {
        var ctx = new GnbSimContext(ueRanSim);
        ctx.config = config;

        // Create AMF gNB contexts
        {
            for (var amfConfig : ctx.config.amfConfigs) {
                if (amfConfig.guami == null) {
                    throw new RuntimeException("amfConfig.guami == null");
                }

                var amfGnbCtx = new GnbAmfContext(amfConfig.guami);
                amfGnbCtx.host = amfConfig.host;
                amfGnbCtx.port = amfConfig.port;

                ctx.amfContexts.put(amfGnbCtx.guami, amfGnbCtx);
            }
        }

        return ctx;
    }

    public UeSimContext createUeSimContext(SimulationContext simCtx, ImplicitTypedObject config) {
        return createUeSimContext(simCtx, config.asConstructed(mts, UeConfig.class));
    }

    public UeSimContext createUeSimContext(SimulationContext simCtx, UeConfig config) {
        var ctx = new UeSimContext(ueRanSim);
        ctx.ueConfig = config;
        return ctx;
    }

    public GnbConfig createGnbConfig() {
        return mts.constructor.construct(GnbConfig.class, ((ImplicitTypedObject) mts.decoder.decode(profile + "gnb.yaml")), true);
    }

    public UeConfig createUeConfig() {
        return mts.constructor.construct(UeConfig.class, ((ImplicitTypedObject) mts.decoder.decode(profile + "ue.yaml")), true);
    }
}
