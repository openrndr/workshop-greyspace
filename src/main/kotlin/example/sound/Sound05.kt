package example.sound

import library.OpenALIO
import net.beadsproject.beads.core.AudioContext
import net.beadsproject.beads.core.IOAudioFormat
import net.beadsproject.beads.data.Buffer
import net.beadsproject.beads.ugens.Gain
import net.beadsproject.beads.ugens.OnePoleFilter
import net.beadsproject.beads.ugens.ScalingMixer
import net.beadsproject.beads.ugens.WavePlayer
import org.openrndr.application

fun main() = application {
    program {
        val alio = OpenALIO()
        val context = AudioContext(alio, 44100/60, IOAudioFormat(44100.0f, 16, 0, 1))

        val wave0 = WavePlayer(context, 55.0f, Buffer.SQUARE)
        val wave1 = WavePlayer(context, 110.0f, Buffer.SQUARE)
        val wave2 = WavePlayer(context, 220.0f, Buffer.SQUARE)
        val wave3 = WavePlayer(context, 440.0f, Buffer.SQUARE)

        val mixer = ScalingMixer(context)

        val gain = Gain(context, 1, 0.1f)
        val filter = OnePoleFilter(context, 400.0f)

        mixer.addInput(wave0)
        mixer.addInput(wave1)
        mixer.addInput(wave2)
        mixer.addInput(wave3)

        gain.addInput(mixer)
        filter.addInput(gain)
        context.out.addInput(filter)
        context.start()

        extend {
            val f = mouse.position.y/height
            wave0.frequency = (55.0f + 2.0f * f).toFloat()
            wave1.frequency = (110.0f - 4.0f * f).toFloat()
            wave2.frequency = (220.0f + 8.0f * f).toFloat()
            wave3.frequency = (440.0f - 16.0f * f).toFloat()
            filter.frequency = (mouse.position.x + 20.0).toFloat()
        }
    }
}