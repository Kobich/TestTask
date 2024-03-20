package com.example.testtask.SQLiteBaseHelper

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.testtask.APi.DataUserModel.Coordinates
import com.example.testtask.APi.DataUserModel.DataModel
import com.example.testtask.APi.DataUserModel.Dob
import com.example.testtask.APi.DataUserModel.Id
import com.example.testtask.APi.DataUserModel.Info
import com.example.testtask.APi.DataUserModel.Location
import com.example.testtask.APi.DataUserModel.Login
import com.example.testtask.APi.DataUserModel.Name
import com.example.testtask.APi.DataUserModel.Picture
import com.example.testtask.APi.DataUserModel.Registered
import com.example.testtask.APi.DataUserModel.Result
import com.example.testtask.APi.DataUserModel.Street
import com.example.testtask.APi.DataUserModel.Timezone

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase"
        private const val TABLE_USERS = "users"
        private const val KEY_ID = "_id"
        private const val KEY_FULL_NAME = "full_name"
        private const val KEY_GENDER = "gender"
        private const val KEY_TITLE = "title"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_STREET_NUMBER = "street_number"
        private const val KEY_STREET_NAME = "street_name"
        private const val KEY_CITY = "city"
        private const val KEY_COUNTRY = "country"
        private const val KEY_COORDINATES_LATITUDE = "coordinates_latitude"
        private const val KEY_COORDINATES_LONGITUDE = "coordinates_longitude"
        private const val KEY_EMAIL = "email"
        private const val KEY_USERNAME = "username"
        private const val KEY_UUID = "uuid"
        private const val KEY_PASSWORD = "password"
        private const val KEY_SALT = "salt"
        private const val KEY_DATE_OF_BIRTH = "date_of_birth"
        private const val KEY_PHONE_NUMBER = "phone_number"
        private const val KEY_NATIONALITY = "nationality"
        private const val KEY_PICTURE_URL = "picture_url"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USERS_TABLE = ("CREATE TABLE $TABLE_USERS(" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_FULL_NAME TEXT," +
                "$KEY_GENDER TEXT," +
                "$KEY_TITLE TEXT," +
                "$KEY_FIRST_NAME TEXT," +
                "$KEY_LAST_NAME TEXT," +
                "$KEY_STREET_NUMBER INTEGER," +
                "$KEY_STREET_NAME TEXT," +
                "$KEY_CITY TEXT," +
                "$KEY_COUNTRY TEXT," +
                "$KEY_COORDINATES_LATITUDE TEXT," +
                "$KEY_COORDINATES_LONGITUDE TEXT," +
                "$KEY_EMAIL TEXT," +
                "$KEY_USERNAME TEXT," +
                "$KEY_UUID TEXT," +
                "$KEY_PASSWORD TEXT," +
                "$KEY_SALT TEXT," +
                "$KEY_DATE_OF_BIRTH TEXT," +
                "$KEY_PHONE_NUMBER TEXT," +
                "$KEY_NATIONALITY TEXT," +
                "$KEY_PICTURE_URL TEXT)")
        try {
            db.execSQL(CREATE_USERS_TABLE)
            Log.d("DatabaseHelper", "Table created successfully")
        } catch (e: SQLException) {
            Log.e("DatabaseHelper", "Error creating table: ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: DataModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(
            KEY_FULL_NAME,
            "${user.results[0].name.title} ${user.results[0].name.first} ${user.results[0].name.last}"
        )
        values.put(KEY_GENDER, user.results[0].gender)
        values.put(KEY_TITLE, user.results[0].name.title)
        values.put(KEY_FIRST_NAME, user.results[0].name.first)
        values.put(KEY_LAST_NAME, user.results[0].name.last)
        values.put(KEY_STREET_NUMBER, user.results[0].location.street.number)
        values.put(KEY_STREET_NAME, user.results[0].location.street.name)
        values.put(KEY_CITY, user.results[0].location.city)
        values.put(KEY_COUNTRY, user.results[0].location.country)
        values.put(KEY_COORDINATES_LATITUDE, user.results[0].location.coordinates.latitude)
        values.put(KEY_COORDINATES_LONGITUDE, user.results[0].location.coordinates.longitude)
        values.put(KEY_EMAIL, user.results[0].email)
        values.put(KEY_USERNAME, user.results[0].login.username)
        values.put(KEY_UUID, user.results[0].login.uuid)
        values.put(KEY_PASSWORD, user.results[0].login.password)
        values.put(KEY_SALT, user.results[0].login.salt)
        values.put(KEY_DATE_OF_BIRTH, user.results[0].dob.date)
        values.put(KEY_PHONE_NUMBER, user.results[0].phone)
        values.put(KEY_NATIONALITY, user.results[0].nat)
        values.put(KEY_PICTURE_URL, user.results[0].picture.medium)

        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun getAllUsers(): List<DataModel> {
        val userList = mutableListOf<DataModel>()
        val selectQuery = "SELECT * FROM $TABLE_USERS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val fullNameIndex = cursor.getColumnIndex(KEY_FULL_NAME)
                val fullName = if (fullNameIndex >= 0) cursor.getString(fullNameIndex) else null

                val genderIndex = cursor.getColumnIndex(KEY_GENDER)
                val gender = if (genderIndex >= 0) cursor.getString(genderIndex) else null

                val titleIndex = cursor.getColumnIndex(KEY_TITLE)
                val title = if (titleIndex >= 0) cursor.getString(titleIndex) else null

                val firstNameIndex = cursor.getColumnIndex(KEY_FIRST_NAME)
                val firstName = if (firstNameIndex >= 0) cursor.getString(firstNameIndex) else null

                val lastNameIndex = cursor.getColumnIndex(KEY_LAST_NAME)
                val lastName = if (lastNameIndex >= 0) cursor.getString(lastNameIndex) else null

                val streetNumberIndex = cursor.getColumnIndex(KEY_STREET_NUMBER)
                val streetNumber = if (streetNumberIndex >= 0) cursor.getInt(streetNumberIndex) else -1

                val streetNameIndex = cursor.getColumnIndex(KEY_STREET_NAME)
                val streetName = if (streetNameIndex >= 0) cursor.getString(streetNameIndex) else null

                val cityIndex = cursor.getColumnIndex(KEY_CITY)
                val city = if (cityIndex >= 0) cursor.getString(cityIndex) else null

                val countryIndex = cursor.getColumnIndex(KEY_COUNTRY)
                val country = if (countryIndex >= 0) cursor.getString(countryIndex) else null

                val coordinatesLatitudeIndex = cursor.getColumnIndex(KEY_COORDINATES_LATITUDE)
                val coordinatesLatitude = if (coordinatesLatitudeIndex >= 0) cursor.getString(coordinatesLatitudeIndex) else null

                val coordinatesLongitudeIndex = cursor.getColumnIndex(KEY_COORDINATES_LONGITUDE)
                val coordinatesLongitude = if (coordinatesLongitudeIndex >= 0) cursor.getString(coordinatesLongitudeIndex) else null

                val coordinates = Coordinates(coordinatesLatitude ?: "", coordinatesLongitude ?: "")

                val emailIndex = cursor.getColumnIndex(KEY_EMAIL)
                val email = if (emailIndex >= 0) cursor.getString(emailIndex) else null

                val usernameIndex = cursor.getColumnIndex(KEY_USERNAME)
                val username = if (usernameIndex >= 0) cursor.getString(usernameIndex) else null

                val uuidIndex = cursor.getColumnIndex(KEY_UUID)
                val uuid = if (uuidIndex >= 0) cursor.getString(uuidIndex) else null

                val passwordIndex = cursor.getColumnIndex(KEY_PASSWORD)
                val password = if (passwordIndex >= 0) cursor.getString(passwordIndex) else null

                val saltIndex = cursor.getColumnIndex(KEY_SALT)
                val salt = if (saltIndex >= 0) cursor.getString(saltIndex) else null

                val dateOfBirthIndex = cursor.getColumnIndex(KEY_DATE_OF_BIRTH)
                val dateOfBirth = if (dateOfBirthIndex >= 0) cursor.getString(dateOfBirthIndex) else null

                val phoneNumberIndex = cursor.getColumnIndex(KEY_PHONE_NUMBER)
                val phoneNumber = if (phoneNumberIndex >= 0) cursor.getString(phoneNumberIndex) else null

                val nationalityIndex = cursor.getColumnIndex(KEY_NATIONALITY)
                val nationality = if (nationalityIndex >= 0) cursor.getString(nationalityIndex) else null

                val pictureUrlIndex = cursor.getColumnIndex(KEY_PICTURE_URL)
                val pictureUrl = if (pictureUrlIndex >= 0) cursor.getString(pictureUrlIndex) else null

                val name = Name(title ?: "", firstName ?: "", lastName ?: "")
                val street = Street(streetNumber, streetName ?: "")

                val location = Location(street, city ?: "", "", country ?: "", 0, coordinates, Timezone("", ""))
                val login = Login(uuid ?: "", username ?: "", password ?: "", salt ?: "", "", "", "")
                val dob = Dob(dateOfBirth ?: "", 0)
                val picture = Picture("", "", pictureUrl ?: "")

                val result = Result(gender ?: "", name, location, email ?: "", login, dob, Registered("", 0), phoneNumber ?: "", "", Id("", ""), picture, nationality ?: "")
                val info = Info("", 0, 0, "") // Not stored in the database

                val dataModel = DataModel(listOf(result), info)
                userList.add(dataModel)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return userList
    }
}
