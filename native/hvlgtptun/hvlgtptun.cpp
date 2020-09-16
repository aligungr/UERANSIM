#include <stdio.h>
#include <stdlib.h>
#include <string.h>
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

#define BUFSIZE 65535
#define PORT 55555

bool debug;

static int tun_alloc(char *dev, int flags)
{
    struct ifreq ifr;
    int fd, err;
    const char *clonedev = "/dev/net/tun";

    if ((fd = open(clonedev, O_RDWR)) < 0)
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
    return 1;
}

int main(int argc, char *argv[])
{
    char tun_name[IFNAMSIZ];
    char buffer[BUFSIZE];

    strcpy(tun_name, "hvlgtptun");
    int tun_fd = tun_alloc(tun_name, IFF_TUN | IFF_NO_PI);

    if (tun_fd < 0)
    {
        perror("Allocating interface");
        exit(1);
    }

    int bridge_fd = bridge_alloc();
    if (bridge_fd < 0)
    {
        perror("Bridge setup");
        exit(1);
    }

    while (1)
    {
        int nread = read(tun_fd, buffer, sizeof(buffer));
        if (nread < 0)
        {
            perror("Reading from interface");
            close(tun_fd);
            exit(1);
        }

        printf("Read %d bytes from device %s\n", nread, tun_name);
    }
}
