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

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_CauseMisc extends NGAP_Enumerated {

    public static final NGAP_CauseMisc CONTROL_PROCESSING_OVERLOAD = new NGAP_CauseMisc("control-processing-overload");
    public static final NGAP_CauseMisc NOT_ENOUGH_USER_PLANE_PROCESSING_RESOURCES = new NGAP_CauseMisc("not-enough-user-plane-processing-resources");
    public static final NGAP_CauseMisc HARDWARE_FAILURE = new NGAP_CauseMisc("hardware-failure");
    public static final NGAP_CauseMisc OM_INTERVENTION = new NGAP_CauseMisc("om-intervention");
    public static final NGAP_CauseMisc UNKNOWN_PLMN = new NGAP_CauseMisc("unknown-PLMN");
    public static final NGAP_CauseMisc UNSPECIFIED = new NGAP_CauseMisc("unspecified");

    protected NGAP_CauseMisc(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CauseMisc";
    }

    @Override
    public String getXmlTagName() {
        return "CauseMisc";
    }
}
