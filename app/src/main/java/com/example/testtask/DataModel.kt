package com.example.testtask

import java.io.Serializable

data class DataModel(
    var results: List<Result>,
    var info: Info
): Serializable

data class Result(
    var gender: String,
    var name: Name,
    var location: Location,
    var email: String,
    var login: Login,
    var dob: Dob,
    var registered: Registered,
    var phone: String,
    var cell: String,
    var id: Id,
    var picture: Picture,
    var nat: String
): Serializable

data class Name(
    var title: String,
    var first: String,
    var last: String
): Serializable

data class Location(
    var street: Street,
    var city: String,
    var state: String,
    var country: String,
    var postcode: Int,
    var coordinates: Coordinates,
    var timezone: Timezone
): Serializable

data class Street(
    var number: Int,
    var name: String
): Serializable

data class Coordinates(
    var latitude: String,
    var longitude: String
): Serializable

data class Timezone(
    var offset: String,
    var description: String
): Serializable

data class Login(
    var uuid: String,
    var username: String,
    var password: String,
    var salt: String,
    var md5: String,
    var sha1: String,
    var sha256: String
): Serializable

data class Dob(
    var date: String,
    var age: Int
): Serializable

data class Registered(
    var date: String,
    var age: Int
): Serializable

data class Id(
    var name: String,
    var value: String
): Serializable

data class Picture(
    var large: String,
    var medium: String,
    var thumbnail: String
): Serializable

data class Info(
    var seed: String,
    var results: Int,
    var page: Int,
    var version: String
): Serializable


