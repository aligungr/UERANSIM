/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ProtocolValue;
import tr.havelsan.ueransim.utils.octets.Octet;

public class Eap extends ProtocolValue {
    public final ECode code;
    public final Octet id;
    public final EEapType EAPType;

    public Eap(ECode code, Octet id, EEapType EAPType) {
        this.code = code;
        this.id = id;
        this.EAPType = EAPType;
    }

    public static class ECode extends ProtocolEnum {
        public static final ECode REQUEST
                = new ECode(1, "Request");
        public static final ECode RESPONSE
                = new ECode(2, "Response");
        public static final ECode SUCCESS
                = new ECode(3, "Success");
        public static final ECode FAILURE
                = new ECode(4, "Failure");
        public static final ECode INITIATE
                = new ECode(5, "Initiate");
        public static final ECode FINISH
                = new ECode(6, "Finish");

        private ECode(int value, String name) {
            super(value, name);
        }

        public static ECode fromValue(int value) {
            return fromValueGeneric(ECode.class, value, null);
        }
    }

    public static class EEapType extends ProtocolEnum {
        public static final EEapType IDENTITY
                = new EEapType(1, "Identity");
        public static final EEapType NOTIFICATION
                = new EEapType(2, "Notification");
        public static final EEapType LEGACY_NAK_RESPONSE_ONLY
                = new EEapType(3, "Legacy Nak (Response Only)");
        public static final EEapType EAP_MD5_CHALLANGE
                = new EEapType(4, "MD5-Challenge EAP (EAP-MD5-CHALLENGE)");
        public static final EEapType EAP_OTP
                = new EEapType(5, "One-Time Password EAP (EAP-OTP)");
        public static final EEapType EAP_GTC
                = new EEapType(6, "Generic Token Card EAP (EAP-GTC)");
        public static final EEapType ALLOCATED1
                = new EEapType(7, "Allocated");
        public static final EEapType ALLOCATED2
                = new EEapType(8, "Allocated");
        public static final EEapType EAP_RSA_PKA
                = new EEapType(9, "RSA Public Key Authentication EAP (EAP-RSA-PKA)");
        public static final EEapType EAP_DSS
                = new EEapType(10, "DSS Unilateral EAP (EAP-DSS)");
        public static final EEapType EAP_KEA
                = new EEapType(11, "KEA EAP (EAP-KEA)");
        public static final EEapType EAP_KEA_VALIDATE
                = new EEapType(12, "KEA Validate EAP (EAP-KEA-VALIDATE)");
        public static final EEapType EAP_TLS
                = new EEapType(13, "TLS EAP (EAP-TLS)");
        public static final EEapType EAP_AXENT
                = new EEapType(14, "Defender Token EAP (EAP-AXENT)");
        public static final EEapType EAP_RSA_SECURID
                = new EEapType(15, "RSA Security SecurID EAP (EAP-RSA-SECURID)");
        public static final EEapType EAP_ARCOT_SYSTEMS
                = new EEapType(16, "Arcot Systems EAP (EAP-ARCOT-SYSTEMS)");
        public static final EEapType EAP_LEAP
                = new EEapType(17, "Cisco Wireless EAP / Lightweight EAP (EAP-LEAP)");
        public static final EEapType EAP_SIM
                = new EEapType(18, "GSM Subscriber Identity Modules EAP (EAP-SIM)");
        public static final EEapType EAP_SRP_SHA1_PART1
                = new EEapType(19, "Secure Remote Password SHA1 Part 1 EAP (EAP-SRP-SHA1-PART1)");
        public static final EEapType EAP_SRP_SHA1_PART2
                = new EEapType(20, "Secure Remote Password SHA1 Part 2 EAP (EAP-SRP-SHA1-PART2)");
        public static final EEapType EAP_TTLS
                = new EEapType(21, "Tunneled TLS EAP (EAP-TTLS)");
        public static final EEapType EAP_RAS
                = new EEapType(22, "Remote Access Service EAP (EAP-RAS)");
        public static final EEapType EAP_AKA
                = new EEapType(23, "UMTS Authentication and Key Agreement EAP (EAP-AKA)");
        public static final EEapType EAP_3COM_WIRELESS
                = new EEapType(24, "3Com Wireless EAP (EAP-3COM-WIRELESS)");
        public static final EEapType EAP_PEAP
                = new EEapType(25, "Protected EAP (EAP-PEAP)");
        public static final EEapType EAP_MS_AUTH
                = new EEapType(26, "MS-Authentication EAP (EAP-MS-AUTH)");
        public static final EEapType EAP_MAKE
                = new EEapType(27, "Mutual Authentication w/Key Exchange EAP (EAP-MAKE)");
        public static final EEapType EAP_CRYPTOCARD
                = new EEapType(28, "CRYPTOCard EAP (EAP-CRYPTOCARD)");
        public static final EEapType EAP_MS_CHAP_V2
                = new EEapType(29, "MS-CHAP-v2 EAP (EAP-MS-CHAP-V2)");
        public static final EEapType EAP_DYNAMID
                = new EEapType(30, "DynamID EAP (EAP-DYNAMID)");
        public static final EEapType EAP_ROB
                = new EEapType(31, "Rob EAP (EAP-ROB)");
        public static final EEapType EAP_POTP
                = new EEapType(32, "Protected One-Time Password EAP (EAP-POTP)");
        public static final EEapType EAP_MS_AUTH_TLV
                = new EEapType(33, "MS-Authentication TLV EAP (EAP-MS-AUTH-TLV)");
        public static final EEapType EAP_SENTRINET
                = new EEapType(34, "SentriNET (EAP-SENTRINET)");
        public static final EEapType EAP_ACTIONTEC_WIRELESS
                = new EEapType(35, "Actiontec Wireless EAP (EAP-ACTIONTEC-WIRELESS)");
        public static final EEapType EAP_COGENT_BIOMETRIC
                = new EEapType(36, "Cogent Systems Biometrics Authentication EAP (EAP-COGENT-BIOMETRIC)");
        public static final EEapType EAP_AIRFORTRESS
                = new EEapType(37, "AirFortress EAP (EAP-AIRFORTRESS)");
        public static final EEapType EAP_HTTP_DIGEST
                = new EEapType(38, "HTTP Digest EAP (EAP-HTTP-DIGEST)");
        public static final EEapType EAP_SECURESUITE
                = new EEapType(39, "SecureSuite EAP (EAP-SECURESUITE)");
        public static final EEapType EAP_DEVICECONNECT
                = new EEapType(40, "DeviceConnect EAP (EAP-DEVICECONNECT)");
        public static final EEapType EAP_SPEKE
                = new EEapType(41, "Simple Password Exponential Key Exchange EAP (EAP-SPEKE)");
        public static final EEapType EAP_MOBAC
                = new EEapType(42, "MOBAC EAP (EAP-MOBAC)");
        public static final EEapType EAP_FAST
                = new EEapType(43, "Flexible Authentication via Secure Tunneling EAP (EAP-FAST)");
        public static final EEapType EAP_ZLXEAP
                = new EEapType(44, "ZoneLabs EAP (EAP-ZLXEAP)");
        public static final EEapType EAP_LINK
                = new EEapType(45, "Link EAP (EAP-LINK)");
        public static final EEapType EAP_PAX
                = new EEapType(46, "Password Authenticated eXchange EAP (EAP-PAX)");
        public static final EEapType EAP_PSK
                = new EEapType(47, "Pre-Shared Key EAP (EAP-PSK)");
        public static final EEapType EAP_SAKE
                = new EEapType(48, "Shared-secret Authentication and Key Establishment EAP (EAP-SAKE)");
        public static final EEapType EAP_IKEV2
                = new EEapType(49, "Internet Key Exchange v2 EAP (EAP-IKEv2)");
        public static final EEapType EAP_AKA_PRIME
                = new EEapType(50, "UMTS Authentication and Key Agreement' EAP (EAP-AKA')");
        public static final EEapType EAP_GPSK
                = new EEapType(51, "Generalized Pre-Shared Key EAP (EAP-GPSK)");
        public static final EEapType EAP_PWD
                = new EEapType(52, "Password EAP (EAP-pwd)");
        public static final EEapType EAP_EKEV1
                = new EEapType(53, "Encrypted Key Exchange v1 EAP (EAP-EKEv1)");
        public static final EEapType EXPANDED_EAP_TYPE
                = new EEapType(254, "Expanded Type");
        public static final EEapType EXPERIMENTAL
                = new EEapType(255, "Experimental");

        private EEapType(int value, String name) {
            super(value, name);
        }

        public static EEapType fromValue(int value) {
            return fromValueGeneric(EEapType.class, value, null);
        }
    }
}
