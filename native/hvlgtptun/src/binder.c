#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <dlfcn.h>
#include <errno.h>
#include <arpa/inet.h>

int (*real_socket)(int, int, int);
int (*real_setsockopt)(int, int, int, const void *, socklen_t);
int (*real_bind)(int, const struct sockaddr *, socklen_t);
int (*real_connect)(int, const struct sockaddr *, socklen_t);

char *bind_addr_env = "10.81.0.0"; // TODO: IP per UE
unsigned long int bind_addr_saddr;
unsigned long int inaddr_any_saddr;
struct sockaddr_in local_sockaddr_in[] = {0};

void _init(void)
{
    const char *err;

    real_socket = dlsym(RTLD_NEXT, "socket");
    if ((err = dlerror()) != NULL)
    {
        fprintf(stderr, "dlsym (socket): %s\n", err);
    }

    real_setsockopt = dlsym(RTLD_NEXT, "setsockopt");
    if ((err = dlerror()) != NULL)
    {
        fprintf(stderr, "dlsym (setsockopt): %s\n", err);
    }

    real_bind = dlsym(RTLD_NEXT, "bind");
    if ((err = dlerror()) != NULL)
    {
        fprintf(stderr, "dlsym (bind): %s\n", err);
    }

    real_connect = dlsym(RTLD_NEXT, "connect");
    if ((err = dlerror()) != NULL)
    {
        fprintf(stderr, "dlsym (connect): %s\n", err);
    }

    inaddr_any_saddr = htonl(INADDR_ANY);
    if (bind_addr_env)
    {
        bind_addr_saddr = inet_addr(bind_addr_env);
        local_sockaddr_in->sin_family = AF_INET;
        local_sockaddr_in->sin_addr.s_addr = bind_addr_saddr;
        local_sockaddr_in->sin_port = htons(0);
    }
}

int socket(int domain, int type, int protocol)
{
    return real_socket(domain, type, protocol);
}

int setsockopt(int sockfd, int level, int optname, const void *optval, socklen_t optlen)
{
    return real_setsockopt(sockfd, level, optname, optval, optlen);
}

int bind(int fd, const struct sockaddr *sk, socklen_t sl)
{
    static struct sockaddr_in *lsk_in;

    lsk_in = (struct sockaddr_in *)sk;

    if ((lsk_in->sin_family == AF_INET) && (lsk_in->sin_addr.s_addr == inaddr_any_saddr) && (bind_addr_env))
    {
        lsk_in->sin_addr.s_addr = bind_addr_saddr;
    }
    return real_bind(fd, sk, sl);
}

int connect(int fd, const struct sockaddr *sk, socklen_t sl)
{
    static struct sockaddr_in *rsk_in;

    rsk_in = (struct sockaddr_in *)sk;

    if ((rsk_in->sin_family == AF_INET) && (bind_addr_env))
    {
        // HACK: DNS sorgusunu 127.0.0.53'e yaptığından eğer DNS sorgusu yapılıyorsa source IP değiştirmiyoruz.
        // 127.0.0.53'in hex karşılığı: 0x3500007F (big endian)
        if (rsk_in->sin_addr.s_addr != 0x3500007F)
        {
            real_bind(fd, (struct sockaddr *)local_sockaddr_in, sizeof(struct sockaddr));
        }
    }
    return real_connect(fd, sk, sl);
}