package de.exultation.passwortgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.exultation.passwortgenerator.ui.main.MainFragment

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}