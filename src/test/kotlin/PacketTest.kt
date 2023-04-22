import model.Player
import org.junit.jupiter.api.Test
import packet.*
import ru.kettuproj.packager.PacketManager
import kotlin.test.assertEquals

class PacketTest {

    private val manager: PacketManager = PacketManager()

    init {
        manager.registerPacket(TextPacket::class)
        manager.registerPacket(MultiDataPacket::class)
        manager.registerPacket(ListPacket::class)
        manager.registerPacket(PlayerPacket::class)
        manager.registerPacket(PlayersPacket::class)
    }

    @Test
    fun testText(){
        val string = "Test string"
        val packetOut = TextPacket(string)
        val packetIn  = manager.readPacket(packetOut.toByteArray()) as TextPacket
        assertEquals(string, packetIn.name)
    }

    @Test
    fun testMultiField(){
        val s: String = "test string "
        val i: Int = 1234
        val f: Float = -123.512f
        val d: Double = -435614.12
        val b: Boolean = true
        val c: Char = 'f'
        val l: Long = 7861240978134
        val packetOut = MultiDataPacket(s, i, f, d, b, c, l)
        val packetIn  = manager.readPacket(packetOut.toByteArray()) as MultiDataPacket
        assertEquals(s, packetIn.s)
        assertEquals(i, packetIn.i)
        assertEquals(f, packetIn.f)
        assertEquals(d, packetIn.d)
        assertEquals(b, packetIn.b)
        assertEquals(c, packetIn.c)
        assertEquals(l, packetIn.l)
    }

    @Test
    fun testList(){
        val s: List<String> = listOf("1", "234", "543112")
        val i: List<Int> = listOf(1, 432, 1153, 42, 123)
        val f: List<Float> = listOf(1f, 432f, 1153f, 42f, 123f)
        val d: List<Double> = listOf(1.0, 432.432, 1153.2, 42.555555, 123.0123)
        val b: List<Boolean> = listOf(true, false)
        val c: List<Char> = listOf('1', 'f', '5')
        val l: List<Long> = listOf(14,1452132189,1232,559953495820324,523489231432)
        val packetOut = ListPacket(s, i, f, d, b, c, l)
        val packetIn  = manager.readPacket(packetOut.toByteArray()) as ListPacket
        assertEquals(s, packetIn.s)
        assertEquals(i, packetIn.i)
        assertEquals(f, packetIn.f)
        assertEquals(d, packetIn.d)
        assertEquals(b, packetIn.b)
        assertEquals(c, packetIn.c)
        assertEquals(l, packetIn.l)
    }

    @Test
    fun testPackable(){
        val s = "test"
        val player = Player("QwertyMo", 100, 23, 5123412)
        val packetOut = PlayerPacket(player, s)
        val packetIn  = manager.readPacket(packetOut.toByteArray()) as PlayerPacket
        assertEquals(player.name, packetIn.player.name)
        assertEquals(player.maxHP, packetIn.player.maxHP)
        assertEquals(player.curHP, packetIn.player.curHP)
        assertEquals(player.id, packetIn.player.id)
        assertEquals(s, packetIn.s)
    }

    @Test
    fun testListOfPlayers(){
        val players = listOf(
            Player("QwertyMo", 100, 23, 1),
            Player("QwertyMo", 230, 11, 2),
            Player("QwertyMo", 22, 1, 3)
        )
        val packetOut = PlayersPacket(players)
        val packetIn  = manager.readPacket(packetOut.toByteArray()) as PlayersPacket
        for(player in players){
            val p = packetIn.players.find { it.id == player.id }!!
            assertEquals(p.id, player.id)
            assertEquals(p.maxHP, player.maxHP)
            assertEquals(p.curHP, player.curHP)
            assertEquals(p.name, player.name)
        }
    }
}

