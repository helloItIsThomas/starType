

import demos.classes.Animation
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.draw.font.loadFace
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.extra.jumpfill.fx.OuterGlow
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.IntVector2
import org.openrndr.math.Matrix44
import org.openrndr.math.transforms.scale
import org.openrndr.shape.SegmentJoin
import org.openrndr.shape.Shape
import org.openrndr.shape.offset
import org.openrndr.shape.shape
import java.io.File
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan


fun main() = application {
    configure {
        width = 304
        height = 171
        hideWindowDecorations = true
        windowAlwaysOnTop = true
        windowTransparent = true
        position = IntVector2(1285,110)
    }
    oliveProgram {
        var marginX = 50.0
        var marginY = 150.0
        val animation = Animation()
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
        val golf = loadImage(File("data/images/cheeta.jpg"))
        val fontSize = 100.0
        val font0: FontImageMap = loadFont("data/fonts/default.otf", fontSize)
        val font1: FontMap = loadFont("data/fonts/default.otf", fontSize)
        val face0 = loadFace("data/fonts/default.otf")
        val tShape: Shape = face0.glyphForCharacter('a').shape(750.0)
        var modX = 0.5
        var modY = animation.pathSlider * 0.7

        val c = compose {
            layer {
//                draw {
//                    drawer.fill = null
//                    drawer.rectangle(drawer.bounds)
//                }
//                post(OuterGlow()) {
//                    this.width = 5.0
//                    this.noise = 0.1
//                }
            }
        }

        extend {


//            modY = animation.pathSlider * 0.444
//            modY = animation.pathSlider * 0.444
            modY = animation.pathSlider * 0.333
            modX = 0.333
//            modX = 0.444


            drawer.clear(ColorRGBa.TRANSPARENT)
//            animation(((frameCount * 0.000888) ) % 1.0)
//            animation(((frameCount * 0.00888) ) % 1.0)
            animation(((frameCount * 0.00444) ) % 1.0)
            drawer.fill = ColorRGBa.WHITE


            drawer.pushTransforms()
            drawer.translate(drawer.bounds.center)
            // what am I trying to do?
            // i am trying to make it so that the shape draws itself.
            val scaleMatrix = Matrix44.scale(modX, modY, 1.0)

//            val rows = 6
//            val cols = 6
//            val spacing = 50.0
//
//            drawer.apply {
//                for (y in 0 until rows) {
//                    for (x in 0 until cols) {
//                        drawer.pushTransforms()
//                        translate(x * spacing, y * spacing)
//                        fill = ColorRGBa.WHITE
//                        contour(tShape.closedContours[0].contour.transform(scaleMatrix))
//                        fill = ColorRGBa.BLACK
//                        contour(tShape.closedContours[1].contour.transform(scaleMatrix))
//                        drawer.popTransforms()
//                    }
//                }
//            }


            drawer.apply {
                fill = ColorRGBa.WHITE
                fill = null
                stroke = ColorRGBa.WHITE
                val baseContour = tShape.closedContours[0].contour.transform(scaleMatrix)
//                val secondContour = tShape.closedContours[1].contour.transform(scaleMatrix)
                val lineSlider = animation.lineSlider
//                val timeFactor = cos(seconds + 0.5)
                val timeFactor = frameCount

                drawer.pushTransforms()
                translate(10.0, 50.0)

                drawer.strokeWeight = 1.0

                for (i in 1.. 10) {
//                    val o = baseContour.sub(lineSlider, lineSlider + 0.3).offset(1.0).offset(i * 10.0 * timeFactor)
//                    val o = baseContour.offset(i * -2.0) //.sub(lineSlider, lineSlider + 0.85).offset(i * 2.0)
//                    val o = baseContour.offset(i * -2.0).sub(lineSlider, lineSlider + ( i * (0.03))) //.sub(lineSlider, lineSlider + 0.85).offset(i * 2.0)
//                    val o = baseContour.offset(i * -2.0).sub(lineSlider, lineSlider + ( i * (0.03))) //.sub(lineSlider, lineSlider + 0.85).offset(i * 2.0)
//                    val wave = tan(frameCount * 0.03)
                    val wave = tan((lineSlider + frameCount * 0.00444))
                    val o = baseContour.offset(i * -2.0 + wave * 5.0).sub(lineSlider, lineSlider + (i * 0.03 + wave * 0.01))

//                    val o1 = baseContour.offset((i * 2.0)) //.sub(lineSlider, lineSlider + 0.85).offset(i * 2.0)
                    val o1 = baseContour.offset((i * 2.0)).sub(lineSlider, lineSlider + (i*0.03))
//                    val o0 = secondContour //.sub(lineSlider, lineSlider + 0.85).offset(i * 2.0)
                    contour(o)
                    contour(o1)
//                    drawer.stroke = ColorRGBa.BLACK
//                    contour(o2)

//                    drawer.fill = ColorRGBa.BLACK
                }
                drawer.popTransforms()
//                contour(tShape.closedContours[0].contour.transform(scaleMatrix).sub(animation.lineSlider, animation.lineSlider + 0.3).offset(1.0))
                fill = ColorRGBa.BLACK
//                contour(tShape.closedContours[1].contour.transform(scaleMatrix).sub(animation.lineSlider, animation.lineSlider + 0.3))
            }
            drawer.popTransforms()

            c.draw(drawer)
        }
    }
}