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

        val osc = OSC(portIn=57110)

        var ax = 0.0
        var ay = 0.0
        var az = 0.0

        // this is for GyroOsc
        osc.listen("/gyrosc/accel") {
            ax = (it[0] as Float).toDouble()
            ay = (it[1] as Float).toDouble()
            az = (it[2] as Float).toDouble()

        }
        extend {
            drawer.fontMap = font
            drawer.fill = ColorRGBa.WHITE
            drawer.text("$ip port: 57110", 20.0, 20.0)

            drawer.strokeWeight = 10.0
            drawer.stroke = ColorRGBa.PINK

            drawer.lineSegment(30.0, height/2.0, 30.0, height/2.0 + ax * 100.0)
            drawer.lineSegment(60.0, height/2.0, 60.0, height/2.0 + ay * 100.0)
            drawer.lineSegment(90.0, height/2.0, 90.0, height/2.0 + az * 100.0)
        }
    }
}