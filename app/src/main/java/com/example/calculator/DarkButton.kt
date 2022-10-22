package com.example.calculator

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class DarkButton(context:Context,  text:String, val onClick:(text:String)->Unit,bg:Int):AppCompatButton(context){


      init {
          //super.setText(text)

          this.text=text
          textSize= 30F
          setTypeface(Typeface.create(Typeface.SANS_SERIF,300,false))
          //setBackgroundColor(bg)
          setBackgroundResource(bg)
          setTextColor(resources.getColor(R.color.lightblack))
          setOnClickListener{onClick(text)}
          stateListAnimator=null
      }







}