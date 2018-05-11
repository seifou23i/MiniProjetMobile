package com.example.ubuntu.miniprojetmobile.entity
import android.provider.ContactsContract
import com.example.ubuntu.miniprojetmobile.R

/**
 * Created by ubuntu on 4/16/18.
 */
class Restaurant(var listImage:Int=0,
                 var detailImage:Int= R.drawable.restaurant1,
                 var name:String="El Djenina",
                 var restaurantAdresse:String="Alger Centre, 140 Rue Didouche Mourad",
                 var plats:Array<String> = arrayOf<String>(),
                 var description:String="Paris est la ville capitale et la plus grande de France. elle est située sur la Seine, dans le nord de la France, au cœur de l’Île-de-France. Elle dispose de 2 millions d’habitants et est la cinquième des villes les plus peuplées du monde. La ville est une des centres les plus influentes dans le monde entier, ainsi que culturel, politique et économique très forte à la fois près at et près national. La ville est administrée par un maire. Entouré d’un conseil municipal, il décide du budjet de la ville, des impots locaux, des grands équipements d’infrastucture, de logement et de loisir. L’Hotel de Ville est le siège des institutions municipales.",
                 var phoneNumber: String ="0662167277",
                 var email: String ="elDjenina@gmail.com",
                 var facebook:String="www.facebook.com/elDjenina",
                 var twitter:String="www.twitter.com/elDjenina")
{
    }