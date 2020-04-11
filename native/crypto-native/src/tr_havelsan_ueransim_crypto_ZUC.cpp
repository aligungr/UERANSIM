#include <jni.h>
#include "zuc.h"
#include "utils.h"

extern "C" JNIEXPORT jintArray JNICALL Java_tr_havelsan_ueransim_crypto_ZUC_zuc(JNIEnv *env, jclass cls, jbyteArray key, jbyteArray iv, jint length)
{
    auto K = Utils::jbyteArrayToUint8Array(env, key, 1, nullptr);
    auto IV = Utils::jbyteArrayToUint8Array(env, iv, 1, nullptr);
    auto KS = new uint32_t[length];

    Zuc::Initialization(K, IV);
    Zuc::GenerateKeyStream(KS, static_cast<uint32_t>(length));

    delete[] K;
    delete[] IV;

    auto res = Utils::int32ArrayToJintArray(env, reinterpret_cast<int32_t*>(KS), length);
    delete[] KS;

    return res;
}