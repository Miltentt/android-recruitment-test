package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.squareup.picasso.Picasso
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.model.Detail
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.layout_appbar.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class DetailsFragment : Fragment(R.layout.details_fragment)
{
    lateinit var detail : Detail
    companion object
    {
        fun fragmentNewInstance (detail: Detail) : DetailsFragment
        {
            val fragmentNewInstance =DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable("detail",detail)
            fragmentNewInstance.arguments=bundle
            return fragmentNewInstance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments !=null)
        {
            detail = requireArguments().getParcelable("detail")!!
        }
        initActionBar()
        sharedElementEnterTransition=Slide()
        sharedElementReturnTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(detail.url).
        placeholder(R.drawable.ic_placeholder).into(iv_photo)
        tv_photo_title.setText(detail.photoTitle)
        tv_album_title.setText(detail.albumTitle)
        tv_username.setText(detail.username)
        tv_email.setText(detail.email)
        tv_phone.setText(detail.phone)
    }
    private fun initActionBar() {
        (activity as MainActivity).apply {
            appbar.isVisible = true
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            appbar.toolbar.titleMarginStart = resources.getDimensionPixelSize(R.dimen.margin_small)
            appbar.setExpanded(true, true)
            supportActionBar?.title = detail.photoTitle
            toolbar.setNavigationOnClickListener({supportFragmentManager.popBackStack()})
        }
    }
}
