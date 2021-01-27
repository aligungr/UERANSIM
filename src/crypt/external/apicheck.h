/*
 * apicheck.h
 * Copyright (C) 2017 Adrian Perez <aperez@igalia.com>
 *
 * Distributed under terms of the MIT license.
 */

#ifndef APICHECK_H
#define APICHECK_H

#include <stdio.h>
#include <stdlib.h>

#ifndef API_CHECK_OUTPUT_FILE
#define API_CHECK_OUTPUT_FILE stderr
#endif /* !API_CHECK_OUTPUT_FILE */

#ifndef API_CHECK_SHOULD_ABORT
#define API_CHECK_SHOULD_ABORT 0
#endif /* !API_CHECK_SHOULD_ABORT */

#if defined(API_CHECK_DISABLE) && API_CHECK_DISABLE

#define api_check_return(expr) ((void)(expr))
#define api_check_return_val(expr, val) ((void)(expr), (void)(val))

#else

#define api_check_return_val(expr, val)                                                                                \
    do                                                                                                                 \
    {                                                                                                                  \
        if (!(expr))                                                                                                   \
        {                                                                                                              \
            FILE *__api_check_output_##__LINE__ = (API_CHECK_OUTPUT_FILE);                                             \
            if (!__api_check_output_##__LINE__)                                                                        \
                __api_check_output_##__LINE__ = stderr;                                                                \
            fprintf(__api_check_output_##__LINE__, "API check '%s' failed at %s (%s:%d)\n", #expr, __func__, __FILE__, \
                    __LINE__);                                                                                         \
            fflush(__api_check_output_##__LINE__);                                                                     \
            if (!!(API_CHECK_SHOULD_ABORT))                                                                            \
                abort();                                                                                               \
            return val;                                                                                                \
        }                                                                                                              \
    } while (0)

#define api_check_return(expr) api_check_return_val(expr, )

#endif /* API_CHECK_DISABLE */

#endif /* !APICHECK_H */
