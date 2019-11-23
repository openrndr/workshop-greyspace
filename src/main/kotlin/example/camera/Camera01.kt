package example.camera

import org.openrndr.application
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
            camera.draw(drawer)
        }
    }
}