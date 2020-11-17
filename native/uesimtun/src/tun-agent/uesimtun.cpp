// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include <cstdio>
#include <iostream>
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

#include "utils.hpp"
#include "packetmeter.hpp"

#define HVL_TUN_HOST "127.0.0.1"
#define HVL_TUN_PORT 49971
#define HVL_UERANSIM_HOST "127.0.0.1"
#define HVL_UERANSIM_PORT 49972

static constexpr int BUFFER_SIZE = 65535;

static int tun_alloc()
{
    char tun_name[IFNAMSIZ];
    strcpy(tun_name, "uesimtun");

    struct ifreq ifr;
    int fd, err;

    if ((fd = open("/dev/net/tun", O_RDWR)) < 0)
    {
        perror("Opening /dev/net/tun");
        return fd;
    }

    memset(&ifr, 0, sizeof(ifr));

    ifr.ifr_flags = IFF_TUN | IFF_NO_PI;

    if (*tun_name)
        strncpy(ifr.ifr_name, tun_name, IFNAMSIZ);

    if ((err = ioctl(fd, TUNSETIFF, (void *)&ifr)) < 0)
    {
        perror("ioctl(TUNSETIFF)");
        close(fd);
        return err;
    }

    strcpy(tun_name, ifr.ifr_name);

    return fd;
}

static int bridge_alloc()
{
    int fd = udp_utils::new_socket(HVL_TUN_HOST, HVL_TUN_PORT);

    if (fd < 0)
    {
        perror("Creating bridge socket");
        return fd;
    }

    return fd;
}

static int cread(int fd, void *buf, size_t nbytes)
{
    int r = ::read(fd, buf, nbytes);
    if (r < 0)
    {
        perror("Reading");
        exit(EXIT_FAILURE);
    }
    return r;
}

int main(int argc, char *argv[])
{
    if (geteuid() != 0)
    {
        std::cerr << "Error: sudo privilage is required to invoke ioctl()" << std::endl;
        return -1;
    }

    int tun_fd = tun_alloc();
    int bridge_fd = bridge_alloc();
    int maxfd = std::max(tun_fd, bridge_fd);

    int8_t buffer[BUFFER_SIZE];

    auto urs = udp_utils::in_address(HVL_UERANSIM_HOST, HVL_UERANSIM_PORT);

    PacketMeter in_meter, out_meter;
    int in_count = 0, out_count = 0;

    std::cout << "UERANSIM TUN agent has been started..." << std::endl;

    while (true)
    {
        fd_set fds;

        FD_ZERO(&fds);
        FD_SET(tun_fd, &fds);
        FD_SET(bridge_fd, &fds);

        int ret = select(maxfd + 1, &fds, NULL, NULL, NULL);

        if (ret < 0 && errno == EINTR)
            continue;

        if (ret < 0)
        {
            perror("select()");
            exit(EXIT_FAILURE);
        }

        if (FD_ISSET(tun_fd, &fds))
        {
            int nread = cread(tun_fd, buffer, sizeof(buffer));

            sendto(bridge_fd, buffer, nread, 0, (struct sockaddr *)&urs, sizeof(urs));

            in_count += nread;
            in_meter.notify(nread);
        }

        if (FD_ISSET(bridge_fd, &fds))
        {
            int nread = read(bridge_fd, buffer, sizeof(buffer));

            if (write(tun_fd, buffer, nread) == -1) {
                perror("write()");
                exit(EXIT_FAILURE);
            }

            out_count += nread;
            out_meter.notify(nread);
        }

        printf("↑ %dB uploaded. %s MBits/s            ↓ %dB downloaded. %s MBits/s \n", in_count, in_meter.speedMbsPerSec().c_str(), out_count, out_meter.speedMbsPerSec().c_str());
    }
}