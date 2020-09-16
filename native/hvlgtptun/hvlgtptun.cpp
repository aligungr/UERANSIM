#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <unistd.h>
#include <net/if.h>
#include <linux/if_tun.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/ioctl.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <sys/time.h>
#include <errno.h>
#include <stdarg.h>

#include "udp_client.hpp"
#include "udp_server.hpp"

static constexpr int BUFFER_SIZE = 65535;
static constexpr int TUN_PORT = 49971;
static constexpr int UERANSIM_PORT = 49972;

bool debug;

static int tun_alloc(char *dev, int flags)
{
    struct ifreq ifr;
    int fd, err;

    if ((fd = open("/dev/net/tun", O_RDWR)) < 0)
    {
        perror("Opening /dev/net/tun");
        return fd;
    }

    memset(&ifr, 0, sizeof(ifr));

    ifr.ifr_flags = flags;

    if (*dev)
    {
        strncpy(ifr.ifr_name, dev, IFNAMSIZ);
    }

    if ((err = ioctl(fd, TUNSETIFF, (void *)&ifr)) < 0)
    {
        perror("ioctl(TUNSETIFF)");
        close(fd);
        return err;
    }

    strcpy(dev, ifr.ifr_name);

    return fd;
}

static int bridge_alloc()
{
    /*udp_server server{"127.0.0.1", TUN_PORT};

    while (true) {
        char buffer[BUFFER_SIZE];

        server.recv(buffer, BUFFER_SIZE);
        puts("received");
    }*/


    
    return -1;
}

int main(int argc, char *argv[])
{
    char tun_name[IFNAMSIZ];

    strcpy(tun_name, "hvlgtptun");
    int tun_fd = tun_alloc(tun_name, IFF_TUN | IFF_NO_PI);

    if (tun_fd < 0)
    {
        perror("Allocating interface");
        exit(EXIT_FAILURE);
    }

    int bridge_fd = bridge_alloc();
    if (bridge_fd < 0)
    {
        perror("Bridge setup failure");
        exit(EXIT_FAILURE);
    }

    int8_t buffer[BUFFER_SIZE];

    while (1)
    {
        int nread = read(tun_fd, buffer, sizeof(buffer));
        if (nread < 0)
        {
            perror("Reading from interface");
            close(tun_fd);
            exit(EXIT_FAILURE);
        }

        printf("Read %d bytes from device %s\n", nread, tun_name);
    }
}