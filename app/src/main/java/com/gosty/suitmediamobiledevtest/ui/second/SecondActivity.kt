package com.gosty.suitmediamobiledevtest.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.gosty.suitmediamobiledevtest.R
import com.gosty.suitmediamobiledevtest.databinding.ActivitySecondBinding
import com.gosty.suitmediamobiledevtest.ui.third.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    /**
     * to get result from third activity
     */
    private val launcherIntentUser = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == USER_RESULT) {
            val data = it?.data?.getStringExtra(RESULT_DATA) as String
            binding.tvSelectedUsername.text = data
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        /**
         * get name from first activity
         */
        val name = intent.getStringExtra(STRING_EXTRA)

        /**
         * setup action bar title and up button
         */
        supportActionBar?.apply {
            title = getString(R.string.second_screen)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.apply {
            tvUsername.text = name

            btnChooseUser.setOnClickListener {
                chooseUserButtonAction()
            }
        }
    }

    private fun chooseUserButtonAction() {
        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        launcherIntentUser.launch(intent)
    }

    companion object {
        const val STRING_EXTRA = "string"
        const val RESULT_DATA = "result_data"
        const val USER_RESULT = 200
    }
}