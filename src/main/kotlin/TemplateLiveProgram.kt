

import demos.classes.Animation
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.IntVector2
import java.io.File


fun main() = application {
    configure {
        width = 608
        height = 342
        hideWindowDecorations = true
        windowAlwaysOnTop = true
        position = IntVector2(1285,110)
    }
    oliveProgram {
        val animation = Animation()
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
        extend {
            drawer.clear(ColorRGBa.PINK)
            animation(((frameCount * 0.01) ) % 2.0)
            drawer.circle(100 + 200.0 * animation.pathSlider, 10.0, 20.0)
        }
    }
}