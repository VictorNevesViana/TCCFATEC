package tcc.com.br.tccfatec.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import tcc.com.br.tccfatec.R
import tcc.com.br.tccfatec.adapter.AdapterItensDonation
import tcc.com.br.tccfatec.model.Item
import tcc.com.br.tccfatec.presenter.ItemPresenter
import tcc.com.br.tccfatec.service.Message

class ItensDonationFragment : android.support.v4.app.Fragment(){
    lateinit var recyclerView: RecyclerView
    lateinit var itens :List<Item>
    lateinit var adapterItensDonation: AdapterItensDonation


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_itens_donation,container,false) as View
        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        adapterItensDonation = AdapterItensDonation(context)
        itens = ArrayList<Item>()
        adapterItensDonation.setItems(itens as ArrayList<Item>)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapterItensDonation
        var itemPresenter = ItemPresenter()
        //itemPresenter.getItens()
        itemPresenter.getITensFirebase()
        return view
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this@ItensDonationFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this@ItensDonationFragment)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventReceived(list: Message){
        itens = list.arraiList
        adapterItensDonation.setItems(itens as ArrayList<Item>)
        adapterItensDonation.notifyDataSetChanged()
    }
}