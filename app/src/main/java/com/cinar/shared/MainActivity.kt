package com.cinar.shared

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cinar.shared.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   lateinit var sharedPref:SharedPreferences
    var ageFromPref : Int?  = null
    var nameFromPref   : String? = null
    var countryFromPref : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Shared Preferences xml dosyasıdır ve xml ile veri tutar düşük boyutta
        //Key - value ile değerleri tutar

        sharedPref = this.getSharedPreferences("com.cinar.shared", MODE_PRIVATE)

        ageFromPref = sharedPref.getInt("age", -1)
        nameFromPref = sharedPref.getString("name", "")
        countryFromPref = sharedPref.getString("country", "")
        if (ageFromPref == -1 && nameFromPref == "" && countryFromPref == "") {
            binding.resultText.text = "Your : "
        } else {
            binding.resultText.text =
                "Your name : ${nameFromPref}    your age : ${ageFromPref}    your country:${countryFromPref}"

        }
    }



    fun Save(view: View){
        val name  = binding.nameText.text.toString()
        val myAge = binding.ageText.text.toString().toIntOrNull()
        val country = binding.countryText.text.toString()
     //   binding.resultText.text = "Your age : ${myAge}"
       // binding.resultText.text = "Your age : "+myAge

        if (myAge !=null && name!=null && country!=null){

           binding.resultText.text= "Your name : ${name}    your age : ${myAge}    your country:${country}"
            sharedPref.edit().putInt("age",myAge).apply()
            sharedPref.edit().putString("name",name).apply()
            sharedPref.edit().putString("country",country).apply()
            //.commit kayıt edildi mi edilmedi mi
            //.apply kayıt edildi işlem bitti
        }
        else{
            binding.resultText.text ="Enter your editTexts !!"
        }

    }

    fun Delete(view:View){
        ageFromPref = sharedPref.getInt("age",-1)
        nameFromPref = sharedPref.getString("name","")
        countryFromPref = sharedPref.getString("country","")
        if (ageFromPref != -1 && nameFromPref !="" && countryFromPref != ""){
            sharedPref.edit().remove("age").apply()
            sharedPref.edit().remove("name").apply()
            sharedPref.edit().remove("country").apply()
            binding.resultText.text = "Your : "        }
    }
}