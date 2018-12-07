package tcc.com.br.tccfatec.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tcc.com.br.tccfatec.MainActivity
import tcc.com.br.tccfatec.R
import tcc.com.br.tccfatec.model.Item
import tcc.com.br.tccfatec.util.ImageUtils

class DetailsItemActivity : AppCompatActivity() {
    companion object {
        private val INTENT_ITEM = "item"

        fun newIntent(context: Context, item: Item): Intent{
            val intent = Intent(context, DetailsItemActivity::class.java)
            intent.putExtra(INTENT_ITEM,item)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_item)

        val item = intent.getParcelableExtra(INTENT_ITEM) as Item

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(item.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageItem = findViewById(R.id.img_item) as ImageView
        val textTitle = findViewById(R.id.title_item) as TextView
        val textDescription = findViewById<TextView>(R.id.descricao_item)
        val textAddress = findViewById<TextView>(R.id.item_addres)
        val messageEdit = findViewById<EditText>(R.id.message_text)
        val btnSend = findViewById<Button>(R.id.btn_send)

        messageEdit.setText("Olá " + item.author + ",Tenho interesse góstaria de saber mais sobre.")

        textTitle.setText(item.author)
        textDescription.setText(item.description)
        textAddress.setText(item.address + ", " + item.city + ", " + item.state)

        /*Glide.with(this@DetailsItemActivity)
                .load(item.photo)
                .error(R.drawable.ic_round_accessible_24px)
                .placeholder(R.drawable.ic_round_accessible_24px)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageItem)*/

        if (ImageUtils.convert(item.photo) != null) {
            imageItem.setImageBitmap(ImageUtils.convert(item.photo))
        } else {
            imageItem.setImageDrawable(ContextCompat.getDrawable(this@DetailsItemActivity, R.drawable.ic_round_accessible_24px))
        }



        btnSend.setOnClickListener {
            startActivity(Intent(this@DetailsItemActivity, MainActivity::class.java))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
