package example.camera

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorType
import org.openrndr.draw.isolatedWithTarget
import org.openrndr.draw.renderTarget
import org.openrndr.draw.tint
import org.openrndr.ffmpeg.VideoPlayerFFMPEG

fun main() = application {

    configure {
        width = 1280
        height = 720
    }

    program {
        val camera = VideoPlayerFFMPEG.fromDevice()

        val target = renderTarget(1280, 720) {
            colorBuffer(type = ColorType.FLOAT32)
        }

        camera.play()
        extend {
            drawer.isolatedWithTarget(target) {
                val f = mouse.position.y / height
                drawer.drawStyle.colorMatrix = tint(ColorRGBa.WHITE.opacify(f))
                camera.draw(drawer)
            }
            drawer.image(target.colorBuffer(0))
        }
    }
}