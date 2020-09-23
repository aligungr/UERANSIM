const char *dev = "ogstun";

#include <pcap.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define SNAP_LEN 65535

void got_packet(u_char *args, const struct pcap_pkthdr *header, const u_char *packet)
{
    puts("got got got got got got got got got got got got got got got ");
    return;
}

int main()
{
    char errbuf[PCAP_ERRBUF_SIZE];

    pcap_t *handle = pcap_open_live(dev, SNAP_LEN, 1, 1000, errbuf);
    if (handle == NULL)
    {
        fprintf(stderr, "Couldn't open device %s: %s\n", dev, errbuf);
        exit(EXIT_FAILURE);
    }

    int num_packets = 10;
    pcap_loop(handle, num_packets, got_packet, NULL);
}