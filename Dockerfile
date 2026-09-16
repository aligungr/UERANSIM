# syntax=docker/dockerfile:1
#
# Multi-stage Dockerfile for building and running UERANSIM (nr-gnb, nr-ue, nr-cli).
#
# Build:
#   docker build -t ueransim .
#
# Run (gNB and UE need root/NET_ADMIN to create TUN devices and manage routing):
#   docker run --rm -it --cap-add=NET_ADMIN --device /dev/net/tun ueransim nr-gnb -c config/open5gs-gnb.yaml
#   docker run --rm -it --cap-add=NET_ADMIN --device /dev/net/tun ueransim nr-ue -c config/open5gs-ue.yaml
#   docker run --rm -it ueransim nr-cli <node-name>

########################################
# Build stage
########################################
FROM ubuntu:22.04 AS builder

RUN apt-get update && apt-get install -y --no-install-recommends \
        cmake \
        make \
        g++ \
        libsctp-dev \
        lksctp-tools \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /src
COPY . .

RUN cmake -DCMAKE_BUILD_TYPE=Release -G "Unix Makefiles" -B build . \
    && cmake --build build --target nr-gnb nr-ue nr-cli devbnd -j"$(nproc)"

########################################
# Runtime stage
########################################
FROM ubuntu:22.04 AS runtime

RUN apt-get update && apt-get install -y --no-install-recommends \
        libsctp1 \
        iproute2 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /ueransim

COPY --from=builder /src/build/nr-gnb /src/build/nr-ue /src/build/nr-cli /src/build/libdevbnd.so ./
COPY --from=builder /src/tools/nr-binder ./
COPY config ./config

ENV LD_LIBRARY_PATH=/ueransim
ENV PATH="/ueransim:${PATH}"

CMD ["/bin/bash"]
