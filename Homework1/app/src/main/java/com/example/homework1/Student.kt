package com.example.homework1

class Student(val id:Int, val name:String, val surname:String, var grade:String, val birthYear:String){
    override fun toString() = "Student $id : $name $surname , class: $grade , birth year: $birthYear"
}


