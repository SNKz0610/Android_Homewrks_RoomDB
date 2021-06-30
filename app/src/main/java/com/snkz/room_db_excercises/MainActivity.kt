package com.snkz.room_db_excercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snkz.room_db_excercises.adapter.EmailAdapter
import com.snkz.room_db_excercises.database.EmailResponsitory
import com.snkz.room_db_excercises.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var responsitory: EmailResponsitory
    private lateinit var adapter : EmailAdapter

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        adapter = EmailAdapter(this)
        mainBinding.revMainEmail.adapter = adapter
        mainBinding.revMainEmail.layoutManager = LinearLayoutManager(applicationContext)

        responsitory = EmailResponsitory(application)
        responsitory.getAllEmail().observe(this){
            if (it != null){
                for (email in it){
                    adapter.arrEmail.add(email)
                }

                adapter.notifyDataSetChanged()
            }
        }

    }
}