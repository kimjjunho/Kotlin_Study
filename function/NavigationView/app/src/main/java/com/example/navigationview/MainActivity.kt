package com.example.navigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionBar: ActionBar?

        actionBar = supportActionBar
        actionBar?.hide()

        val drawer_Layout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navi_btn : ImageButton = findViewById(R.id.navi_btn)
        val naviView : NavigationView = findViewById(R.id.naviView)

       /* val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)*/

        /*supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.navi_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)*/

        navi_btn.setOnClickListener{
            drawer_Layout.openDrawer(GravityCompat.START)
        }
        naviView.setNavigationItemSelectedListener(this)
    }

    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer_Layout : DrawerLayout = findViewById(R.id.drawer_layout)
        when (item.itemId) {
            R.id.menu_item1 -> Toast.makeText(this, "메뉴 1", Toast.LENGTH_LONG).show()
            R.id.menu_item2 -> Toast.makeText(this, "메뉴 2", Toast.LENGTH_LONG).show()
            R.id.menu_item3 -> Toast.makeText(this, "메뉴 3", Toast.LENGTH_LONG).show()
        }
        drawer_Layout.closeDrawers()
        return false
    }

}
