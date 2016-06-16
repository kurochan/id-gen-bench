package org.kurochan.id_gen.bench

import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
class Bench {
  // Random Based
  @Benchmark
  def fixedID = "fixed value"
  @Benchmark
  def randomID = RandomBasedIdGenerator.randomID
  @Benchmark
  def randomIDBase64 = RandomBasedIdGenerator.randomIDBase64
  @Benchmark
  def secureRandomID = RandomBasedIdGenerator.secureRandomID
  @Benchmark
  def secureRandomIDBase64 = RandomBasedIdGenerator.secureRandomIDBase64
  @Benchmark
  def secureRandomStrongID = RandomBasedIdGenerator.secureRandomStrongID
  @Benchmark
  def secureRandomStrongIDBase64 = RandomBasedIdGenerator.secureRandomStrongIDBase64
  @Benchmark
  def threadLocalRandomID = RandomBasedIdGenerator.threadLocalRandomID
  @Benchmark
  def threadLocalRandomIDBase64 = RandomBasedIdGenerator.threadLocalRandomIDBase64
  @Benchmark
  def randomUUID = RandomBasedIdGenerator.randomUUID

  // Timestamp Based
  @Benchmark
  def timestampID = TimestampBasedIdGenerator.timestampId
  @Benchmark
  def timestampSHA1ID = TimestampBasedIdGenerator.timestampSHA1Id
  @Benchmark
  def timestampFNVID = TimestampBasedIdGenerator.timestampFNVId
  @Benchmark
  def timestampFNVIDBase64 = TimestampBasedIdGenerator.timestampFNVIdBase64
  @Benchmark
  def timestampSequenceID = TimestampBasedIdGenerator.timestampSequenceId
  @Benchmark
  def timestampSequenceSHA1ID = TimestampBasedIdGenerator.timestampSequenceSHA1Id
  @Benchmark
  def timestampSequenceFNVID = TimestampBasedIdGenerator.timestampSequenceFNVId
  @Benchmark
  def timestampSequenceFNVIDBase64 = TimestampBasedIdGenerator.timestampSequenceFNVIdBase64
  @Benchmark
  def myId = TimestampBasedIdGenerator.myID
}
