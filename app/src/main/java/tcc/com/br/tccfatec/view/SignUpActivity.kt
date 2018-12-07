package tcc.com.br.tccfatec.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import tcc.com.br.tccfatec.MainActivity
import tcc.com.br.tccfatec.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.signUp_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val button = findViewById(R.id.btn_signUp) as Button

        button.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
        })
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
