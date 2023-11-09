package com.bluetechloop.mvvmtempleteproject.view.activities

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bluetechloop.mvvmtempleteproject.R
import com.bluetechloop.mvvmtempleteproject.databinding.ActivityMainBinding
import com.example.zameedar.view.Fragment.HomeFragment
import com.example.zameedar.view.Fragment.MenuFragment
import com.example.zameedar.view.Fragment.MoreFragment
import com.example.zameedar.view.Fragment.OfferFragment
import com.example.zameedar.view.Fragment.ProfileFragment
import com.move.zoom.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    //Creating a ViewBinding Variable in kotlin.
    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    lateinit var leftNavHeader: View

//    private var user =
//        SingletonGson.gson.fromJson(ArtiPreferenceManager.getUser(), User::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@MainActivity

        replaceFragment(HomeFragment())
        binding.bottomNavBar.background = null
        val toggle = ActionBarDrawerToggle(this, binding.drawLayout, binding.qq, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(MenuFragment())
        binding.imvMenuq1.text = "Menu"

        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item -> {
                    replaceFragment(MenuFragment())
                     binding.imvMenuq1.text = "Menu"
                }
                R.id.offer_item -> {
                    replaceFragment(OfferFragment())
                     binding.imvMenuq1.text = "New Feed"
                }
                R.id.profile_item -> {
//                    if(!isTokenEmpty()){
                        replaceFragment(ProfileFragment())
                        binding.imvMenuq1.text = "Profile"
//                    }else {
//                       gotoLoginActivity()
//                    }
                }

                R.id.more_items -> {
                    replaceFragment(MoreFragment())
                     binding.imvMenuq1.text = "More"
                }
            }
            true
        }

        binding.leftNav.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuMyDashboard -> {
                    binding.bottomNavBar.selectedItemId=R.id.menu_item
                }
                R.id.menuCreatePost -> {

                }
                R.id.menuEditPost -> {

                }
                R.id.menuBlog -> {
//                    gotoMyBlogActivity()
                }

                R.id.menuGallery -> {
//                    gotoMyGalleryActivity()
                }

                R.id.menulogout -> {

                }
            }
            binding.drawLayout.closeDrawers()
            true
        }

        val menu: Menu = binding.leftNav.menu
        val logout = menu.findItem(R.id.menulogout)

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frmContainer, fragment)
        fragmentTransaction.commit()
    }
}