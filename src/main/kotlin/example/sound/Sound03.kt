package example.sound

import library.OpenALIO
import net.beadsproject.beads.core.AudioContext
import net.beadsproject.beads.core.IOAudioFormat
import net.beadsproject.beads.data.Buffer
import net.beadsproject.beads.ugens.Gain
import net.beadsproject.beads.ugens.OnePoleFilter
import net.beadsproject.beads.ugens.WavePlayer
import org.openrndr.application

fun main() = application {
    program {
        val alio = OpenALIO()
        val context = AudioContext(alio, 44100/60, IOAudioFormat(44100.0f, 16, 0, 1))

        val wave = WavePlayer(context, 220.0f, Buffer.SQUARE)
        val gain = Gain(context, 1, 0.1f)
        val filter = OnePoleFilter(context, 400.0f)

        filter.addInput(wave)
        gain.addInput(filter)
        context.out.addInput(gain)
        context.start()

        extend {

            wave.frequency = (mouse.position.y + 20.0).toFloat()
            filter.frequency = (mouse.position.x + 20.0).toFloat()
        }
    }
}