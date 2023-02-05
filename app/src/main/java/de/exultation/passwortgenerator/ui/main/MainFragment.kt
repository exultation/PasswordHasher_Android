package de.exultation.passwortgenerator.ui.main

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.ClipboardManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.material.button.MaterialButtonToggleGroup
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

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupListeners()

        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        //binding.txtAlias.setAdapter(adapter)
        return binding.root//inflater.inflate(R.layout.fragment_main, container, false)
    }


    fun setupListeners()
    {
        binding.imgCopy.setOnClickListener{onCopyToClipboard()}
        binding.imgHelp.setOnClickListener{onHelpClick(it)}
        binding.imgSettings.setOnClickListener{onSettingsClick(it)}
        binding.toggelPasswortLen.setOnClickListener{onPasswordLenChange(it)}
        binding.toggelPasswortLen.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if(isChecked)
            {
                when (toggleButton.checkedButtonId)
                {
                    R.id.passwortLen8 -> viewModel.passwordLength.value = 8
                    R.id.passwortLen12 -> viewModel.passwordLength.value = 12
                    R.id.passwortLen16 -> viewModel.passwordLength.value = 16
                    R.id.passwortLen20 -> viewModel.passwordLength.value = 20
                    else -> viewModel.passwordLength.value = 8
                }
            }
        }
        viewModel.key.observe(viewLifecycleOwner  ){onChange()}
        viewModel.alias.observe(viewLifecycleOwner){onChange()}
        viewModel.passwordLength.observe(viewLifecycleOwner){onChange()}
    }

    fun onCopyToClipboard()
    {
        viewModel.password.value?.let {
            it.copyToClipboard(this.requireContext())
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(requireContext(),"$it copied to Clipboard" , duration)
            toast.show()

        }

    }

    fun onChange()
    {
        viewModel.encryptAlias()
    }

    fun onPasswordLenChange(view : Any)
    {
        if(view is MaterialButtonToggleGroup)
        {
            when(view.checkedButtonId)
            {
                R.id.passwortLen8 -> viewModel.passwordLength.value = 8
                R.id.passwortLen12 -> viewModel.passwordLength.value = 12
                R.id.passwortLen16 -> viewModel.passwordLength.value = 16
                R.id.passwortLen20 -> viewModel.passwordLength.value = 20
                else -> viewModel.passwordLength.value = 8

            }
        }

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

fun String.copyToClipboard(context : Context)
{
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
    {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.text = "Passwort $this copied to Clipboard"
    }
    else
    {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager

        val clip = ClipData.newPlainText("Copied Text",this )
        clipboard.setPrimaryClip(clip)
    }
}