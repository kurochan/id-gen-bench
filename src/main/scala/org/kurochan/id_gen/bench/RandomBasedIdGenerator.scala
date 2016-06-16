package org.kurochan.id_gen.bench

import java.security.SecureRandom
import java.util.concurrent.ThreadLocalRandom
import java.util.{ Base64, UUID }

import org.apache.commons.codec.digest.DigestUtils

import scala.util.Random

object RandomBasedIdGenerator {

  private val random = new Random
  private val secureRandom = new SecureRandom
  private val secureRandomStrong = SecureRandom.getInstanceStrong
  private val base64Encoder = Base64.getUrlEncoder().withoutPadding()

  def randomID(): String = {
    random.nextLong.toString
  }

  def randomIDBase64(): String = {
    val array = new Array[Byte](8)
    random.nextBytes(array)
    new String(base64Encoder.encode(array))
  }

  def secureRandomID(): String = {
    secureRandom.nextLong.toString
  }

  def secureRandomIDBase64(): String = {
    val array = new Array[Byte](8)
    secureRandom.nextBytes(array)
    new String(base64Encoder.encode(array))
  }

  def secureRandomStrongID(): String = {
    secureRandomStrong.nextLong.toString
  }

  def secureRandomStrongIDBase64(): String = {
    val array = new Array[Byte](8)
    secureRandomStrong.nextBytes(array)
    new String(base64Encoder.encode(array))
  }

  def threadLocalRandomID(): String = {
    ThreadLocalRandom.current().nextLong().toString
  }

  def threadLocalRandomIDBase64(): String = {
    val array = new Array[Byte](8)
    ThreadLocalRandom.current().nextBytes(array)
    new String(base64Encoder.encode(array))
  }

  def randomUUID(): String = {
    UUID.randomUUID().toString
  }

  val MACHINE_ID = "fixed-id"
  def myRandomID(): String = {
    val thread = Thread.currentThread().getName
    val id = s"${UUID.randomUUID().toString}_${MACHINE_ID}_${thread}"
    DigestUtils.sha1Hex(id)
  }
}
