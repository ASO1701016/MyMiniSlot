package jp.ac.asojuku.st.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_game.view.*
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity() {
    //スロット
    var slot: ImageView? = null;
    //val slotB = slot2
    //val slotC = slot3

    //持ち金、掛け金
    //val coin =Integer.parseInt(my_coin.toString());
    //val kake = Integer.parseInt(kake_coin.toString());
    //val coin2 =Integer.parseInt(my_coin2.toString());
    //val kake2 = Integer.parseInt(kake_coin2.toString());

    var bai = 0

    private var coin = 1000
    private var kake = 0
    //private val coin2 = 0
    //private val kake2 = 0
    var slotrt1: Int = 999
    var slotrt2: Int = 999
    var slotrt3: Int = 999
    // var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        var startCoin = pref.getInt("START_MY_COIN", coin)
        val startKakeCoin = pref.getInt("START_KAKE_COIN", kake)
        my_coin2.setText(startCoin.toString())
        kake_coin2.setText(startKakeCoin.toString())
        // cnt = 0


        //共有プリファレンス初期化
        // val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val editor = pref.edit()
        //editor.clear().apply()
    }

    override fun onResume() {
        super.onResume()
        val id = this.intent.getIntExtra("", 0)


        //戻るボタンのクリック反応(リスナーとコールバックメソッド)を設定する
        back_button.setOnClickListener { finish() }
//        slot1.setOnClickListener { Slot(it, 1) }
//        slot2.setOnClickListener { Slot(it, 2) }
//        slot3.setOnClickListener { Slot(it, 3) }

        slot1.setOnClickListener { Slot(slot1) }
        slot2.setOnClickListener { Slot(slot2) }
        slot3.setOnClickListener { Slot(slot3) }
    }

    private fun Slot(imageView: ImageView) {
        when(imageView) {
            slot1 -> if (slotrt1 != 999) return
            slot2 -> if (slotrt2 != 999) return
            slot3 -> if (slotrt3 != 999) return
        }

        var slot = (Math.random() * 9).toInt()
        when (slot) {
            0 -> imageView.setImageResource(R.drawable.bar)
            1 -> imageView.setImageResource(R.drawable.bigwin)
            2 -> imageView.setImageResource(R.drawable.cherry)
            3 -> imageView.setImageResource(R.drawable.grape)
            4 -> imageView.setImageResource(R.drawable.lemon)
            5 -> imageView.setImageResource(R.drawable.orange)
            6 -> imageView.setImageResource(R.drawable.seven)
            7 -> imageView.setImageResource(R.drawable.waltermelon)
            8 -> imageView.setImageResource(R.drawable.banana)
        }
        when(imageView) {
            slot1 -> slotrt1 = slot
            slot2 -> slotrt2 = slot
            slot3 -> slotrt3 = slot
        }
        // cnt = cnt + 1
        if (slotrt1 != 999 && slotrt2 != 999 && slotrt3 != 999) {
            Bai()
            Kei()
        }
    }

    /*
    private fun Slot(view: View?, int: Int) {
        var imageView: ImageView = view as ImageView;
        //ランダムなスロット値:
        val slot = (Math.random() * 9).toInt()

        when (int) {
            1 -> slotrt1 = slot
            2 -> slotrt2 = slot
            3 -> slotrt3 = slot
        }

        when (slot) {
            0 -> imageView.setImageResource(R.drawable.bar)
            1 -> imageView.setImageResource(R.drawable.bigwin)
            2 -> imageView.setImageResource(R.drawable.cherry)
            3 -> imageView.setImageResource(R.drawable.grape)
            4 -> imageView.setImageResource(R.drawable.lemon)
            5 -> imageView.setImageResource(R.drawable.orange)
            6 -> imageView.setImageResource(R.drawable.seven)
            7 -> imageView.setImageResource(R.drawable.waltermelon)
            8 -> imageView.setImageResource(R.drawable.banana)
        }
        cnt = cnt + 1
        if (cnt >= 3) {
            Bai()
            Kei()
        }
    }
    */

    fun Bai() {
        if (slotrt1 == slotrt2 && slotrt1 == slotrt3) {
            bai = 5
        } else if (slotrt1 == slotrt2 || slotrt2 == slotrt3 || slotrt3 == slotrt1) {
            bai = 3
        }
    }

    fun Kei() {
        //var rt = kake*bai
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var k: Int = pref.getInt("START_KAKE_COIN", 0)
        //coin=coin+rt
        var n: Int = pref.getInt("START_MY_COIN", coin)
        var rt = k * bai
        n = rt + n
        pref.edit().putInt("START_MY_COIN", n).apply()
        my_coin2.setText(n.toString())


    }

}


