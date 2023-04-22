package packet

import ru.kettuproj.packager.Packet
import ru.kettuproj.packager.annotation.Protocol

@Protocol(0)
class MultiDataPacket : Packet {

    var s: String = ""
    var i: Int = -1
    var f: Float = -0.1f
    var d: Double = -0.1
    var b: Boolean = false
    var c: Char = ' '
    var l: Long = 0

    constructor(buf: ByteArray) : super(buf){
        s = readString()
        i = readInt()
        f = readFloat()
        d = readDouble()
        b = readBool()
        c = readChar()
        l = readLong()
    }

    constructor(
        s: String,
        i: Int,
        f: Float,
        d: Double,
        b: Boolean,
        c: Char,
        l: Long
    ) : super(){
        writeString(s)
        writeInt(i)
        writeFloat(f)
        writeDouble(d)
        writeBool(b)
        writeChar(c)
        writeLong(l)
    }
}