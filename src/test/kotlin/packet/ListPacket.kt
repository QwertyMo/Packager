package packet

import ru.kettuproj.packager.Packet
import ru.kettuproj.packager.annotation.Protocol

@Protocol(2)
class ListPacket : Packet {

    var s: List<String> = listOf()
    var i: List<Int> = listOf()
    var f: List<Float> = listOf()
    var d: List<Double> = listOf()
    var b: List<Boolean> = listOf()
    var c: List<Char> = listOf()
    var l: List<Long> = listOf()

    constructor(buf: ByteArray) : super(buf){
        s = readStringList()
        i = readIntList()
        f = readFloatList()
        d = readDoubleList()
        b = readBoolList()
        c = readCharList()
        l = readLongList()
    }

    constructor(
        s: List<String>,
        i: List<Int>,
        f: List<Float>,
        d: List<Double>,
        b: List<Boolean>,
        c: List<Char>,
        l: List<Long>
    ) : super(){
        writeList(s)
        writeList(i)
        writeList(f)
        writeList(d)
        writeList(b)
        writeList(c)
        writeList(l)
    }
}