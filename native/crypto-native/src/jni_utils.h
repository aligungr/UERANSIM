/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

#pragma once

#include <string>
#include <cstdint>
#include <jni.h>

namespace JniConvert
{

uint8_t *jbytearray_to_uint8array(JNIEnv *pJniEnv, jbyteArray pJba, jsize alignment, jsize *pOutLength);

uint32_t *jbytearray_to_uint32array(JNIEnv *pJniEnv, jbyteArray pJba, jsize *pOutLength);

jbyteArray uint32array_to_jbytearray(JNIEnv *pJniEnv, uint32_t *pArray, jsize nLength);

jintArray int32array_to_jintarray(JNIEnv *pJniEnv, int32_t *pArray, jsize nLength);

jbyteArray uint8array_to_jbytearray(JNIEnv *pJniEnv, uint8_t *pArray, jsize nLength);

} // namespace JniConvert