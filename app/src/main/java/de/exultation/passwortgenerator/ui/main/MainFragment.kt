package de.exultation.passwortgenerator.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.exultation.passwortgenerator.R
import de.exultation.passwortgenerator.databinding.FragmentMainBinding

class MainFragment : Fragment()
{

    val TAG = MainFragment::class.java.simpleName
    companion object
    {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding =   FragmentMainBinding.inflate(layoutInflater)
        setupListeners()
        binding.viewModel = viewModel
        return binding.root//inflater.inflate(R.layout.fragment_main, container, false)
    }


    fun setupListeners()
    {
        binding.imgHelp.setOnClickListener{onHelpClick(it)}
        binding.imgSettings.setOnClickListener{onSettingsClick(it)}
        viewModel.key.observe(viewLifecycleOwner  ){onKeyChenged(it)}
        viewModel.password.observe(viewLifecycleOwner){onPasswordChange(it)}

    }

    fun onPasswordChange(password : String)
    {

        binding.txtPassword.setText(password)
    }
    
    fun onKeyChenged(key : String)
    {

       viewModel.encryptAlias()
    }

    fun onHelpClick(view : View)
    {
        Log.d(TAG, "onCreateView: setOnClickListener")
    }

    fun onSettingsClick(view : View)
    {
        Log.d(TAG, "onCreateView: setOnClickListener")
    }
}