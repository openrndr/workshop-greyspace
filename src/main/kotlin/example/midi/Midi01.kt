package example.midi

import org.openrndr.application
import org.openrndr.extra.midi.MidiDeviceDescription
import org.openrndr.extra.midi.MidiDeviceName

fun main() = application {
    program {

        val devices = MidiDeviceDescription.list()
        val candidate = devices.find { it.name.toLowerCase().contains("akai") }

        val controller = candidate?.open()
        require(controller != null) {
            "no midi controller connected"
        }

        controller.controlChanged.listen {
            println("controller value changed $it")
        }

        controller.noteOn.listen {
            println("note on: $it")
        }
    }
}