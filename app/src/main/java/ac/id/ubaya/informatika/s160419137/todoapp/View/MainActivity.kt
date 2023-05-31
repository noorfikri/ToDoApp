package ac.id.ubaya.informatika.s160419137.todoapp.View

import ac.id.ubaya.informatika.s160419137.todoapp.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = (supportFragmentManager.findFragmentById(R.id.navHostMainView) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)

    }
}