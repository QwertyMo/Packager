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

    constructor(buf: ByteArray){
        this.buf = Unpooled.wrappedBuffer(buf)
        protocol = this.buf.readInt()
    }

    override fun toString(): String {
        return javaClass.simpleName
    }

    fun getID():Int{
        return protocol
    }

    fun toByteArray(): ByteArray {
        return buf.array()
    }

    fun toByteBuff(): ByteBuf {
        return buf.copy()
    }

    fun <T> writeList(value: List<T>){
        writeInt(value.size)
        for(i in value){
            when(i){
                is String   -> writeString(i)
                is Int      -> writeInt(i)
                is Boolean  -> writeBool(i)
                is Float    -> writeFloat(i)
                is Char     -> writeChar(i)
                is Double   -> writeDouble(i)
                is Long     -> writeLong(i)
            }
        }
    }

    fun readLongList(): List<Long>{
        val size = readInt()
        val list = mutableListOf<Long>()
        for(i in 0 until size) {
            list.add(readLong())
        }
        return list
    }

    fun readIntList(): List<Int>{
        val size = readInt()
        val list = mutableListOf<Int>()
        for(i in 0 until size) {
            list.add(readInt())
        }
        return list
    }

    fun readStringList(): List<String>{
        val size = readInt()
        val list = mutableListOf<String>()
        for(i in 0 until size) {
            list.add(readString())
        }
        return list
    }

    fun readBoolList():List<Boolean>{
        val size = readInt()
        val list = mutableListOf<Boolean>()
        for(i in 0 until size) {
            list.add(readBool())
        }
        return list
    }

    fun readFloatList():List<Float>{
        val size = readInt()
        val list = mutableListOf<Float>()
        for(i in 0 until size) {
            list.add(readFloat())
        }
        return list
    }

    fun readCharList():List<Char>{
        val size = readInt()
        val list = mutableListOf<Char>()
        for(i in 0 until size) {
            list.add(readChar())
        }
        return list
    }

    fun readDoubleList():List<Double>{
        val size = readInt()
        val list = mutableListOf<Double>()
        for(i in 0 until size) {
            list.add(readDouble())
        }
        return list
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