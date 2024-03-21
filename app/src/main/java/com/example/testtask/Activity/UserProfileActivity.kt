package com.example.testtask.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.example.testtask.APi.DataUserModel.DataModel
import com.example.testtask.R

class UserProfileActivity : ComponentActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_activity)
        val intent = intent
        val person: DataModel = intent.getSerializableExtra("person_id") as DataModel

        val fullNameTextView: TextView = findViewById(R.id.full_name)
        val genderTextView: TextView = findViewById(R.id.gender)
        val dateOfBirthTextView: TextView = findViewById(R.id.date_of_birth)
        val addressTextView: TextView = findViewById(R.id.address)
        val phoneNumberTextView: TextView = findViewById(R.id.phone_number)
        val emailTextView: TextView = findViewById(R.id.email)
        val usernameTextView: TextView = findViewById(R.id.username)
        val uuidTextView: TextView = findViewById(R.id.login)
        val passwordTextView:TextView = findViewById(R.id.password)
        val saltTextView:TextView = findViewById(R.id.salt)
        val nationalityTextView: TextView = findViewById(R.id.nationality)
        val profilePictureImageView: ImageView = findViewById(R.id.profile_picture)
        val coordinatesTextView: TextView = findViewById(R.id.coordinates)

        fullNameTextView.text = "${person.results[0].name.title} ${person.results[0].name.first}" +
                " ${person.results[0].name.last}"
        genderTextView.text = person.results[0].gender
        dateOfBirthTextView.text = person.results[0].dob.date
        addressTextView.text = "${person.results[0].location.street.number} ${person.results[0].location.street.name}," +
                " ${person.results[0].location.city}, ${person.results[0].location.country}"
        phoneNumberTextView.text = person.results[0].phone
        emailTextView.text = person.results[0].email
        usernameTextView.text = person.results[0].login.username
        uuidTextView.text = person.results[0].login.uuid
        passwordTextView.text = person.results[0].login.password
        saltTextView.text = person.results[0].login.salt
        nationalityTextView.text = person.results[0].nat
        coordinatesTextView.text = "${person.results[0].location.coordinates.latitude}, ${person.results[0].location.coordinates.longitude}"


        // Загрузка изображения профиля с помощью Glide
        Glide.with(this)
            .load(person.results[0].picture.thumbnail)
            .into(profilePictureImageView)
    }



    private var x1: Float = 0f
    private var x2: Float = 0f
    private var y1: Float = 0f
    private var y2: Float = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                y2 = event.y
                val deltaX: Float = x2 - x1
                val deltaY: Float = y2 - y1
                if (Math.abs(deltaX) > SWIPE_THRESHOLD && Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > 0) {
                        finish()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
    }
}
