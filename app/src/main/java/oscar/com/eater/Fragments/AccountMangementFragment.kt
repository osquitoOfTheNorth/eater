package oscar.com.eater.Fragments

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import oscar.com.eater.EaterApplication
import oscar.com.eater.R
import oscar.com.eater.ViewModelFactories.ViewModelFactory
import oscar.com.eater.ViewModels.AccountViewModel
import oscar.com.eater.databinding.AccountFragmentBinding
import javax.inject.Inject

class AccountMangementFragment : Fragment() {


    private lateinit var viewModel : AccountViewModel
    @Inject private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,ViewModelFactory(null,EaterApplication.getApplication(), null)).get(AccountViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding : AccountFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.account_fragment, container, false)
        databinding.viewModel = viewModel
        return databinding.root
    }
}