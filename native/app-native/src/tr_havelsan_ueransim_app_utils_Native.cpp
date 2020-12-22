// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "jni_utils.hpp"
#include "tun_config.hpp"

#include <unistd.h>
#include <sys/types.h>

extern "C" JNIEXPORT jboolean JNICALL Java_tr_havelsan_ueransim_app_utils_Native_isRoot(JNIEnv *pEnv, jclass cls)
{
    return geteuid() == 0;
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_utils_Native_tunAllocate(JNIEnv *pEnv, jclass cls, jstring namePrefix, jobjectArray allocatedName, jobjectArray error)
{
    char *if_prefix = JniConvert::jstring2string(pEnv, namePrefix);
    char *allocated_name = nullptr;

    jobject err = nullptr;
    int fd = 0;

    try
    {
        fd = tun_alloc(if_prefix, &allocated_name);
    }
    catch (const tun_config_error &e)
    {
        err = pEnv->NewStringUTF(e.what());
    }

    pEnv->SetObjectArrayElement(allocatedName, 0, pEnv->NewStringUTF(allocated_name));
    pEnv->SetObjectArrayElement(error, 0, err);

    free(if_prefix);
    free(allocated_name);

    return fd;
}

extern "C" JNIEXPORT void JNICALL Java_tr_havelsan_ueransim_app_utils_Native_tunConfigure(JNIEnv *pEnv, jclass cls, jstring tunName, jstring ipAddress, jboolean configureRouting, jobjectArray error)
{
    char *tun_name = JniConvert::jstring2string(pEnv, tunName);
    char *ip_addr = JniConvert::jstring2string(pEnv, ipAddress);

    jobject err = nullptr;

    try
    {
        configure_tun_interface(tun_name, ip_addr, configureRouting);
    }
    catch (const tun_config_error &e)
    {
        err = pEnv->NewStringUTF(e.what());
    }

    pEnv->SetObjectArrayElement(error, 0, err);

    free(tun_name);
    free(ip_addr);
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_utils_Native_read(JNIEnv *pEnv, jclass cls, jint fd, jobject buffer)
{
    jlong cap = pEnv->GetDirectBufferCapacity(buffer);
    jbyte *buf = reinterpret_cast<jbyte *>(pEnv->GetDirectBufferAddress(buffer));
    return ::read(static_cast<int>(fd), buf, static_cast<size_t>(cap));
}

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_app_utils_Native_write(JNIEnv *pEnv, jclass cls, jint fd, jobject buffer, jint size)
{
    jbyte *buf = reinterpret_cast<jbyte *>(pEnv->GetDirectBufferAddress(buffer));
    return ::write(static_cast<int>(fd), buf, static_cast<size_t>(size));
}
