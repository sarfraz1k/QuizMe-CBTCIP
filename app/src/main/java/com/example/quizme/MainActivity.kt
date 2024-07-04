package com.example.quizme

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList:MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        quizModelList= mutableListOf()
        getDataFromFirebase()



    }
    private fun setupRecyclerView(){
        adapter =QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter= adapter

    }
    private fun getDataFromFirebase(){
        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("what is android os?", mutableListOf("Language","OS","Product","Node"),"OS"))


        quizModelList.add(QuizModel("1", "programing", "All the basic programming", "10", listQuestionModel))

        setupRecyclerView()
    }

}