package de.exultation.passwortgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.exultation.passwortgenerator.databinding.FragmentConfigurationBinding
import de.exultation.passwortgenerator.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.combine

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfigurationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigurationFragment : Fragment()
{




    lateinit var binding : FragmentConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        binding = FragmentConfigurationBinding.inflate(activity?.layoutInflater!!)
        return inflater.inflate(R.layout.fragment_configuration, container, false)
    }

}