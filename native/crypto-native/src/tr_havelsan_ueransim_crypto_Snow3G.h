#pragma once

#include <jni.h>

extern "C" JNIEXPORT jintArray JNICALL Java_tr_havelsan_ueransim_crypto_Snow3G_snow3g
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jint);
