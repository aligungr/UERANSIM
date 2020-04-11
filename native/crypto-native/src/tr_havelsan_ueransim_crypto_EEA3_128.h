#pragma once

#include <jni.h>

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_eea3
  (JNIEnv *, jclass, jlong, jint, jboolean, jbyteArray, jint, jbyteArray);
