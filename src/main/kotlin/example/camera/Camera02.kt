package example.camera

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.tint
import org.openrndr.ffmpeg.VideoPlayerFFMPEG

fun main() = application {

    configure {
        width = 1280
        height = 720
    }

    program {
        val camera = VideoPlayerFFMPEG.fromDevice()
        camera.play()
        extend {
            drawer.drawStyle.colorMatrix = tint(ColorRGBa.RED)
            camera.draw(drawer)
        }
    }
}