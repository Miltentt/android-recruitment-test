package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.ViewModels.Main_ViewModel
import dog.snow.androidrecruittest.ui.ViewModels.Splash_ViewModel
import dog.snow.androidrecruittest.ui.adapter.ListAdapter
import dog.snow.androidrecruittest.ui.model.Detail
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class ListFragment : DaggerFragment(R.layout.list_fragment)
{
   lateinit var lol : Detail
     var position: Int  = 0
    lateinit var v :View
    var listadapter : ListAdapter = ListAdapter({lol,position,v-> Unit})
    inline fun onClick(item: Detail, position: Int, view: View)
{

}
    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory
    private lateinit var mainVewmodel: Main_ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainVewmodel = ViewModelProviders.of(this,viewModelsProviderFactory)[Main_ViewModel::class.java]
        initRecycler(view)
        mainVewmodel.getDetails().observe({lifecycle},{t-> Log.i("xd",t.get(0).albumTitle)})
    }

    private fun initRecycler(view : View)
    {

        rv_items.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listadapter
        }

    }
}

