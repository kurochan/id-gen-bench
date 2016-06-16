package org.kurochan.id_gen.bench

import java.nio.ByteBuffer
import java.util.Base64
import java.util.concurrent.atomic.AtomicInteger

import org.apache.commons.codec.digest.DigestUtils

object TimestampBasedIdGenerator {

  private val MACHINE_ID = "machine-id"
  private val base64Encoder = Base64.getUrlEncoder().withoutPadding()
  private val sequence = new AtomicInteger(0)

  def timestampId: String = {
    val timestamp = System.currentTimeMillis
    s"${timestamp}_${MACHINE_ID}"
  }

  def timestampSHA1Id: String = {
    val timestamp = System.currentTimeMillis
    DigestUtils.sha1Hex(s"${timestamp}_${MACHINE_ID}")
  }

  def timestampFNVId: String = {
    val timestamp = System.currentTimeMillis
    val id = fnv64Hash(s"${timestamp}_${MACHINE_ID}")
    id.toString
  }

  def timestampFNVIdBase64: String = {
    val timestamp = System.currentTimeMillis
    val id = fnv64Hash(s"${timestamp}_${MACHINE_ID}")
    val buffer = ByteBuffer.allocate(8)
    buffer.putLong(0, id)
    new String(base64Encoder.encode(buffer.array))
  }

  def timestampSequenceId: String = {
    val timestamp = System.currentTimeMillis
    val next = sequence.addAndGet(1)
    s"${timestamp}_${MACHINE_ID}_${next}"
  }

  def timestampSequenceSHA1Id: String = {
    val timestamp = System.currentTimeMillis
    val next = sequence.addAndGet(1)
    DigestUtils.sha1Hex(s"${timestamp}_${MACHINE_ID}_${next}")
  }

  def timestampSequenceFNVId: String = {
    val timestamp = System.currentTimeMillis
    val next = sequence.addAndGet(1)
    val id = fnv64Hash(s"${timestamp}_${MACHINE_ID}_${next}")
    id.toString
  }

  def timestampSequenceFNVIdBase64: String = {
    val timestamp = System.currentTimeMillis
    val next = sequence.addAndGet(1)
    val id = fnv64Hash(s"${timestamp}_${MACHINE_ID}_${next}")
    val buffer = ByteBuffer.allocate(8)
    buffer.putLong(0, id)
    new String(base64Encoder.encode(buffer.array))
  }

  def myID: String = {
    val TIMESTAMP_BIT = 44
    val TIMESTAMP_SHIFT = 64 - TIMESTAMP_BIT
    val TIMESTAMP_INDEX = TIMESTAMP_BIT / 8
    val HASH_BIT = 64
    val ID_BIT = TIMESTAMP_BIT + HASH_BIT
    val ID_BYTE = ID_BIT / 8

    val TIME_EPOCH = 1391958000000L

    val timestamp = System.currentTimeMillis() - TIME_EPOCH
    val nextSequence = sequence.addAndGet(1)
    val hash = fnv64Hash(nextSequence + MACHINE_ID)
    val buffer = ByteBuffer.allocate(ID_BYTE)
    buffer.putLong(0, timestamp << TIMESTAMP_SHIFT)
    buffer.putLong(TIMESTAMP_INDEX, hash)
    new String(base64Encoder.encode(buffer.array))
  }

  private def fnv64Hash(input: String): Long = {
    val FNV_OFFSET_BASIS = 0xcbf29ce484222325L
    val FNV_PRIME = 0x100000001b3L
    val data = input.getBytes("UTF-8")

    data.foldLeft(FNV_OFFSET_BASIS) { (hash, byte) =>
      val mul = hash * FNV_PRIME
      mul ^ byte
    }
  }
}
