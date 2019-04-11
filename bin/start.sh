#!/bin/sh
PID_FILE=himage.pid
rm -f ${PID_FILE}
nohup java -jar target/HImage.jar >> himage_nohup.out 2>&1 &
echo $! | awk '{print $2}' > ${PID_FILE}
tpid=`cat ${PID_FILE}`
echo 'PID:${tpid}.'
echo 'server start successful.'