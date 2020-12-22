// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "jni_utils.hpp"
#include "tun_config.hpp"

#include <iostream>
#include <cstring>
#include <unistd.h>

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_ue_app_TunFunctions_tunAllocate(JNIEnv *pEnv, jclass cls, jstring namePrefix, jobjectArray allocatedName)
{
    char *if_prefix = JniConvert::jstring2string(pEnv, namePrefix);
    char *allocated_name = nullptr;

    int fd = tun_alloc(if_prefix, &allocated_name);

    pEnv->SetObjectArrayElement(allocatedName, 0, pEnv->NewStringUTF(allocated_name));

    free(if_prefix);
    free(allocated_name);

    return fd;
}

extern "C" JNIEXPORT void JNICALL Java_tr_havelsan_ueransim_app_ue_app_TunFunctions_tunConfigure(JNIEnv *pEnv, jclass cls, jstring tunName, jstring ipAddress)
{
    char *tun_name = JniConvert::jstring2string(pEnv, tunName);
    char *ip_addr = JniConvert::jstring2string(pEnv, ipAddress);

    // TODO: configure_route: true
    configure_tun_interface(tun_name, ip_addr, true);

    free(tun_name);
    free(ip_addr);
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_ue_app_TunFunctions_read(JNIEnv *pEnv, jclass cls, jint fd, jobject buffer)
{
    jlong cap = pEnv->GetDirectBufferCapacity(buffer);
    jbyte *buf = reinterpret_cast<jbyte *>(pEnv->GetDirectBufferAddress(buffer));
    return ::read(static_cast<int>(fd), buf, static_cast<size_t>(cap));
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_ue_app_TunFunctions_write(JNIEnv *pEnv, jclass cls, jint fd, jobject buffer, jint size)
{
    jbyte *buf = reinterpret_cast<jbyte *>(pEnv->GetDirectBufferAddress(buffer));
    return ::write(static_cast<int>(fd), buf, static_cast<size_t>(size));
}