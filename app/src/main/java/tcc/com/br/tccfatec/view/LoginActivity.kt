package tcc.com.br.tccfatec.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.EditText
import tcc.com.br.tccfatec.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.login_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val signUp = findViewById(R.id.sign_up) as Button
        var recoveryPassword: Button = findViewById(R.id.password_recovery)

        signUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
        }

        recoveryPassword.setOnClickListener {
            var alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@LoginActivity)
            var editText = EditText(this@LoginActivity)
            editText.hint = "Insira seu email. Que te enviaremos a senha"
            alertDialog.setView(editText)
            alertDialog.setPositiveButton("OK", null)
            alertDialog.show()
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
