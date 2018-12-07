package tcc.com.br.tccfatec.view.fragment

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AlertDialogLayout
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import tcc.com.br.tccfatec.MainActivity
import tcc.com.br.tccfatec.R
import tcc.com.br.tccfatec.model.Item
import tcc.com.br.tccfatec.service.Message
import tcc.com.br.tccfatec.util.CircleTransformation
import tcc.com.br.tccfatec.util.ImageUtils
import tcc.com.br.tccfatec.util.UpdateImage

class DonateFragment : Fragment(), UpdateImage {


    override fun updateImage(bitmap: Bitmap) {
        updatePhoto(bitmap)
    }

    fun getUpdateImage(): UpdateImage {
        return this@DonateFragment
    }

    companion object {
        public val PICK_PHOTO = 1
        public val TAKE_PHOTO = 2
    }

    lateinit var selectPicture: ImageButton
    lateinit var selectedPicture: ImageView
    lateinit var databasereference: DatabaseReference
    lateinit var imageItem: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.donation_layout, container, false) as View
        selectPicture = view.findViewById(R.id.select_picture)
        selectedPicture = view.findViewById(R.id.selectedPicture)
        var sppinerItem: AppCompatSpinner = view.findViewById(R.id.drop_item)
        var address:EditText = view.findViewById(R.id.address_item)
        var checkAddress: AppCompatCheckBox = view.findViewById(R.id.check_address)
        var annunciateItem: Button = view.findViewById(R.id.post_donate)
        var nameItem: EditText = view.findViewById(R.id.name_item)
        var descriptionIten: EditText = view.findViewById(R.id.descricao_item)
        databasereference = FirebaseDatabase.getInstance().getReference()


        annunciateItem.setOnClickListener {
            Toast.makeText(context,"Doação efetuada com sucesso! Aguarde interessados", Toast.LENGTH_LONG).show()
            var item:Item = Item(nameItem.text.toString(),sppinerItem.selectedItem.toString(),getImage(),
                    descriptionIten.text.toString(), address.text.toString(), "SP","Marcos","SP")
            val ref = databasereference.child("itens")
            val newPostRef = ref.push()
            newPostRef.setValue(item)
            var mainActivity: MainActivity = activity as MainActivity
            mainActivity.replaceTab()

        }

        checkAddress.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(b){
                address.setText("Rua benedito fernandes 152")
                address.isEnabled = false
            }else{
                address.text.clear()
                address.isEnabled = true
            }
        }

        selectedPicture.setOnClickListener { openDialog() }
        selectPicture.setOnClickListener { openDialog() }

        //String array.
        val myStrings = arrayOf("Cadeira de rodas eletrica", "Cadeira de rodas manual", "Aparelho auditivo", "Muletas", "Andador")

        //Adapter for spinner
        sppinerItem.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, myStrings)

        return view
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }


    fun openDialog() {
        var dialog = AlertDialog.Builder(context!!)
        dialog.setMessage("Selecionar metodo")
        dialog.setPositiveButton("Tirar foto") { dialog, which ->
            var mainActivity: MainActivity = activity as MainActivity
            mainActivity.openCamera()
        }
        dialog.setNeutralButton("Selecionar da galeria") { dialog, which ->
            var mainActivity: MainActivity = activity as MainActivity
            mainActivity.openGalery()
        }
        dialog.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()

    }

    fun updatePhoto(photo: Bitmap) {

        selectPicture.setVisibility(GONE)
        selectedPicture.setVisibility(View.VISIBLE)
        imageItem = ImageUtils.convert(photo)

        Glide.with(this)
                .load(ImageUtils.bitmapToByte(photo))
                .asBitmap().centerCrop().into(object : BitmapImageViewTarget(selectedPicture) {
                    override fun setResource(resource: Bitmap?) {
                        var circularBitmapDrawable: RoundedBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context!!.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        selectedPicture.setImageDrawable(circularBitmapDrawable);
                    }
                })
    }

    fun getImage(): String{
        if(imageItem != null){
            return imageItem
        }else{
            return ""
        }
    }

}