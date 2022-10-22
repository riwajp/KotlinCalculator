package com.example.calculator

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //text box===========================================================================
        val linear=findViewById<LinearLayout>(R.id.linear1)
        val tv=OutputView(this,60f)
        tv.textSize=40f
        linear.addView(tv)



        //num pad======================================================================
        val items= listOf<String>("C","(",")","Del","9","8","7","+","6","5","4","-","3","2","1","X","0","00",".","/","END")
        val clickMap= mapOf<String,(String)->Unit>("C" to tv::clear,"Del" to tv::del)
        val signs=listOf<String>("+","-","X","/")

        fun bgMapper(string:String):Int{
            when(string){
                "C"->return R.drawable.button_c
                "Del"->return R.drawable.button_del
                in signs->return R.drawable.button
                else->return R.drawable.button
            }

        }


        val table=ButtonsTable(this,items, clickMap,defaultClick =tv::add,::bgMapper)
        val table1=findViewById<TableLayout>(R.id.table1)

        val df=this.getResources().getDisplayMetrics().density;

        val eq_lp=ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(70*df).toInt())
        val eq_btn=DarkButton(this,"=",::println,R.drawable.button_eq)
        eq_btn.setOnClickListener{tv.calculate(tv.text.toString().replace(Regex(" "),"").replace("  ","")
        )}
        table.addView(eq_btn,eq_lp)

        table1.addView(table)

        //===================================================================================

        }

    }









