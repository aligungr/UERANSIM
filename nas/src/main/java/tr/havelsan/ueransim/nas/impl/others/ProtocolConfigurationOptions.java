/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.others;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;
import java.util.List;

public class ProtocolConfigurationOptions {
    public static final int PROTOCOL_ID__LCP = 0xC021;
    public static final int PROTOCOL_ID__PAP = 0xC023;
    public static final int PROTOCOL_ID__CHAP = 0xC223;
    public static final int PROTOCOL_ID__IPCP = 0x8021;

    public static final int CONT_ID_UP__PCSCF_IPV6_ADDRESS_REQUEST = 0x0001;
    public static final int CONT_ID_UP__IM_CN_SUBSYSTEM_SIGNALING_FLAG = 0x0002;
    public static final int CONT_ID_UP__DNS_SERVER_IPV6_ADDRESS_REQUEST = 0x0003;
    public static final int CONT_ID_UP__MS_SUPPORT_OF_NETWORK_REQUESTED_BEARER_CONTROL_INDICATOR = 0x0005;
    public static final int CONT_ID_UP__DSMIPV6_HOME_AGENT_ADDRESS_REQUEST = 0x0007;
    public static final int CONT_ID_UP__DSMIPV6_HOME_NETWORK_PREFIX_REQUEST = 0x0008;
    public static final int CONT_ID_UP__DSMIPV6_IPV4_HOME_AGENT_ADDRESS_REQUEST = 0x0009;
    public static final int CONT_ID_UP__IP_ADDRESS_ALLOCATION_VIA_NAS_SIGNALLING = 0x000A;
    public static final int CONT_ID_UP__IPV4_ADDRESS_ALLOCATION_VIA_DHCPV4 = 0x000B;
    public static final int CONT_ID_UP__P_CSCF_IPV4_ADDRESS_REQUEST = 0x000C;
    public static final int CONT_ID_UP__DNS_SERVER_IPV4_ADDRESS_REQUEST = 0x000D;
    public static final int CONT_ID_UP__MSISDN_REQUEST = 0x000E;
    public static final int CONT_ID_UP__IFOM_SUPPORT_REQUEST = 0x000F;
    public static final int CONT_ID_UP__IPV4_LINK_MTU_REQUEST = 0x0010;
    public static final int CONT_ID_UP__MS_SUPPORT_OF_LOCAL_ADDRESS_IN_TFT_INDICATOR = 0x0011;
    public static final int CONT_ID_UP__P_CSCF_RE_SELECTION_SUPPORT = 0x0012;
    public static final int CONT_ID_UP__NBIFOM_REQUEST_INDICATOR = 0x0013;
    public static final int CONT_ID_UP__NBIFOM_MODE = 0x0014;
    public static final int CONT_ID_UP__NON_IP_LINK_MTU_REQUEST = 0x0015;
    public static final int CONT_ID_UP__APN_RATE_CONTROL_SUPPORT_INDICATOR = 0x0016;
    public static final int CONT_ID_UP__3GPP_PS_DATA_OFF_UE_STATUS = 0x0017;
    public static final int CONT_ID_UP__RELIABLE_DATA_SERVICE_REQUEST_INDICATOR = 0x0018;
    public static final int CONT_ID_UP__ADDITIONAL_APN_RATE_CONTROL_FOR_EXCEPTION_DATA_SUPPORT_INDICATOR = 0x0019;
    public static final int CONT_ID_UP__PDU_SESSION_ID = 0x001A;
    public static final int CONT_ID_UP__ETHERNET_FRAME_PAYLOAD_MTU_REQUEST = 0x0020;
    public static final int CONT_ID_UP__UNSTRUCTURED_LINK_MTU_REQUEST = 0x0021;
    public static final int CONT_ID_UP__5GSM_CAUSE_VALUE = 0x0022;
    public static final int CONT_ID_UP__QOS_RULES_WITH_THE_LENGTH_OF_TWO_OCTETS_SUPPORT_INDICATOR = 0x0023;
    public static final int CONT_ID_UP__QOS_FLOW_DESCRIPTIONS_WITH_THE_LENGTH_OF_TWO_OCTETS_SUPPORT_INDICATOR = 0x0024;
    public static final int CONT_ID_UP__ACS_INFORMATION_REQUEST = 0x0027;
    public static final int CONT_ID_UP__ATSSS_REQUEST = 0x0030;
    public static final int CONT_ID_UP__DNS_SERVER_SECURITY_INFORMATION_INDICATOR = 0x0031;

    public static final int CONT_ID_DOWN__P_CSCF_IPV6_ADDRESS = 0x0001;
    public static final int CONT_ID_DOWN__IM_CN_SUBSYSTEM_SIGNALING_FLAG = 0x0002;
    public static final int CONT_ID_DOWN__DNS_SERVER_IPV6_ADDRESS = 0x0003;
    public static final int CONT_ID_DOWN__POLICY_CONTROL_REJECTION_CODE = 0x0004;
    public static final int CONT_ID_DOWN__SELECTED_BEARER_CONTROL_MODE = 0x0005;
    public static final int CONT_ID_DOWN__DSMIPV6_HOME_AGENT_ADDRESS = 0x0007;
    public static final int CONT_ID_DOWN__DSMIPV6_HOME_NETWORK_PREFIX = 0x0008;
    public static final int CONT_ID_DOWN__DSMIPV6_IPV4_HOME_AGENT_ADDRESS = 0x0009;
    public static final int CONT_ID_DOWN__P_CSCF_IPV4_ADDRESS = 0x000C;
    public static final int CONT_ID_DOWN__DNS_SERVER_IPV4_ADDRESS = 0x000D;
    public static final int CONT_ID_DOWN__MSISDN = 0x000E;
    public static final int CONT_ID_DOWN__IFOM_SUPPORT = 0x000F;
    public static final int CONT_ID_DOWN__IPV4_LINK_MTU = 0x0010;
    public static final int CONT_ID_DOWN__NETWORK_SUPPORT_OF_LOCAL_ADDRESS_IN_TFT_INDICATOR = 0x0011;
    public static final int CONT_ID_DOWN__NBIFOM_ACCEPTED_INDICATOR = 0x0013;
    public static final int CONT_ID_DOWN__NBIFOM_MODE = 0x0014;
    public static final int CONT_ID_DOWN__NON_IP_LINK_MTU = 0x0015;
    public static final int CONT_ID_DOWN__APN_RATE_CONTROL_PARAMETERS = 0x0016;
    public static final int CONT_ID_DOWN__3GPP_PS_DATA_OFF_SUPPORT_INDICATION = 0x0017;
    public static final int CONT_ID_DOWN__RELIABLE_DATA_SERVICE_ACCEPTED_INDICATOR = 0x0018;
    public static final int CONT_ID_DOWN__ADDITIONAL_APN_RATE_CONTROL_FOR_EXCEPTION_DATA_PARAMETERS = 0x0019;
    public static final int CONT_ID_DOWN__S_NSSAI = 0x001B;
    public static final int CONT_ID_DOWN__QOS_RULES = 0x001C;
    public static final int CONT_ID_DOWN__SESSION_AMBR = 0x001D;
    public static final int CONT_ID_DOWN__PDU_SESSION_ADDRESS_LIFETIME = 0x001E;
    public static final int CONT_ID_DOWN__QOS_FLOW_DESCRIPTIONS = 0x001F;
    public static final int CONT_ID_DOWN__ETHERNET_FRAME_PAYLOAD_MTU = 0x0020;
    public static final int CONT_ID_DOWN__UNSTRUCTURED_LINK_MTU = 0x0021;
    public static final int CONT_ID_DOWN__QOS_RULES_WITH_THE_LENGTH_OF_TWO_OCTET = 0x0023;
    public static final int CONT_ID_DOWN__QOS_FLOW_DESCRIPTIONS_WITH_THE_LENGTH_OF_TWO_OCTETS = 0x0024;
    public static final int CONT_ID_DOWN__SMALL_DATA_RATE_CONTROL_PARAMETERS = 0x0025;
    public static final int CONT_ID_DOWN__ADDITIONAL_SMALL_DATA_RATE_CONTROL_FOR_EXCEPTION_DATA_PARAMETERS = 0x0026;
    public static final int CONT_ID_DOWN__ACS_INFORMATION = 0x0027;
    public static final int CONT_ID_DOWN__INITIAL_SMALL_DATA_RATE_CONTROL_PARAMETERS = 0x0028;
    public static final int CONT_ID_DOWN__INITIAL_ADDITIONAL_SMALL_DATA_RATE_CONTROL_FOR_EXCEPTION_DATA_PARAMETERS = 0x0029;
    public static final int CONT_ID_DOWN__INITIAL_APN_RATE_CONTROL_PARAMETERS = 0x002A;
    public static final int CONT_ID_DOWN__INITIAL_ADDITIONAL_APN_RATE_CONTROL_FOR_EXCEPTION_DATA_PARAMETERS = 0x002B;
    public static final int CONT_ID_DOWN__ATSSS_RESPONSE_WITH_THE_LENGTH_OF_TWO_OCTETS = 0x0030;
    public static final int CONT_ID_DOWN__DNS_SERVER_SECURITY_INFORMATION_WITH_LENGTH_OF_TWO_OCTETS = 0x0031;

    public final List<ProtocolConfigurationItem> configurationProtocols;
    public final List<ProtocolConfigurationItem> additionalParams;

    public ProtocolConfigurationOptions() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public ProtocolConfigurationOptions(List<ProtocolConfigurationItem> configurationProtocols, List<ProtocolConfigurationItem> additionalParams) {
        this.configurationProtocols = new ArrayList<>(configurationProtocols);
        this.additionalParams = new ArrayList<>(additionalParams);
    }

    public static ProtocolConfigurationOptions decode(OctetString data, boolean isUplink) {
        var res = new ProtocolConfigurationOptions();

        var stream = new OctetInputStream(data);
        while (stream.hasNext()) {
            int id = stream.readOctet2I();
            if (isProtocolId(id)) {
                int length = stream.readOctetI();
                var contents = stream.readOctetString(length);
                res.configurationProtocols.add(new ProtocolConfigurationItem(id, isUplink, contents));
            } else {
                int length = isTwoOctetLengthContainerId(id, isUplink) ? stream.readOctet2I() : stream.readOctetI();
                var contents = stream.readOctetString(length);
                res.additionalParams.add(new ProtocolConfigurationItem(id, isUplink, contents));
            }
        }

        return res;
    }

    private static boolean isProtocolId(int id) {
        return id == 0xC021 || id == 0xC023 || id == 0xC223 || id == 0x8021;
    }

    private static boolean isTwoOctetLengthContainerId(int id, boolean isUplink) {
        return !isUplink && (id == 0x0023 || id == 0x0024 || id == 0x0030);
    }

    public OctetString encode() {
        var stream = new OctetOutputStream();

        for (var item : configurationProtocols) {
            stream.writeOctet2(item.id);
            stream.writeOctet(item.content.length);
            stream.writeOctetString(item.content);
        }

        for (var item : additionalParams) {
            stream.writeOctet2(item.id);
            if (isTwoOctetLengthContainerId(item.id, item.isUplink)) {
                stream.writeOctet2(item.content.length);
            } else {
                stream.writeOctet(item.content.length);
            }
            stream.writeOctetString(item.content);
        }

        return stream.toOctetString();
    }
}
