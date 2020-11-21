#
# Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
# This software and all associated files are licensed under GPL-3.0.
#

if [ -z "$JAVA_HOME" ]; then
echo "ERROR: 'JAVA_HOME' is not defined" 1>&2;
exit 1
fi

make clean && make