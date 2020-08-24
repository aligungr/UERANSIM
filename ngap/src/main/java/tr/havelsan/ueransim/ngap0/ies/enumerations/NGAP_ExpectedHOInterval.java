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

public class NGAP_ExpectedHOInterval extends NGAP_Enumerated {

    public static final NGAP_ExpectedHOInterval SEC15 = new NGAP_ExpectedHOInterval("sec15");
    public static final NGAP_ExpectedHOInterval SEC30 = new NGAP_ExpectedHOInterval("sec30");
    public static final NGAP_ExpectedHOInterval SEC60 = new NGAP_ExpectedHOInterval("sec60");
    public static final NGAP_ExpectedHOInterval SEC90 = new NGAP_ExpectedHOInterval("sec90");
    public static final NGAP_ExpectedHOInterval SEC120 = new NGAP_ExpectedHOInterval("sec120");
    public static final NGAP_ExpectedHOInterval SEC180 = new NGAP_ExpectedHOInterval("sec180");
    public static final NGAP_ExpectedHOInterval LONG_TIME = new NGAP_ExpectedHOInterval("long-time");

    protected NGAP_ExpectedHOInterval(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ExpectedHOInterval";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedHOInterval";
    }
}
