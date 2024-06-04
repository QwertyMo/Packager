package model

import ru.kettuproj.packager.Packable

class Team : Packable {
    var name: String = ""
    var players: List<Player> = listOf()

    constructor(buf: ByteArray) : super(buf){
        name = readString()
        players = readPackableList()
    }

    constructor(
        name: String,
        players: List<Player>
    ) : super(){
        this.name = name
        this.players = players
        writeString(name)
        writeList(players)
    }

    override fun toString(): String {
        return "($name $players)"
    }

    override fun hashCode(): Int {
        return "$name $players".hashCode()
    }
}