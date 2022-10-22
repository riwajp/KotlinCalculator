package com.example.calculator

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class ButtonsTable(context:Context,val items:List<String>,val clickMap: Map<String,(String)->Unit> = mapOf(),val defaultClick:(String)->Unit,val bgMapper:(String)->Int):TableLayout(context){

    init {

        //Properties=====================================================================
        var counter=0
        var rows= mutableListOf<TableRow>()
        var row= mutableListOf<TextView>()
        val df=context.getResources().getDisplayMetrics().density;
        val screen_width=(context.getResources().getDisplayMetrics().widthPixels/4).toInt()
        var lp=TableRow.LayoutParams(screen_width,screen_width)
        val row_lp=TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

        //Set 'rows'=====================================================================

        for(number in items) {
            if(counter==4){
                var rowView=TableRow(context)
                for(i in row){
                    rowView.addView(i,lp)
                }
                rows.add(rowView)
                row.clear()
                Log.d("number",number.toString())

                counter=0
            }
            var numberView:DarkButton?
            if(clickMap.containsKey(number)){
                var bg=bgMapper(number)
                 numberView= clickMap[number]?.let { DarkButton(context,number, it,bg) }


            }else{
                var bg=bgMapper(number)
                numberView= DarkButton(context,number,defaultClick,bg)
            }

            if (numberView != null) {
                row.add(numberView)
            }

            counter+=1

        }

       //Add rows to table===============================================================
        for(i in rows){
            addView(i,row_lp)

        }


    }
}