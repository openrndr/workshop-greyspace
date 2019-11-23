package example.osc

import library.getLocalIP
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extra.osc.OSC

// some good and free OSC apps for the phone
// Android
// oscHook - hollyhook
// videoOSC - Stefan Nussbaumer (haven't managed to get this to work yet)

// iOS
// GyrOsc (is what is used the performance) is not free (but cheap)
// Little OSC - just 4 buttons

fun main() = application {

    configure {
        width = 600
        height = 600
    }

    program {
        val ip = getLocalIP()
        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 24.0)

        val osc = OSC(portIn=57120)

        // this is for Little OSC

        var button1 = 0.0
        var button2 = 0.0
        var button3 = 0.0
        var button4 = 0.0

        osc.listen("/1/push1") {
            button1 = (it[0] as Float).toDouble()
        }
        osc.listen("/1/push2") {
            button2 = (it[0] as Float).toDouble()
        }
        osc.listen("/1/push3") {
            button3 = (it[0] as Float).toDouble()
        }
        osc.listen("/1/push4") {
            button4 = (it[0] as Float).toDouble()
        }

        extend {
            drawer.fontMap = font
            drawer.fill = ColorRGBa.WHITE
            drawer.text("$ip port: 57120", 20.0, 20.0)


            drawer.circle(200.0, 200.0, button1 * 50.0)
            drawer.circle(400.0, 200.0, button2 * 50.0)
            drawer.circle(200.0, 400.0, button3 * 50.0)
            drawer.circle(400.0, 400.0, button4 * 50.0)

        }



    }

}