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
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

public class AppConfig {

    public final MtsContext mts;
    private final String profile;

    public AppConfig() {
        this.mts = new MtsContext();
        MtsInitializer.initDefaultMts(this.mts);

        var root = (ImplicitTypedObject) mts.decoder.decode("config/profile.yaml");
        var profile = root.getString("selected-profile");
        this.profile = "config/" + profile + "/";
        Console.println(AnsiPalette.PAINT_IMPORTANT_INFO, "INFO: Selected profile: \"%s\"", profile);

        var general = (ImplicitTypedObject) mts.decoder.decode(this.profile + "general.yaml");
        Constants.USE_LONG_MNC = general.getBool("use-long-mnc");
        Constants.TREAT_ERRORS_AS_FATAL = general.getBool("treat-errors-as-fatal");
    }

    public GnbConfig createGnbConfig() {
        return mts.constructor.construct(GnbConfig.class, ((ImplicitTypedObject) mts.decoder.decode(profile + "gnb.yaml")), true);
    }

    public UeConfig createUeConfig() {
        return mts.constructor.construct(UeConfig.class, ((ImplicitTypedObject) mts.decoder.decode(profile + "ue.yaml")), true);
    }
}
