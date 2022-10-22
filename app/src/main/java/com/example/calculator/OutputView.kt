package com.example.calculator

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration

class OutputView(context:Context,textSize:Float=60f):AppCompatTextView(context) {
    val font=Typeface.create(Typeface.SANS_SERIF,40,false)




    fun clear(string:String=""){
        text=""
    }
    fun del(string:String=""){
        text=text.dropLast(1)
    }
    fun add(string: String=""){
        val signs=listOf<String>("+","-","X","/")
        if(string in signs){
            text=text.toString()+" "+string+" "

        }else {
            text = text.toString() + string
        }

    }
    fun calculate( string_temp:String){
        if(string_temp.length>0) {

            var result = 0f
            var lastIndex = 0
            var calcArray = mutableListOf<String>()
            var string = ""
            if (string_temp[string_temp.length - 1].toString() in signs) {
                string = string_temp.substring(0, string_temp.length)
            } else {
                string = string_temp

            }
            string=string.filter { l->l!=='('&&l!==')' }

            for (i in string.indices) {

                if (i > 0 && string[i].toString() in signs)  {
                    if (string[i - 1].toString() !in signs ) {
                        calcArray.add(string.substring(lastIndex, i))
                        if(i+1<string.length) {
                            calcArray.add(string[i].toString())
                        }

                            lastIndex = i + 1

                    }


                }

            }
            if(lastIndex<string.length) {
                calcArray.add(string.substring(lastIndex, string.length))
            }

            //===============================================================
            var signCorectedCalcArray = mutableListOf<String>()
            Log.d("calcArray", calcArray.toString())
            for (i in calcArray.indices) {
                if (i > 0 && calcArray[i - 1] in signs ) {
                    if (i > 1 && calcArray[i - 2] in signs) {
                        signCorectedCalcArray[i - 1] += calcArray[i]
                        Log.d("Signed", calcArray[i] + calcArray[i + 1])

                    } else {
                        signCorectedCalcArray.add(calcArray[i])
                    }
                } else {
                    signCorectedCalcArray.add(calcArray[i])

                }


            }
            signCorectedCalcArray= signCorectedCalcArray.filter{ e->e!==""} as MutableList<String>

            Log.d("Sign Corrected", signCorectedCalcArray.toString())


            //==============================================================
            result = calcArray[0].toFloat()
            for (i in calcArray.indices) {
                if(i+1<calcArray.size) {
                    Log.d("calcArray",calcArray.toString())


                    when (calcArray[i]) {
                        "+" -> result += calcArray[i + 1].toFloat()
                        "X" -> result *= calcArray[i + 1].toFloat()
                        "/" -> result /= calcArray[i + 1].toFloat()
                        "-" -> result -= calcArray[i + 1].toFloat()
                    }

                }
            }
            if (result % 1 == 0f) {
                text = result.toInt().toString()
            } else {
                text = result.toString()
            }
        }
    }
    var signs= listOf<String>("+","-","X","/")
    override fun setText(text: CharSequence, type: BufferType?) {
        var currentText=text.replace(Regex(" "),"")
        if(text.length>0){
             currentText=currentText[currentText.length-1].toString()

        }



        val stripped_text=this.text.replace(Regex(" "),"")

        val len=stripped_text.length

        if(len>0 && currentText.toString() in signs) {
            Log.d("Stripped Text","stripped_text[len - 1].toString()")

            if ((currentText=="X" || currentText=="/") && stripped_text[len-1].toString() in signs) {

            } else if (len>=2 && (stripped_text[len-1] == '+' || stripped_text[len-1] == '-' )&& stripped_text[len-2].toString() in signs
            ) {


            } else {
                super.setText(text, type)

            }
        }else{
            super.setText(text, type)
        }

    }

    init{
        setTypeface(font)
        letterSpacing=0.05f
        setPadding(50,50,50,50)



    }



}