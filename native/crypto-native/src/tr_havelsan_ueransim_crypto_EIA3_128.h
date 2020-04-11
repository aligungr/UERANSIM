#include <jni.h>

#pragma once

extern "C" JNIEXPORT jint JNICALL Java_tr_havelsan_ueransim_crypto_EIA3_1128_computeMac
  (JNIEnv *, jclass, jlong, jint, jboolean, jbyteArray, jint, jbyteArray);
