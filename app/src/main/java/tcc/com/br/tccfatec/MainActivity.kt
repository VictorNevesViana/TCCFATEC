package tcc.com.br.tccfatec

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import tcc.com.br.tccfatec.view.LoginActivity
import org.greenrobot.eventbus.EventBus
import tcc.com.br.tccfatec.model.Item
import tcc.com.br.tccfatec.presenter.ItemPresenter
import tcc.com.br.tccfatec.util.ImageUtils
import tcc.com.br.tccfatec.util.UpdateImage
import tcc.com.br.tccfatec.view.fragment.DonateFragment
import tcc.com.br.tccfatec.view.fragment.DonateFragment.Companion.PICK_PHOTO
import tcc.com.br.tccfatec.view.fragment.DonateFragment.Companion.TAKE_PHOTO
import tcc.com.br.tccfatec.view.fragment.ItensDonationFragment
import java.io.IOException
import tcc.com.br.tccfatec.R.id.tabLayout




class MainActivity : AppCompatActivity() {
    lateinit var donateFragment: DonateFragment
    lateinit var itens:ArrayList<Item>
    lateinit var tabLayout: TabLayout
    private var mUri: Uri? = null
    private var mBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.text_ad)
        donateFragment = DonateFragment()

        tabLayout = findViewById(R.id.tabLayout) as TabLayout

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.text_ad)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.text_donate)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.text_login)))

        tabLayout?.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceFragment(ItensDonationFragment())
                    1 ->
                        replaceFragment(donateFragment)
                    2 ->
                        startActivity(Intent(this@MainActivity,LoginActivity::class.java))

                }

            }

        })


        replaceFragment(ItensDonationFragment())
    }


    private fun replaceFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    fun replaceTab(){
        val tab = tabLayout.getTabAt(0)
        tab!!.select()
    }

    fun openGalery() {
        val photoPicker = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        photoPicker.type = "image/*"
        startActivityForResult(photoPicker, PICK_PHOTO)
    }

    /**
     * Open Camera to Take Photo
     */
    fun openCamera() {
        val takePhoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePhoto, TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_PHOTO) {
                if (data != null) {
                    mUri = data.data
                }

                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(contentResolver, mUri)
                    mBitmap = ImageUtils.modifyOrientation(this, mBitmap, mUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                if (mBitmap != null) {
                   // EventBus.getDefault().post(UpdateImage(mBitmap!!))
                    donateFragment.getUpdateImage().updateImage(mBitmap!!)
                }
            } else if (requestCode == TAKE_PHOTO) {
                val extras = data!!.extras
                mBitmap = extras!!.get("data") as Bitmap

                if (mBitmap != null) {
                    donateFragment.getUpdateImage().updateImage(mBitmap!!)
                }
            }
        }
    }
}
