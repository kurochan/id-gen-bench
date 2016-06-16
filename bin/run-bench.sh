#!/bin/bash

NUM_CPU_THREAD=64

function set_enable_cpu() {
  max_index=`expr $NUM_CPU_THREAD - 1`
  for i in `seq 1 $max_index`; do
    echo 1 > /sys/devices/system/cpu/cpu$i/online
  done

  sleep 0.1

  disable=`expr $NUM_CPU_THREAD - ${1:-$NUM_CPU_THREAD}`
  for i in `seq 1 $disable`; do
    echo 0 > /sys/devices/system/cpu/cpu$i/online
  done
}

for j in `seq 1 $NUM_CPU_THREAD`; do
  echo "start $j / $NUM_CPU_THREAD"
  set_enable_cpu $j
  sleep 0.1
  sbt "jmh:run -wi 10 -i 8 -f 1 -t $j -o bench-logs/bench-$j.log"
done
set_enable_cpu
