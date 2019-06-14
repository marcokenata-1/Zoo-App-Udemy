package com.example.zooapp_udemy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter  : AnimalAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load animals
        listOfAnimals.add(Animal("Baboon","Baboon lives in trees",R.drawable.baboon,false))
        listOfAnimals.add(Animal("Bulldog","Baboon lives in trees",R.drawable.bulldog,false))
        listOfAnimals.add(Animal("Panda","Baboon lives in trees",R.drawable.panda,true))
        listOfAnimals.add(Animal("Swallow Bird","Baboon lives in trees",R.drawable.swallow_bird,false))
        listOfAnimals.add(Animal("White Tiger","Baboon lives in trees",R.drawable.white_tiger,true))
        listOfAnimals.add(Animal("Zebra","Baboon lives in trees",R.drawable.zebra,false))

        adapter = AnimalAdapter(this,listOfAnimals)
        tvListAnimal.adapter = adapter

    }

    class AnimalAdapter : BaseAdapter {

        var listOfAnimals = ArrayList<Animal>()
        var context: Context? = null
        constructor(context: Context,listOfAnimals: ArrayList<Animal>): super() {
            this.context = context
            this.listOfAnimals = listOfAnimals
        }

        override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[position]
            var myView:View
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            if(animal.isKiller == true){
                myView = inflator.inflate(R.layout.animal_killer_ticket, null)
            } else {
                myView = inflator.inflate(R.layout.animal_ticket, null)
            }

            myView.tvName.text = animal.name!!
            myView.tvDes.text = animal.des!!
            myView.ivAnimalImage.setImageResource(animal.image!!)

            myView.ivAnimalImage.setOnClickListener{
                val intent = Intent(context,AnimalInfo::class.java)
                intent.putExtra("name",animal.name!!)
                intent.putExtra("des",animal.des!!)
                intent.putExtra("image",animal.image!!)
                context!!.startActivity(intent)

            }

            return myView
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
