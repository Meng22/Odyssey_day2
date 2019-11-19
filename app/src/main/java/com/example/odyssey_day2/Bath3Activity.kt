package com.example.odyssey_day2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_bath.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Bath3Activity : AppCompatActivity() {
    private val bathAdapter = BathAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bath)

        val peopleList = arrayListOf<String>()
        title = "歡迎光臨好想露仙浴池"

        val fakeList = arrayListOf("載入中...")
        val drinkList = arrayOf("奶油啤酒","南瓜汁","櫻桃糖漿")

        rv_bath.layoutManager = GridLayoutManager(this, 4)
        rv_bath.adapter = bathAdapter
        bathAdapter.update(fakeList)

        API3.apiInterface.showPeople().enqueue(object: Callback<ShowPeople3Response>{
            override fun onFailure(call: Call<ShowPeople3Response>, t: Throwable) {
                println("===================$t")
            }
            override fun onResponse(call: Call<ShowPeople3Response>, response: Response<ShowPeople3Response>) {
                if (response.isSuccessful){
                    val responsebody = response.body()
                    val responseList = responsebody!!.data
                    for (i in 0 until responseList.size){
                        peopleList.add(responseList[i].name)
                    }
                    bathAdapter.update(peopleList)

                }
            }
        })


        btn_dobby.setOnClickListener {
            var position = 0            //設定position
            AlertDialog.Builder(this)
                .setTitle("請問你要喝什麼？")
                .setSingleChoiceItems(drinkList, 0) { _, i ->
                    position = i }
                .setPositiveButton("確認") { dialog, which ->
                    //提醒
                    Toast.makeText(this, "你點的是" + drinkList[position],
                        Toast.LENGTH_SHORT ). show()
                    //發送買飲料API

                }.show()

        }

        btn_leave.setOnClickListener {
            //移除使用者api
            finish()
        }
    }
}
