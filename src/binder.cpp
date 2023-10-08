//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cerrno>
#include <cstdio>
#include <cstdlib>
#include <cstring>

#include <arpa/inet.h>
#include <dlfcn.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>

int (*real_socket)(int, int, int);
int (*real_setsockopt)(int, int, int, const void *, socklen_t);
int (*real_bind)(int, const struct sockaddr *, socklen_t);
int (*real_connect)(int, const struct sockaddr *, socklen_t);

char *bind_addr_env;
unsigned long int bind_addr_saddr;
unsigned long int inaddr_any_saddr;
struct sockaddr_in local_sockaddr_in[1];

extern "C" void _init(void)
{
    const char *err;

    real_socket = reinterpret_cast<int (*)(int, int, int)>(dlsym(RTLD_NEXT, "socket"));
    if ((err = dlerror()) != nullptr)
    {
        fprintf(stderr, "dlsym (socket): %s\n", err);
    }

    real_setsockopt = reinterpret_cast<int (*)(int, int, int, const void *, socklen_t)>(dlsym(RTLD_NEXT, "setsockopt"));
    if ((err = dlerror()) != nullptr)
    {
        fprintf(stderr, "dlsym (setsockopt): %s\n", err);
    }

    real_bind = reinterpret_cast<int (*)(int, const struct sockaddr *, socklen_t)>(dlsym(RTLD_NEXT, "bind"));
    if ((err = dlerror()) != nullptr)
    {
        fprintf(stderr, "dlsym (bind): %s\n", err);
    }

    real_connect = reinterpret_cast<int (*)(int, const struct sockaddr *, socklen_t)>(dlsym(RTLD_NEXT, "connect"));
    if ((err = dlerror()) != nullptr)
    {
        fprintf(stderr, "dlsym (connect): %s\n", err);
    }

    inaddr_any_saddr = htonl(INADDR_ANY);

    bind_addr_env = getenv("UE_BIND_ADDR");

    if (bind_addr_env)
    {
        bind_addr_saddr = inet_addr(bind_addr_env);
        local_sockaddr_in->sin_family = AF_INET;
        local_sockaddr_in->sin_addr.s_addr = bind_addr_saddr;
        local_sockaddr_in->sin_port = htons(0);
    }
}

extern "C" int socket(int domain, int type, int protocol)
{
    return real_socket(domain, type, protocol);
}

extern "C" int setsockopt(int sockfd, int level, int optname, const void *optval, socklen_t optlen)
{
    return real_setsockopt(sockfd, level, optname, optval, optlen);
}

extern "C" int bind(int fd, const struct sockaddr *sk, socklen_t sl)
{
    static struct sockaddr_in *lsk_in;

    lsk_in = (struct sockaddr_in *)sk;

    if ((lsk_in->sin_family == AF_INET) && (lsk_in->sin_addr.s_addr == inaddr_any_saddr) && (bind_addr_env))
    {
        lsk_in->sin_addr.s_addr = bind_addr_saddr;
    }
    return real_bind(fd, sk, sl);
}

extern "C" int connect(int fd, const struct sockaddr *sk, socklen_t sl)
{
    static struct sockaddr_in *rsk_in;

    rsk_in = (struct sockaddr_in *)sk;

    if ((rsk_in->sin_family == AF_INET) && (bind_addr_env))
    {
        // HACK: DNS sorgusunu 127.0.0.53'e yaptığından eğer DNS sorgusu yapılıyorsa source IP değiştirmiyoruz.
        //       127.0.0.53'in hex karşılığı: 0x3500007F (big endian)
        // LATER NOTE: Aslında bunu DNS için değil genel olarak loopback'lerin hepsi için yapmak gerekir.
        //             Belki de rotalamada linux üzerinden ayar yapılırken specify ediliyordur. Bu kısma gerek
        //             kalmayabilir.
        if (rsk_in->sin_addr.s_addr != 0x3500007F)
        {
            real_bind(fd, (struct sockaddr *)local_sockaddr_in, sizeof(struct sockaddr));
        }
    }
    return real_connect(fd, sk, sl);
}