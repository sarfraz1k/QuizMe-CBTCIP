package com.example.quizme

import android.content.IntentSender.OnFinished
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizme.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(),View.OnClickListener {

    companion object{
        var questionModelList: List<QuestionModel> = listOf()
        var time:String=""
    }
    var currentQuestionIndex = 0;
    var selectedAnswer=""
    var score=0;


    lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestion()
        startTimer()

    }

    private fun startTimer() {
        val totalTimeInMillis=time.toInt()*60*1000L
        object :CountDownTimer(totalTimeInMillis, 1000L){
            override fun onTick(millisUntilFinished:Long) {
                val seconds=millisUntilFinished/1000
                val minutes= seconds/60
                val remainingSeconds= seconds%60
                binding.timerIndiacatorTextview.text=String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                //finish
            }

        }.start()
    }

    private fun loadQuestion(){

        selectedAnswer=""
        if(currentQuestionIndex== questionModelList.size){
            finishQuiz()
            return
        }
        binding.apply {
            questionIndicatorTextview.text="Question ${currentQuestionIndex+1}/${questionModelList.size}"
            questionProgressIndicator.progress=
                (currentQuestionIndex.toFloat()/ questionModelList.size.toFloat()*100).toInt()
            questionTextview.text= questionModelList[currentQuestionIndex].question
            btn0.text= questionModelList[currentQuestionIndex].options[0]
            btn1.text= questionModelList[currentQuestionIndex].options[1]
            btn2.text= questionModelList[currentQuestionIndex].options[2]
            btn3.text= questionModelList[currentQuestionIndex].options[3]
        }
    }

    override fun onClick(view: View?) {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }
        val clickedBtn = view as Button
        if (clickedBtn.id==R.id.next_btn){
            if(selectedAnswer== questionModelList[currentQuestionIndex].correct){
                score++
                Log.i("Score of quiz", score.toString())
            }
            currentQuestionIndex++
            loadQuestion()
        }else{
            selectedAnswer=clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.orange))
        }
    }
    private fun finishQuiz(){


    }

}