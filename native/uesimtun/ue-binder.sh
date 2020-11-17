if [ -z "$1" ]
  then
    echo "No IP address supplied"
    exit
fi

addr=$1
shift

LD_PRELOAD=./libue-binder.so UE_BIND_ADDR=$addr $@