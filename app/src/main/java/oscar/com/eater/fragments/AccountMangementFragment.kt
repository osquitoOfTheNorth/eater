package oscar.com.eater.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import oscar.com.eater.EaterApplication
import oscar.com.eater.R
import oscar.com.eater.adapter.SavedRecipesAdapter
import oscar.com.eater.viewmodelfactories.ViewModelFactory
import oscar.com.eater.viewmodels.AccountViewModel
import oscar.com.eater.databinding.AccountFragmentBinding
import oscar.com.eater.pojo.SavedRecipe
import java.util.HashMap

class AccountMangementFragment : Fragment() {


    private lateinit var viewModel : AccountViewModel
    private var mSavedRecipes: MutableList<SavedRecipe> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,ViewModelFactory(null,EaterApplication.getApplication(), null)).get(AccountViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val databinding : AccountFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.account_fragment,
                container, false)
        databinding.viewModel = viewModel
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val recyclerView = databinding.root.findViewById<RecyclerView>(R.id.saved_recipes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SavedRecipesAdapter(context!!,mSavedRecipes)
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val savedRecipes = dataSnapshot.child(uid).child("recipes").value as List<HashMap<String, String>>?
                for (savedRecipe in savedRecipes!!) {
                    mSavedRecipes.add(SavedRecipe(savedRecipe["name"]!!,
                            savedRecipe["url"]!!))
                }
                adapter.update(mSavedRecipes)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, R.string.unable_to_load_recipes, Toast.LENGTH_LONG).show()
            }
        }

        FirebaseDatabase.getInstance("https://querico-53ad8.firebaseio.com")
                .getReference("/users")
                .addListenerForSingleValueEvent(listener)
        recyclerView.adapter = adapter
        return databinding.root
    }
}