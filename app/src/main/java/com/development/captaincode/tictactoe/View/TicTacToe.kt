package com.development.captaincode.tictactoe.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paintBackground: Paint = Paint()
    private var paintLine: Paint = Paint()

    private var xCoord: Float = -1f
    private var yCoord: Float = -1f
    private var radius = 80f


    private var topRow = -1f
    private var leftCol = -1f

    //mid section
    private var midRow = -1f
    private var midCol = -1f

    //right side
    private var rightCol = -1f
    private var bottomRow = -1f


    private var topRowThreshold = -1f
    private var bottomRowThreshold = -1f

    private var leftColThreshold = -1f
    private var rightColThreshold = -1f

    init {
        paintBackground.color = Color.BLACK
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 8f
    }


    override fun onDraw(canvas: Canvas?) {

        // vertical lines
        val line1_vert_origin = width.toFloat()/3
        val line2_vert_origin = line1_vert_origin * 2

        //horizontal lines
        val line1_horiz_origin = height.toFloat()/3
        val line2_horiz_origin = line1_horiz_origin *2


        val height = height.toFloat()
        val width = width.toFloat()

        canvas?.drawRect(0f, 0f, width, height, paintBackground)

        //vertical l    ines
        canvas?.drawLine(line1_vert_origin, 0f   , line1_vert_origin, height, paintLine)
        canvas?.drawLine(line2_vert_origin, 0f  , line2_vert_origin, height, paintLine)

        //horizontal lines
        canvas?.drawLine(0f, line1_horiz_origin, width, line1_horiz_origin, paintLine)
        canvas?.drawLine(0f, line2_horiz_origin, width, line2_horiz_origin, paintLine)


        topRow = ((height/3)/2)
        leftCol = ((width/3)/2)

        midRow = height/2

        midCol = (width/2).toFloat()

        //right side
        rightCol = width - leftCol
        bottomRow = height - topRow


        topRowThreshold = height/3
        bottomRowThreshold = 2 * (height/3)

        leftColThreshold = width/3
        rightColThreshold = (2* leftColThreshold)


        if (xCoord != -1f && yCoord != -1f) {
            canvas?.drawCircle(xCoord, yCoord, radius, paintLine)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            findCenter(event.x, event.y)
            invalidate()
        }
        return true
    }
    fun findCenter(xVal:Float, yVal: Float){

        //Vertical for upper third

        if (xVal < leftColThreshold) {
            xCoord = leftCol
            findYCoordinate(yVal)
        }else if ((xVal> leftColThreshold) and (xVal < rightColThreshold)){
            xCoord = midCol
            findYCoordinate(yVal)
        }else if (xVal > rightColThreshold){
            xCoord = rightCol
            findYCoordinate(yVal)
        }

    }
    fun findYCoordinate(yVal:Float){
        if (yVal <topRowThreshold   ) {
            yCoord = topRow
        } else if ((yVal > topRowThreshold) and (yVal < bottomRowThreshold)) {
            yCoord = midRow
        } else if (yVal > bottomRowThreshold) {
            yCoord = bottomRow
        }
    }
}
