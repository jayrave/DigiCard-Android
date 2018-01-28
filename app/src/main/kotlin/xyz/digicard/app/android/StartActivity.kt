package xyz.digicard.app.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        findViewById<View>(R.id.nfc).setOnClickListener {

            // Until we have a login/signup screen I am creating a new
            // dummy user every time!
            val randomInt = Random().nextInt().absoluteValue
            val user = User(
                    id = UUID.randomUUID(),
                    firstName = "First_$randomInt",
                    lastName = "Last_$randomInt",
                    email = "Email_$randomInt",
                    purpose = "Purpose_$randomInt"
            )

            // Put the user in the db & start beaming
            UsersTable.instance.dao.insert(user)
            NfcSendActivity.start(this, user.id)
        }
        findViewById<View>(R.id.register).setOnClickListener {
            startActivity(Intent(this, NewActivity::class.java))
        }
        findViewById<View>(R.id.menu).setOnClickListener{
            startActivity(Intent(this, Menu::class.java))
        val currentUserId = CurrentUser.getId(this)
        when (currentUserId) {
            null -> startActivity(Intent(this, NewActivity::class.java))
            else -> startActivity(Intent(this, Menu::class.java))
        }
        findViewById<View>(R.id.nearby_connections).setOnClickListener{
            startActivity(Intent(this, NearbyConnections::class.java))
        }
    }
}