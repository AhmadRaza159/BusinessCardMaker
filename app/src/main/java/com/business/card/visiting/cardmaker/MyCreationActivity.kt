package com.business.card.visiting.cardmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.business.card.visiting.cardmaker.fragments.JpegFragment
import com.business.card.visiting.cardmaker.fragments.PdfFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_my_creation.*

class MyCreationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_creation)

        back.setOnClickListener {
            finish()
        }

        tablayout.addTab(tablayout.newTab().setText("PDFs"))
        tablayout.addTab(tablayout.newTab().setText("Images"))
        tablayout.tabGravity = TabLayout.GRAVITY_FILL
        val FileViewAdapter: FileViewAdapter =
            FileViewAdapter(
                supportFragmentManager
            )
        viewpager.adapter = FileViewAdapter
        viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tablayout
            )
        )
        tablayout.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                viewpager
            )
        )
    }

    class FileViewAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    PdfFragment()
                }
                1 -> {
                    JpegFragment()
                }
                else -> {
                    PdfFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}