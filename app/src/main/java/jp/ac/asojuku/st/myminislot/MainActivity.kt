package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.IntegerRes
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var myCoin = 1000
    private var kakeCoin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.kakeCoin=0


        //スタートボタン
        btn_start.setOnClickListener {onStartButtonTapped(it)}
        btn_reset.setOnClickListener {onResetTapped(it) }
        btn_up.setOnClickListener {onKakeUpTapped(it)}
        btn_down.setOnClickListener {onKakeDownTapped(it)}

        btn_start.setOnClickListener{onStartButtonTapped(it)};

    }


//    fun onStartButtonTapped(view: View?) {
//        val intent = Intent(this,GameActivity::class.java)
//        intent.putExtra("KAKE",view?.id)
//            //画面遷移スタート
//        startActivity(intent)
//          // startActivity<GameActivity>("KAKE_COIN" to view?.id)
//    }

    fun onStartButtonTapped(view:View?){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        saveData()
        val intent=Intent(this, GameActivity::class.java)
        startActivity(intent)
}


    fun onKakeUpTapped(view: View?){
        this.myCoin = myCoin-10
        this.kakeCoin = kakeCoin+10
        saveData()
        Save()
    }

    fun onKakeDownTapped(view: View?){
        this.myCoin = myCoin+10
        this.kakeCoin = kakeCoin-10
        saveData()
        Save()
    }
    fun onResetTapped(view: View?){
        this.myCoin = 1000
        this.kakeCoin = 0
       saveData()
        Save()
    }

    override fun onResume() {
        super.onResume()
        this.kakeCoin = 0
        Save()
    }
    //Ankoのやつ
    //startActivity<GameActivity>("GAME" to view?.id)

    fun saveData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val startMyCoin = pref.getInt("START_MY_COIN",0)//最初の持ち金の額
        //val startKakeCoin = pref.getInt("START_KAKE_COIN",0)//最初の持ち金の額

        val editor = pref.edit()
        editor.putInt("START_MY_COIN",myCoin).apply()
        editor.putInt("START_KAKE_COIN",kakeCoin).apply()
    }

    fun Save(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        my_coin.setText(pref.getInt("START_MY_COIN",1000).toString())
        kake_coin.setText(pref.getInt("START_KAKE_COIN",0).toString())
    }
   }


