package ru.kettuproj.packager

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import java.nio.charset.StandardCharsets

abstract class Packable{
    private var buf: ByteBuf = Unpooled.buffer()
    val accumulator: Int
        get() = buf.readerIndex()

    constructor(buf: ByteArray){
        this.buf = Unpooled.wrappedBuffer(buf)
    }

    constructor()

    fun toByteArray(): ByteArray {
        val size = if(buf.writerIndex() != 0) buf.writerIndex()
                else buf.readerIndex()
        return buf.array().copyOfRange(0, size)
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