package tcc.com.br.tccfatec.presenter

import android.content.Context
import android.util.Log
import org.greenrobot.eventbus.EventBus
import retrofit2.Callback
import tcc.com.br.tccfatec.model.Item
import tcc.com.br.tccfatec.service.Client
import tcc.com.br.tccfatec.service.Message
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import com.google.firebase.database.DatabaseError
import android.R.attr.author
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




class ItemPresenter(){
    lateinit var mDatabase:DatabaseReference
    private val mPostReference: DatabaseReference? = null

    fun getItens() {
        val call = Client().createService().getItens()
        call.enqueue(object : Callback<List<Item>?> {
            override fun onResponse(call: retrofit2.Call<List<Item>?>?,
                                    response: retrofit2.Response<List<Item>?>?) {
                response?.body()?.let {
                    val itens: List<Item> = it
                    val message = Message(itens)
                    EventBus.getDefault().post(message)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Item>?>?,
                                   t: Throwable?) {
                if (t != null) {
                    Log.i(javaClass.simpleName, "Error in response " + t.message)
                }
            }
        })
    }

    fun getITensFirebase(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("itens")
        var list: ArrayList<Item> = ArrayList()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                dataSnapshot.children.forEach {
                    val post = it.getValue(Item::class.java) as Item
                    Log.i("Teste","Qual o valor aqui " + post.name)
                    list.add(post)
                }
                val message = Message(list)
                EventBus.getDefault().post(message)
                // [END_EXCLUDE]
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        mDatabase.addValueEventListener(postListener)
    }
}