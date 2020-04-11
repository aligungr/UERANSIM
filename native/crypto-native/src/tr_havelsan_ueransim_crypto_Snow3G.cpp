#include "tr_havelsan_ueransim_crypto_Snow3G.h"
#include "snow3g.h"
#include "utils.h"

extern "C" JNIEXPORT jintArray JNICALL Java_tr_havelsan_ueransim_crypto_Snow3G_snow3g(JNIEnv *env, jclass cls, jbyteArray key, jbyteArray iv, jint length)
{
    auto K = Utils::jbyteArrayToUint32Array(env, key, nullptr);
    auto IV = Utils::jbyteArrayToUint32Array(env, iv, nullptr);
    auto KS = new uint32_t[length];

    Snow3G::Initialize(K, IV);
    Snow3G::GenerateKeyStream(static_cast<uint32_t>(length), KS);

    delete[] K;
    delete[] IV;

    auto res = Utils::int32ArrayToJintArray(env, reinterpret_cast<int32_t*>(KS), length);
    delete[] KS;

    return res;
}