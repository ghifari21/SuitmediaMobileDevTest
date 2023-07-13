package com.gosty.suitmediamobiledevtest.ui.first

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.gosty.suitmediamobiledevtest.R
import com.gosty.suitmediamobiledevtest.core.utils.Utils
import com.gosty.suitmediamobiledevtest.databinding.ActivityFirstBinding
import com.gosty.suitmediamobiledevtest.ui.second.SecondActivity
import java.io.File

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    private var getFile: File? = null

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it?.data?.data as Uri
            val myFile = Utils.uriToFile(selectedImg, this@FirstActivity)
            getFile = myFile
            binding.ivAvatar.setImageURI(selectedImg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.apply {
            ivAvatar.setOnClickListener {
                setAvatar()
            }

            btnCheck.setOnClickListener {
                checkPalindromeButtonAction()
            }

            btnNext.setOnClickListener {
                nextButtonAction()
            }
        }
    }

    private fun nextButtonAction() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(
                this@FirstActivity,
                getString(R.string.empty_field),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val intent = Intent(this@FirstActivity, SecondActivity::class.java)
        intent.putExtra(SecondActivity.STRING_EXTRA, name)
        startActivity(intent)
    }

    private fun setAvatar() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun checkPalindromeButtonAction() {
        val text = binding.etPalindrome.text.toString().trim()

        if (text.isEmpty()) {
            Toast.makeText(
                this@FirstActivity,
                getString(R.string.empty_field),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val title = if (isPalindrome(text)) {
            R.string.is_palindrome
        } else {
            R.string.not_palindrome
        }

        AlertDialog.Builder(this@FirstActivity).apply {
            setTitle(getString(title))
            setPositiveButton(getString(R.string.OK)) { _, _ ->
                // Do nothing
            }
            create()
            show()
        }
    }

    private fun isPalindrome(s: String) =
        s.filter {
            it.isLetterOrDigit()
        }.run {
            equals(reversed(), true)
        }
}