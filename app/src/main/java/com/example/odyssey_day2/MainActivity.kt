package com.example.odyssey_day2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "選擇羅馬浴場"
        val username = ed_user.text.toString()


        API1.apiInterface.showBath1().enqueue(object: Callback<ShowBath1Response>{
            override fun onFailure(call: Call<ShowBath1Response>, t: Throwable) {
                println("==============$t")
            }
            override fun onResponse(call: Call<ShowBath1Response>, response: Response<ShowBath1Response>) {
                if (response.isSuccessful){
                    val responseBody1 = response.body()
                    println("==============$responseBody1")
                    val bath1_name = responseBody1!!.poolName
                    val bath1_quantity = responseBody1.peopleAmount
                    val bath1_limit = responseBody1.upperLimit
                    val bath1_intro = responseBody1.introduction

                    tv_name1.setText("$bath1_name")
                    tv_number1.setText("${bath1_quantity}/${bath1_limit}")
                    tv_intro1.setText("$bath1_intro")

                }
            }
        })
        API3.apiInterface.showBath3().enqueue(object: Callback<ShowBath3Response>{
            override fun onFailure(call: Call<ShowBath3Response>, t: Throwable) {
                println("==============$t")
            }
            override fun onResponse(call: Call<ShowBath3Response>, response: Response<ShowBath3Response>) {
                if (response.isSuccessful){
                    val responseBody3 = response.body()
                    val bath3_name = responseBody3!!.name
                    val bath3_quantity = responseBody3.quantity
                    val bath3_limit = responseBody3.limit
                    val bath3_intro = responseBody3.introduction

                    tv_name3.setText("$bath3_name")
                    tv_number3.setText("${bath3_quantity}/${bath3_limit}")
                    tv_intro3.setText("$bath3_intro")
                }
            }
        })

        btn_entry1.setOnClickListener {
            if (ed_user.text.isNullOrEmpty()){
                Toast.makeText(this, "請登記姓名", Toast.LENGTH_SHORT).show()
            }
            else{
                    API1.apiInterface.entryBath1(EntryRequest(ed_user.text.toString())).enqueue(object : Callback<Entry1Response>{
                        override fun onFailure(call: Call<Entry1Response>, t: Throwable) {
                            println("=============$t")
                        }
                        override fun onResponse(call: Call<Entry1Response>, response: Response<Entry1Response>) {
                            if (response.isSuccessful){
                                println("=============$response")
                                val intent = Intent(this@MainActivity, Bath3Activity::class.java)
                                startActivityForResult(intent, 1)

                            }
//                            else if (response.code() ==409 ){
//                                val responsebody = response.body()
//                                val message = responsebody!!.message
//                                Toast.makeText(this@MainActivity, "$message", Toast.LENGTH_SHORT).show()
//                            }
                        }
                    })
                }
//            }
        }

        btn_entry3.setOnClickListener {
            if (ed_user.text.isNullOrEmpty()){
                    Toast.makeText(this, "請登記姓名", Toast.LENGTH_SHORT).show()
                } else{
                    API3.apiInterface.entryBath3(EntryRequest(ed_user.text.toString())).enqueue(object : Callback<Entry3Response>{
                        override fun onFailure(call: Call<Entry3Response>, t: Throwable) {
                            println("=============$t")
                        }
                        override fun onResponse(call: Call<Entry3Response>, response: Response<Entry3Response>) {
                            if (response.isSuccessful){
                                val responsebody = response.body()
                                val message = responsebody!!.message
                                val name = responsebody.name
                                val intent = Intent(this@MainActivity, Bath3Activity::class.java)
                                intent.putExtra("name", name)
                                startActivityForResult(intent, 3)
                                Toast.makeText(this@MainActivity, "$message", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
        }

    }

}
