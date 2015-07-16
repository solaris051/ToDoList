package com.culturall.todolist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by kyshynevskyi on 15.07.2015.
 */
public class ToDoItemView extends TextView {

    //resources needed to draw the View
    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;

    //constructor
    public ToDoItemView(Context context, AttributeSet ats, int ds) {
        super(context, ats, ds);
        init();
    }

    //constructor
    public ToDoItemView (Context context, AttributeSet ats) {
        super(context, ats);
        init();
    }

    //constructor
    public ToDoItemView (Context context) {
        super(context);
        init();
    }

    private void init() {
        //get the reference to resources table
        Resources myResources = getResources();

        //Create the paint brushes to use in the onDraw method
        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(myResources.getColor(R.color.notepad_margin));
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(myResources.getColor(R.color.notepad_lines));

        //get the paper background color and the margin width
        paperColor = myResources.getColor(R.color.noteepad_paper);
        margin = myResources.getDimension(R.dimen.notepad_margin);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //color the paper
        canvas.drawColor(paperColor);

        //draw ruled lines
        canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        //move the text across from teh margin
        canvas.save();
        canvas.translate(0, margin);

        //Use the base TextView to render the text
        super.onDraw(canvas);
        canvas.restore();
    }
}
