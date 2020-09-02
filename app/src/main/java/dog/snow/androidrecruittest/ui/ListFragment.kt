package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.support.DaggerFragment
import dog.snow.androidrecruittest.DI.ViewModelsProviderFactory
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.ViewModels.Main_ViewModel
import dog.snow.androidrecruittest.ui.ViewModels.Splash_ViewModel
import dog.snow.androidrecruittest.ui.adapter.ListAdapter

import dog.snow.androidrecruittest.ui.model.Detail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.layout_appbar.*
import kotlinx.android.synthetic.main.layout_empty_view.*
import kotlinx.android.synthetic.main.layout_search.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_item.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListFragment : DaggerFragment(R.layout.list_fragment)
{
    var listadapter : ListAdapter = ListAdapter({item ->onClick(item)})

    @Inject
    lateinit var viewModelsProviderFactory: ViewModelsProviderFactory
    private lateinit var mainVewmodel: Main_ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainVewmodel = ViewModelProvider(this,viewModelsProviderFactory).get(Main_ViewModel::class.java)
        initActionBar()
        initRecycler()
        initSearchView()
        mainVewmodel.getLiveData().observe({lifecycle}, {
                t-> listadapter.submitList(t)
            if(t.isEmpty())
                tv_empty.visibility=View.VISIBLE
            else
                tv_empty.visibility=View.GONE
        })

    }

    private fun initRecycler() {
        rv_items.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listadapter
        }
    }

    fun onClick(item : Detail)
    {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,DetailsFragment.fragmentNewInstance(item))
            .addSharedElement(iv_thumb,"transition")
            .addToBackStack(null)
            .commit()
    }

    fun initSearchView()
    {
        et_search.textChanges()
            .debounce(500,TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({mainVewmodel.getDetails(et_search.text.toString())})
    }
    private fun initActionBar() {
        (activity as MainActivity).apply {
            appbar.isVisible = true
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            appbar.toolbar.titleMarginStart = resources.getDimensionPixelSize(R.dimen.margin_large)
            appbar.setExpanded(true, true)
            supportActionBar?.title = resources.getString(R.string.app_name)

        }
    }
    }


