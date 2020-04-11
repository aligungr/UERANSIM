#include <jni.h>

#pragma once

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_encrypt
  (JNIEnv *, jclass, jlong, jint, jboolean, jbyteArray, jint, jbyteArray);

extern "C" JNIEXPORT jbyteArray JNICALL Java_tr_havelsan_ueransim_crypto_EEA3_1128_decrypt
  (JNIEnv *, jclass, jlong, jint, jboolean, jbyteArray, jint, jbyteArray);
