package ru.kettuproj.packager

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import ru.kettuproj.packager.annotation.Protocol
import ru.kettuproj.packager.exception.PacketException
import java.nio.charset.StandardCharsets
import kotlin.reflect.full.findAnnotation

/**
 * Byte packet class. All packets need to be extended by this class
 *
 * @author QwertyMo
 */
abstract class Packet{

    private var buf: ByteBuf
    private var protocol: Int

    constructor(){
        this.protocol = this::class.findAnnotation<Protocol>()?.protocol ?: throw PacketException("Can't get protocol id from ${this::class.simpleName}")
        buf = Unpooled.buffer()
        writeInt(protocol)
    }

    constructor(buf: ByteBuf){
        this.buf = buf
        protocol = buf.readInt()
    }

    override fun toString(): String {
        return javaClass.simpleName
    }

    fun getProtocol():Int{
        return protocol
    }

    fun toByteArray(): ByteArray {
        return buf.array()
    }

    fun toByteBuff(): ByteBuf {
        return buf.copy()
    }

    fun writeInt(value: Int) {
        buf.writeInt(value)
    }

    fun readInt(): Int {
        return buf.readInt()
    }

    fun writeString(value: String) {
        val bytes = value.toByteArray(StandardCharsets.UTF_8)
        writeInt(bytes.size)
        buf.writeBytes(bytes)
    }

    fun readString(): String {
        val len = readInt()
        val b = ByteArray(len)
        buf.readBytes(b)
        return String(b, StandardCharsets.UTF_8)
    }

    fun writeLong(value: Long){
        buf.writeLong(value)
    }

    fun readLong(): Long{
        return buf.readLong()
    }

    fun writeBool(value: Boolean){
        buf.writeBoolean(value)
    }

    fun readBool():Boolean{
        return buf.readBoolean()
    }

    fun writeChar(value: Char){
        buf.writeChar(value.code)
    }

    fun readChar():Char{
        return buf.readChar()
    }

    fun writeFloat(value: Float){
        buf.writeFloat(value)
    }

    fun readFloat(): Float{
        return buf.readFloat()
    }

    fun writeDouble(value: Double){
        buf.writeDouble(value)
    }

    fun readDouble():Double{
        return buf.readDouble()
    }

}