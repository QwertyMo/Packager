package ru.kettuproj.packager

import io.netty.buffer.Unpooled
import ru.kettuproj.packager.annotation.Protocol
import ru.kettuproj.packager.exception.PacketException
import java.lang.Exception
import kotlin.reflect.KClass
import kotlin.reflect.full.createType
import kotlin.reflect.full.findAnnotation

/**
 * Packet register and read manager. All packets must register here.
 *
 * @author QwertyMo
 */
class PacketManager{
    private val packets : MutableMap<Int, KClass<*>> = mutableMapOf()

    /**
     * Register packet function
     *
     * @param packet class of packet
     *
     * @author QwertyMo
     */
    fun registerPacket(packet: KClass<*>){
        if(packet.java.constructors.size!=2)
            throw PacketException("Error creating instance without 2 constructors from $packet")
       val id: Int = packet.findAnnotation<Protocol>()?.protocol ?: throw PacketException("Error registering packet $packet, because it don't contains protocol id")
        if(packets.keys.contains(id))
            throw PacketException("Can't register input packet $packet, because id $id already in use")
        packets[id] = packet
    }

    /**
     * Read packet class. Get byte buffer, and return registered packet. If packet
     * from this byte buffer is not registered, return null
     *
     * @param bytes byte buffer with packet
     *
     * @return packet or null, if not exists
     *
     * @author QwertyMo
     */
    fun readPacket(bytes: ByteArray) : Packet?{
        val packet = packets[getProtocol(bytes)] ?: return null
        return try {
            packet.constructors.find {
                    construct ->
                    val i = construct.parameters.filter {
                        param ->
                        param.type == ByteArray::class.createType()
                    }
                    i.size == 1
            }?.call(bytes) as Packet?
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    private fun getProtocol(bytes: ByteArray): Int?{
        return try{
            Unpooled.copiedBuffer(bytes).readInt()
        }catch (e: Exception){
            null
        }
    }
}