package model

import ru.kettuproj.packager.Packable

class Player : Packable {
    var name: String = ""
    var maxHP: Int = -1
    var curHP: Int = -1
    var id: Long = -1
    constructor(buf: ByteArray) : super(buf){
        name = readString()
        maxHP = readInt()
        curHP = readInt()
        id = readLong()
    }

    constructor(
        name: String,
        maxHP: Int,
        curHP: Int,
        id: Long
    ) : super(){
        this.name = name
        this.maxHP = maxHP
        this.curHP = curHP
        this.id = id
        writeString(name)
        writeInt(maxHP)
        writeInt(curHP)
        writeLong(id)
    }

    override fun toString(): String {
        return "($name $maxHP $curHP $id)"
    }

    override fun hashCode(): Int {
        return "$name $maxHP $curHP $id".hashCode()
    }
}