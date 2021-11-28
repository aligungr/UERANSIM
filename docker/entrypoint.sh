#!/usr/bin/env bash

set -euo pipefail

CONFIG_DIR="/ueransim/etc"

# Default values
USE_FQDN=${USE_FQDN:-no}

if [[ ${USE_FQDN} == "yes" ]];then
    NGAPPeerAddr=(`getent hosts $AMF_FQDN | awk '{print $1}'`)
    echo -e "\nResolving AMF by FQDN : $AMF_FQDN - $NGAPPeerAddr"
fi

for c in ${CONFIG_DIR}/*.yaml; do
    # grep variable names (format: ${VAR}) from template to be rendered
    VARS=$(grep -oP '@[a-zA-Z0-9_]+@' ${c} | sort | uniq | xargs)
    echo "Now setting these variables '${VARS}'"

    # create sed expressions for substituting each occurrence of ${VAR}
    # with the value of the environment variable "VAR"
    EXPRESSIONS=""
    for v in ${VARS}; do
        NEW_VAR=$(echo $v | sed -e "s#@##g")
        if [[ -z ${!NEW_VAR+x} ]]; then
            echo "Error: Environment variable '${NEW_VAR}' is not set." \
                "Config file '$(basename $c)' requires all of $VARS."
            exit 1
        fi
        EXPRESSIONS="${EXPRESSIONS};s|${v}|${!NEW_VAR}|g"
    done
    EXPRESSIONS="${EXPRESSIONS#';'}"

    # render template and inline replace config file
    sed -i "${EXPRESSIONS}" ${c}
done
echo "Done setting the configuration"
echo "### Running ueransim ###"

echo "Running gnb"
/ueransim/bin/nr-gnb -c /ueransim/etc/custom-gnb.yaml &

sleep 1
echo "Running ue"
/ueransim/bin/nr-ue -c /ueransim/etc/custom-ue.yaml -n $NUMBER_OF_UE
exec "$@" 
