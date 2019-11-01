package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class EAPType extends ProtocolEnum {

    public static final EAPType IDENTITY
            = new EAPType(1, "Identity");
    public static final EAPType NOTIFICATION
            = new EAPType(2, "Notification");
    public static final EAPType LEGACY_NAK_RESPONSE_ONLY
            = new EAPType(3, "Legacy Nak (Response Only)");
    public static final EAPType EAP_MD5_CHALLANGE
            = new EAPType(4, "MD5-Challenge EAP (EAP-MD5-CHALLENGE)");
    public static final EAPType EAP_OTP
            = new EAPType(5, "One-Time Password EAP (EAP-OTP)");
    public static final EAPType EAP_GTC
            = new EAPType(6, "Generic Token Card EAP (EAP-GTC)");
    //public static final Type ALLOCATED
    //      = new Type(7, "Allocated");
    //public static final Type ALLOCATED
    //      = new Type(8, "Allocated");
    public static final EAPType EAP_RSA_PKA
            = new EAPType(9, "RSA Public Key Authentication EAP (EAP-RSA-PKA)");
    public static final EAPType EAP_DSS
            = new EAPType(10, "DSS Unilateral EAP (EAP-DSS)");
    public static final EAPType EAP_KEA
            = new EAPType(11, "KEA EAP (EAP-KEA)");
    public static final EAPType EAP_KEA_VALIDATE
            = new EAPType(12, "KEA Validate EAP (EAP-KEA-VALIDATE)");
    public static final EAPType EAP_TLS
            = new EAPType(13, "TLS EAP (EAP-TLS)");
    public static final EAPType EAP_AXENT
            = new EAPType(14, "Defender Token EAP (EAP-AXENT)");
    public static final EAPType EAP_RSA_SECURID
            = new EAPType(15, "RSA Security SecurID EAP (EAP-RSA-SECURID)");
    public static final EAPType EAP_ARCOT_SYSTEMS
            = new EAPType(16, "Arcot Systems EAP (EAP-ARCOT-SYSTEMS)");
    public static final EAPType EAP_LEAP
            = new EAPType(17, "Cisco Wireless EAP / Lightweight EAP (EAP-LEAP)");
    public static final EAPType EAP_SIM
            = new EAPType(18, "GSM Subscriber Identity Modules EAP (EAP-SIM)");
    public static final EAPType EAP_SRP_SHA1_PART1
            = new EAPType(19, "Secure Remote Password SHA1 Part 1 EAP (EAP-SRP-SHA1-PART1)");
    public static final EAPType EAP_SRP_SHA1_PART2
            = new EAPType(20, "Secure Remote Password SHA1 Part 2 EAP (EAP-SRP-SHA1-PART2)");
    public static final EAPType EAP_TTLS
            = new EAPType(21, "Tunneled TLS EAP (EAP-TTLS)");
    public static final EAPType EAP_RAS
            = new EAPType(22, "Remote Access Service EAP (EAP-RAS)");
    public static final EAPType EAP_AKA
            = new EAPType(23, "UMTS Authentication and Key Agreement EAP (EAP-AKA)");
    public static final EAPType EAP_3COM_WIRELESS
            = new EAPType(24, "3Com Wireless EAP (EAP-3COM-WIRELESS)");
    public static final EAPType EAP_PEAP
            = new EAPType(25, "Protected EAP (EAP-PEAP)");
    public static final EAPType EAP_MS_AUTH
            = new EAPType(26, "MS-Authentication EAP (EAP-MS-AUTH)");
    public static final EAPType EAP_MAKE
            = new EAPType(27, "Mutual Authentication w/Key Exchange EAP (EAP-MAKE)");
    public static final EAPType EAP_CRYPTOCARD
            = new EAPType(28, "CRYPTOCard EAP (EAP-CRYPTOCARD)");
    public static final EAPType EAP_MS_CHAP_V2
            = new EAPType(29, "MS-CHAP-v2 EAP (EAP-MS-CHAP-V2)");
    public static final EAPType EAP_DYNAMID
            = new EAPType(30, "DynamID EAP (EAP-DYNAMID)");
    public static final EAPType EAP_ROB
            = new EAPType(31, "Rob EAP (EAP-ROB)");
    public static final EAPType EAP_POTP
            = new EAPType(32, "Protected One-Time Password EAP (EAP-POTP)");
    public static final EAPType EAP_MS_AUTH_TLV
            = new EAPType(33, "MS-Authentication TLV EAP (EAP-MS-AUTH-TLV)");
    public static final EAPType EAP_SENTRINET
            = new EAPType(34, "SentriNET (EAP-SENTRINET)");
    public static final EAPType EAP_ACTIONTEC_WIRELESS
            = new EAPType(35, "Actiontec Wireless EAP (EAP-ACTIONTEC-WIRELESS)");
    public static final EAPType EAP_COGENT_BIOMETRIC
            = new EAPType(36, "Cogent Systems Biometrics Authentication EAP (EAP-COGENT-BIOMETRIC)");
    public static final EAPType EAP_AIRFORTRESS
            = new EAPType(37, "AirFortress EAP (EAP-AIRFORTRESS)");
    public static final EAPType EAP_HTTP_DIGEST
            = new EAPType(38, "HTTP Digest EAP (EAP-HTTP-DIGEST)");
    public static final EAPType EAP_SECURESUITE
            = new EAPType(39, "SecureSuite EAP (EAP-SECURESUITE)");
    public static final EAPType EAP_DEVICECONNECT
            = new EAPType(40, "DeviceConnect EAP (EAP-DEVICECONNECT)");
    public static final EAPType EAP_SPEKE
            = new EAPType(41, "Simple Password Exponential Key Exchange EAP (EAP-SPEKE)");
    public static final EAPType EAP_MOBAC
            = new EAPType(42, "MOBAC EAP (EAP-MOBAC)");
    public static final EAPType EAP_FAST
            = new EAPType(43, "Flexible Authentication via Secure Tunneling EAP (EAP-FAST)");
    public static final EAPType EAP_ZLXEAP
            = new EAPType(44, "ZoneLabs EAP (EAP-ZLXEAP)");
    public static final EAPType EAP_LINK
            = new EAPType(45, "Link EAP (EAP-LINK)");
    public static final EAPType EAP_PAX
            = new EAPType(46, "Password Authenticated eXchange EAP (EAP-PAX)");
    public static final EAPType EAP_PSK
            = new EAPType(47, "Pre-Shared Key EAP (EAP-PSK)");
    public static final EAPType EAP_SAKE
            = new EAPType(48, "Shared-secret Authentication and Key Establishment EAP (EAP-SAKE)");
    public static final EAPType EAP_IKEV2
            = new EAPType(49, "Internet Key Exchange v2 EAP (EAP-IKEv2)");
    public static final EAPType EAP_AKA_PRIME
            = new EAPType(50, "UMTS Authentication and Key Agreement' EAP (EAP-AKA')");
    public static final EAPType EAP_GPSK
            = new EAPType(51, "Generalized Pre-Shared Key EAP (EAP-GPSK)");
    public static final EAPType EAP_PWD
            = new EAPType(52, "Password EAP (EAP-pwd)");
    public static final EAPType EAP_EKEV1
            = new EAPType(53, "Encrypted Key Exchange v1 EAP (EAP-EKEv1)");
    public static final EAPType EXPANDED_EAP_TYPE
            = new EAPType(254, "Expanded Type");
    public static final EAPType EXPERIMENTAL
            = new EAPType(255, "Experimental");

    private EAPType(int value, String name) {
        super(value, name);
    }

    public static EAPType fromValue(int value) {
        return fromValueGeneric(EAPType.class, value);
    }
}
