/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MobilityStateParameters extends AsnSequence {
    public RRC_t_Evaluation t_Evaluation; // mandatory
    public RRC_t_HystNormal t_HystNormal; // mandatory
    public AsnInteger n_CellChangeMedium; // mandatory, VALUE(1..16)
    public AsnInteger n_CellChangeHigh; // mandatory, VALUE(1..16)

    public static class RRC_t_HystNormal extends AsnEnumerated {
        public static final RRC_t_HystNormal S30 = new RRC_t_HystNormal(0);
        public static final RRC_t_HystNormal S60 = new RRC_t_HystNormal(1);
        public static final RRC_t_HystNormal S120 = new RRC_t_HystNormal(2);
        public static final RRC_t_HystNormal S180 = new RRC_t_HystNormal(3);
        public static final RRC_t_HystNormal S240 = new RRC_t_HystNormal(4);
        public static final RRC_t_HystNormal SPARE3 = new RRC_t_HystNormal(5);
        public static final RRC_t_HystNormal SPARE2 = new RRC_t_HystNormal(6);
        public static final RRC_t_HystNormal SPARE1 = new RRC_t_HystNormal(7);
    
        private RRC_t_HystNormal(long value) {
            super(value);
        }
    }

    public static class RRC_t_Evaluation extends AsnEnumerated {
        public static final RRC_t_Evaluation S30 = new RRC_t_Evaluation(0);
        public static final RRC_t_Evaluation S60 = new RRC_t_Evaluation(1);
        public static final RRC_t_Evaluation S120 = new RRC_t_Evaluation(2);
        public static final RRC_t_Evaluation S180 = new RRC_t_Evaluation(3);
        public static final RRC_t_Evaluation S240 = new RRC_t_Evaluation(4);
        public static final RRC_t_Evaluation SPARE3 = new RRC_t_Evaluation(5);
        public static final RRC_t_Evaluation SPARE2 = new RRC_t_Evaluation(6);
        public static final RRC_t_Evaluation SPARE1 = new RRC_t_Evaluation(7);
    
        private RRC_t_Evaluation(long value) {
            super(value);
        }
    }
}

