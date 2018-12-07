package oscar.com.eater.holder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR
import oscar.com.eater.pojo.Ingredient

/**
 * Created by oscmenji on 2018-05-12.
 */
class IngredientViewHolder(v : ViewDataBinding) : RecyclerView.ViewHolder(v.root) {
    val viewDataBinding = v
    fun bindView(i : Ingredient){
        viewDataBinding.setVariable(BR.ingredient,i)
    }

}