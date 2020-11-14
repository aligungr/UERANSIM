// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "jni_utils.hpp"
#include "ip.hpp"
#include "ip_checksum.hpp"
#include "icmp_checksum.hpp"

static const char *ECHO_DATA = "UERANSIM{Fatih'in Istanbul'u fethettigi yastasin.}{1453}";

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_app_ue_app_PingApp_createPingPacket(JNIEnv *pEnv, jclass cls, jint src, jint dest, jshort id, jshort seq)
{
  uint8_t *packet = new uint8_t[84]();

  ip_header ip(packet);
  ip.set_ver(4);
  ip.set_hl(5);
  ip.set_tos(0);
  ip.set_len(84);
  ip.set_id(0);
  ip.set_off(0x4000);
  ip.set_ttl(64);
  ip.set_p(1);
  ip.set_sum(0);
  ip.set_src(src);
  ip.set_dst(dest);

  icmp_header icmp(packet + 20);
  icmp.set_type(8);
  icmp.set_id(id);
  icmp.set_sequence(seq);

  for (size_t i = 0; i < 56; i++)
    packet[20 + 8 + i] = ECHO_DATA[i];
  
  icmp_checksum_assign(packet + 20, 64);
  ip_checksum_assign(packet);

  auto res = JniConvert::uint8array_to_jbytearray(pEnv, packet, 84);
  delete[] packet;
  return res;
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_ue_app_PingApp_handleEchoReplyPacket(JNIEnv *pEnv, jclass cls, jbyteArray ipData)
{
  uint8_t* packet = JniConvert::jbytearray_to_uint8array(pEnv, ipData, 1, nullptr);

  ip_header ip(packet);
  if (ip.ver() != 4) return 0; // IPv4
  if (ip.p() != 1) return 0; // ICMP

  icmp_header icmp(packet + ip.hl() * 4);
  if (icmp.type() != 0) return 0; // Echo reply

  // TODO: Also checksum of IP and ICMP may be controlled.
  // TODO: Data also may be controlled (ECHO_DATA)
  return (icmp.id() << 16) | icmp.sequence();
}