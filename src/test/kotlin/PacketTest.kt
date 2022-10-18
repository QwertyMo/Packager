import org.junit.jupiter.api.Test
import packet.MultiDataPacket
import packet.TextPacket
import ru.kettuproj.packager.PacketManager
import kotlin.test.assertEquals

class PacketTest {

    private val manager: PacketManager = PacketManager()

    init {
        manager.registerPacket(TextPacket::class)
        manager.registerPacket(MultiDataPacket::class)
    }

    @Test
    fun testText(){
        val string = "Test string"
        val packetOut = TextPacket(string)
        val packetIn  = manager.readPacket(packetOut.toByteBuff()) as TextPacket
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
        val packetIn  = manager.readPacket(packetOut.toByteBuff()) as MultiDataPacket
        assertEquals(s, packetIn.s)
        assertEquals(i, packetIn.i)
        assertEquals(f, packetIn.f)
        assertEquals(d, packetIn.d)
        assertEquals(b, packetIn.b)
        assertEquals(c, packetIn.c)
        assertEquals(l, packetIn.l)
    }
}

