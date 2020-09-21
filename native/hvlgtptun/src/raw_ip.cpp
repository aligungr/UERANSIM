#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <errno.h>
#include <netinet/tcp.h>
#include <netinet/ip.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string>
#include <iostream>
#include <vector>
#include "utils.hpp"

int main(void)
{
    int fd = socket(PF_INET, SOCK_RAW, IPPROTO_RAW);

    if (fd == -1)
    {
        perror("Failed to create socket");
        return 1;
    }

    struct sockaddr_in sin;
    sin.sin_family = AF_INET;
    sin.sin_port = htons(80); // aslında kullanılmıyor "sanırım". çünkü ip packetini komple biz veriyoruz.
    sin.sin_addr.s_addr = inet_addr("1.2.3.4"); // ama bu kullanılıyor bazen. en azından rotalama için kullanıldığını müşahede ettim. IP paketi aynı olduğu halde network interface'i farklı seçimine sebep oldu.

    std::string hardcoded = "45000054f56640004001840dc0a80125d8ef26780800ba0300010001a70c695f0000000069bb050000000000101112131415161718191a1b1c1d1e1f202122232425262728292a2b2c2d2e2f3031323334353637";
    std::vector<char> bytes = HexToBytes(hardcoded);

    if (sendto(fd, bytes.data(), bytes.size(), 0, (struct sockaddr *)&sin, sizeof(sin)) < 0)
    {
        perror("sendto failed");
    }
    else
    {
        std::cout << "Packet Send. Length :" << bytes.size() << std::endl;
    }
    return 0;
}