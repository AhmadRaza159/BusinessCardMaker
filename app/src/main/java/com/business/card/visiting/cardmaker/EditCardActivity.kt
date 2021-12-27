package com.business.card.visiting.cardmaker

import android.Manifest
import android.R.attr
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.business.card.visiting.cardmaker.widget_utils.DragableView
import com.business.card.visiting.cardmaker.widget_utils.VerticalSeekBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.edit_buttons.*
import kotlinx.android.synthetic.main.seekbar_drawer.*
import kotlinx.android.synthetic.main.template_1.*
import kotlinx.android.synthetic.main.template_1.c_addr
import kotlinx.android.synthetic.main.template_1.c_des
import kotlinx.android.synthetic.main.template_1.c_image
import kotlinx.android.synthetic.main.template_1.c_mail
import kotlinx.android.synthetic.main.template_1.c_name
import kotlinx.android.synthetic.main.template_1.c_phone
import kotlinx.android.synthetic.main.template_1.c_web
import kotlinx.android.synthetic.main.template_6.c_company_name
import kotlinx.android.synthetic.main.widgets_menu.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.save_dialog.*
import kotlinx.android.synthetic.main.save_dialog.view.*
import java.io.FileOutputStream
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import android.R.attr.path
import android.os.Handler
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_user_details.*


class EditCardActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var theView: ConstraintLayout
    private lateinit var mName: DragableView
    private lateinit var mDes: DragableView
    private lateinit var mAddr: DragableView
    private lateinit var mPhone: DragableView
    private lateinit var mGmail: DragableView
    private lateinit var mWeb: DragableView
    private lateinit var mSeekBar: VerticalSeekBar
    var widgetList = ArrayList<DragableView>()
    lateinit var adapter: ItemsListAdapter
    private lateinit var cardSurface: ConstraintLayout
    private var activeTextView: TextView? = null
    private var activeImage: ImageView? = null
    private var activeView: DragableView? = null
    private var bool = true
    private var flag: String = ""
    private var fileName: String = ""
    var onTextChangeFlag = false
    var imageSizeFlag = false
    private lateinit var storagePermission: Array<String>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        var code = intent.getIntExtra("code", 1)
        storagePermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (code == 1) {
            setContentView(R.layout.template_1)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_1)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_1)
                .into(bck_img)
        } else if (code == 2) {
            setContentView(R.layout.template_2)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_2)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_2)
                .into(bck_img)
        } else if (code == 3) {
            setContentView(R.layout.template_3)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_3)
                .into(frnt_img)
        } else if (code == 4) {
            setContentView(R.layout.template_4)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_4)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_4)
                .into(bck_img)
        } else if (code == 5) {
            setContentView(R.layout.template_5)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_5)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_5)
                .into(bck_img)
        } else if (code == 6) {
            setContentView(R.layout.template_6)
            Glide.with(applicationContext)
                .load(R.drawable.card_back_6)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_6)
                .into(bck_img)
        } else if (code == 7) {
            setContentView(R.layout.template_7)
            Glide.with(applicationContext)
                .load(R.drawable.card_back_7)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_7)
                .into(bck_img)
        } else if (code == 8) {
            setContentView(R.layout.template_8)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_8)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_8)
                .into(bck_img)
        } else if (code == 9) {
            setContentView(R.layout.template_9)
            Glide.with(applicationContext)
                .load(R.drawable.card_back_9)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_9)
                .into(bck_img)
        } else if (code == 10) {
            setContentView(R.layout.template_10)
            c_company_name.visibility = View.GONE
            c_image.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_10)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_10)
                .into(bck_img)
        } else if (code == 11) {
            setContentView(R.layout.template_11)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_11)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_11)
                .into(bck_img)
        } else if (code == 12) {
            setContentView(R.layout.template_12)
            c_company_name.visibility = View.GONE
            Glide.with(applicationContext)
                .load(R.drawable.card_back_12)
                .into(frnt_img)
            Glide.with(applicationContext)
                .load(R.drawable.back_side_12)
                .into(bck_img)
        }
        supportActionBar?.hide()
////////////////////////////////////////////////////////////////////////
        //shared preferance code

        var sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        name.text = sharedPref.getString("user_name","Your Name")
        des.text = sharedPref.getString("user_des","Your Designation")
        phone.text = sharedPref.getString("user_phone","Your Phone")
        mail.text = sharedPref.getString("user_email","Your Email")
        web.text = sharedPref.getString("user_web","Your Website")
        addr.text = sharedPref.getString("user_addr","Your Address")
        company_name.text = sharedPref.getString("user_company_name","Company Name")

        fileName=name.text.toString()


            Handler().postDelayed({
                Toast.makeText(getApplicationContext(),"Click on any item to edit it or drag them to a desired location!",Toast.LENGTH_LONG).show()

        }, 700)




        BottomSheetBehavior.from(sheeeet).apply {
            peekHeight = 0
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }


        cardSurface = findViewById(R.id.card_surface)
        theView = findViewById(R.id.the_view)
        mName = findViewById(R.id.c_name)
        mDes = findViewById(R.id.c_des)
        mSeekBar = findViewById(R.id.seekbar)


        if (widgetList.size != 0) {
            widgetList.clear()
        }
        widgetList.add(c_name)
        widgetList.add(c_des)
        widgetList.add(c_phone)
        widgetList.add(c_mail)
        widgetList.add(c_web)
        widgetList.add(c_addr)
        widgetList.add(c_image)
        widgetList.add(c_company_name)

        items_list.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)

        adapter = ItemsListAdapter(applicationContext, widgetList, object : ItemsListInterface {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(model: DragableView, img: ImageView) {
                if (model.visibility == View.VISIBLE) {
                    model.visibility = View.GONE
                    if(model.tooltipText=="Phone No"){
                        c_content_des_phone.visibility = View.GONE
                    }
                    else if(model.tooltipText=="Website"){
                        c_content_des_web.visibility = View.GONE
                    }
                    else if(model.tooltipText=="Email"){
                        c_content_des_mail.visibility = View.GONE
                    }
                    else if(model.tooltipText=="Address"){
                        c_content_des_address.visibility = View.GONE
                    }
                    else if(model.tooltipText=="Name"){
                        fileName= sharedPref.getString("user_name","Your Name").toString()
                    }
                    img.setImageDrawable(resources.getDrawable(R.drawable.menu_invisble))
                } else if (model.visibility == View.GONE) {
                    model.visibility = View.VISIBLE
                    if(model.tooltipText=="Phone No"){
                        c_content_des_phone.visibility = View.VISIBLE
                    }
                    else if(model.tooltipText=="Website"){
                        c_content_des_web.visibility = View.VISIBLE
                    }
                    else if(model.tooltipText=="Email"){
                        c_content_des_mail.visibility = View.VISIBLE
                    }
                    else if(model.tooltipText=="Address"){
                        c_content_des_address.visibility = View.VISIBLE
                    }
                    img.setImageDrawable(resources.getDrawable(R.drawable.menu_visible))


                }
            }

        }, object : ItemsListInterface {
            override fun onClick(model: DragableView, img: ImageView) {
                var event =
                    MotionEvent.obtain(model.drawingTime, model.drawingTime, 0, model.x, model.y, 0)
                model.onTouchEvent(event)
            }

        }
        )
        items_list.adapter = adapter

        Log.d("LogKey", "${cardSurface.width}, ${cardSurface.height}")

        parent_surface.setOnClickListener {
            flag = ""
            activeTextView = null
            side_menu.animate().translationX(400F)
            seekbar_drawer.animate().translationX(-400f)
            save_btn.animate().translationX(0f)
            bck_frnt_btn.animate().translationX(0f)
            widget_main_menu.animate().translationY(items_list.height*-1f)
            widget_list_opener.animate().rotation(0f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            c_name.background = null
            c_des.background = null
            c_phone.background = null
            c_mail.background = null
            c_web.background = null
            c_back_image.background=null
            c_addr.background = null
            c_image.background = null
            c_company_name?.background = null
            mSeekBar.setProgress(0, true)
        }

        card_surface_back.setOnClickListener {
            flag = ""
            activeTextView = null
            side_menu.animate().translationX(400F)
            seekbar_drawer.animate().translationX(-400f)
            save_btn.animate().translationX(0f)
            bck_frnt_btn.animate().translationX(0f)
            widget_main_menu.animate().translationY(items_list.height*-1f)
            widget_list_opener.animate().rotation(0f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            c_name.background = null
            c_des.background = null
            c_phone.background = null
            c_mail.background = null
            c_web.background = null
            c_back_image.background=null
            c_addr.background = null
            c_image.background = null
            c_company_name?.background = null
            mSeekBar.setProgress(0, true)

        }

        cardSurface.setOnClickListener {
            flag = ""
            activeTextView = null
            side_menu.animate().translationX(400F)
            seekbar_drawer.animate().translationX(-400f)
            save_btn.animate().translationX(0f)
            bck_frnt_btn.animate().translationX(0f)
            widget_main_menu.animate().translationY(items_list.height*-1f)
            widget_list_opener.animate().rotation(0f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            c_name.background = null
            c_des.background = null
            c_phone.background = null
            c_mail.background = null
            c_web.background = null
            c_back_image.background=null
            c_addr.background = null
            c_image.background = null
            c_company_name?.background = null
            mSeekBar.setProgress(0, true)
        }

        widget_list_opener_main.setOnClickListener {
            if (widget_main_menu.translationY == 0f) {
                save_btn.animate().translationX(0f)
                bck_frnt_btn.animate().translationX(0f)
                widget_list_opener.animate().rotation(0f)
                widget_main_menu.animate().translationY(items_list.height*-1f)
            } else {
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
                side_menu.animate().translationX(400F)
                seekbar_drawer.animate().translationX(-400f)
                widget_list_opener.animate().rotation(180f)
                widget_main_menu.animate().translationY(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)

            }


        }


        mName.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))

                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view

                c_name.background = resources.getDrawable(R.drawable.selected_item_background)
                c_des.background = null
                c_phone.background = null
                c_mail.background = null
                c_web.background = null
                c_back_image.background=null
                c_addr.background = null
                c_image.background = null
                c_company_name?.background = null

                bool = true
            }

            override fun setImage(view: ImageView?) {
            }
        }
        mDes.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view
                c_name.background = null
                c_des.background = resources.getDrawable(R.drawable.selected_item_background)
                c_phone.background = null
                c_mail.background = null
                c_back_image.background=null
                c_web.background = null
                c_addr.background = null
                c_image.background = null
                c_company_name?.background = null
                bool = true

            }

            override fun setImage(view: ImageView?) {
            }
        }

        c_addr.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view
                c_name.background = null
                c_des.background = null
                c_phone.background = null
                c_mail.background = null
                c_web.background = null
                c_back_image.background=null
                c_addr.background = resources.getDrawable(R.drawable.selected_item_background)
                c_image.background = null
                c_company_name?.background = null
                bool = true

            }

            override fun setImage(view: ImageView?) {
            }
        }

        c_web.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view
                c_name.background = null
                c_des.background = null
                c_phone.background = null
                c_back_image.background=null
                c_mail.background = null
                c_web.background = resources.getDrawable(R.drawable.selected_item_background)
                c_addr.background = null
                c_image.background = null
                c_company_name?.background = null
                bool = true

            }

            override fun setImage(view: ImageView?) {
            }
        }

        c_mail.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view
                c_name.background = null
                c_des.background = null
                c_phone.background = null
                c_back_image.background=null
                c_mail.background = resources.getDrawable(R.drawable.selected_item_background)
                c_web.background = null
                c_addr.background = null
                c_image.background = null
                c_company_name?.background = null
                bool = true

            }

            override fun setImage(view: ImageView?) {
            }
        }

        c_phone.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view
                c_name.background = null
                c_des.background = null
                c_phone.background = resources.getDrawable(R.drawable.selected_item_background)
                c_mail.background = null
                c_back_image.background=null
                c_web.background = null
                c_addr.background = null
                c_image.background = null
                c_company_name?.background = null
                bool = true

            }

            override fun setImage(view: ImageView?) {
            }
        }

        c_company_name?.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.VISIBLE
                btn_font.visibility = View.VISIBLE
                btn_color.visibility = View.VISIBLE
                btn_rotate_left.visibility=View.GONE
                btn_rotate_right.visibility=View.GONE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_edit_text))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "tv"
                var per = (view?.textSize?.div(300))?.times(100)
                per?.toInt()?.let { mSeekBar.setProgress(it) }
                activeTextView = view
                c_name.background = null
                c_des.background = null
                c_phone.background = null
                c_back_image.background=null
                c_mail.background = null
                c_web.background = null
                c_addr.background = null
                c_image.background = null
                c_company_name?.background =
                    resources.getDrawable(R.drawable.selected_item_background)
                bool = true

            }

            override fun setImage(view: ImageView?) {
            }
        }


        c_image?.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
            }

            override fun setImage(view: ImageView?) {
                activeTextView = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.GONE
                btn_font.visibility = View.GONE
                btn_color.visibility = View.GONE
                btn_rotate_left.visibility=View.VISIBLE
                btn_rotate_right.visibility=View.VISIBLE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_change_image))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "img"
                Log.d("LogKey", view?.layoutParams?.width.toString())
                if (imageSizeFlag) {
                    view?.layoutParams?.width?.div(5)?.let { mSeekBar.setProgress(it) }
                } else {
                    200.div(5)?.let { mSeekBar.setProgress(it) }

                }


//                var pr= ((view?.measuredHeight )?.div(203))?.times(100)
//                if (pr != null) {
//                    mSeekBar.setProgress(pr,true)
//                }
                activeImage = view
                c_name.background = null
                c_des.background = null
                c_phone.background = null
                c_mail.background = null
                c_web.background = null
                c_addr.background = null
                c_back_image.background=null
                c_image.background = resources.getDrawable(R.drawable.selected_item_background)
                c_company_name?.background = null
                bool = true
            }
        }
        c_back_image?.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
            }

            override fun setImage(view: ImageView?) {
                activeTextView = null
                widget_main_menu.animate().translationY(items_list.height*-1f)
                widget_list_opener.animate().rotation(0f)
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
                save_btn.animate().translationX(400F)
                bck_frnt_btn.animate().translationX(-400f)
                btn_style.visibility = View.GONE
                btn_font.visibility = View.GONE
                btn_color.visibility = View.GONE
                btn_rotate_left.visibility=View.VISIBLE
                btn_rotate_right.visibility=View.VISIBLE
                edit_view.setImageDrawable(resources.getDrawable(R.drawable.button_change_image))
                side_menu.animate().translationX(0f)
                seekbar_drawer.animate().translationX(0f)
                bool = false
                flag = "img"
                Log.d("LogKey", view?.layoutParams?.width.toString())
                if (imageSizeFlag) {
                    view?.layoutParams?.width?.div(5)?.let { mSeekBar.setProgress(it) }
                } else {
                    200.div(5)?.let { mSeekBar.setProgress(it) }

                }


//                var pr= ((view?.measuredHeight )?.div(203))?.times(100)
//                if (pr != null) {
//                    mSeekBar.setProgress(pr,true)
//                }
                activeImage = view
                c_name.background = null
                c_des.background = null
                c_phone.background = null
                c_mail.background = null
                c_web.background = null
                c_addr.background = null
                c_back_image.background=resources.getDrawable(R.drawable.selected_item_background)
                c_image.background = null
                c_company_name?.background = null
                bool = true
            }
        }
        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                widget_main_menu.animate().translationY(items_list.height*-1f)

                if (bool)
                    if (flag == "tv") {
                        activeTextView?.let { manageTextViewSize(it, progress) }
                    } else if (flag == "img") {
                        activeImage?.let { manageImageSize(it, progress) }
                    }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        fun getDir(): String {
            val newDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            val file = File(newDir, "Business Card Maker")
            if (!file.exists()) file.mkdirs()
            Log.d("pathVal", file.path.toString())
            return file.path
        }
        ///////////////////////////////
        bck_frnt_btn.setOnClickListener {
            if (the_view.translationY == 0f) {
                the_view.animate().translationY(-1400f).duration = 500
                bck_frnt_btn.animate().rotation(360f).duration = 500
                bck_frnt_btn.setImageDrawable(resources.getDrawable(R.drawable.button_front_side))
                if (widgetList.size != 0) {
                    widgetList.clear()
                }
                widgetList.add(c_back_image!!)
                adapter.notifyDataSetChanged()
            } else {
                the_view.animate().translationY(0f).duration = 500
                bck_frnt_btn.animate().rotation(0f).duration = 500
                bck_frnt_btn.setImageDrawable(resources.getDrawable(R.drawable.button_back_side))
                if (widgetList.size != 0) {
                    widgetList.clear()
                }
                widgetList.add(c_name)
                widgetList.add(c_des)
                widgetList.add(c_phone)
                widgetList.add(c_mail)
                widgetList.add(c_web)
                widgetList.add(c_addr)
                widgetList.add(c_image)
                widgetList.add(c_company_name)
                adapter.notifyDataSetChanged()

            }

        }
        /////////////////////////////////////////////////
        save_btn.setOnClickListener {
            if(checkStoragePermission()){

                val dialog = Dialog(this)
                dialog.setContentView(R.layout.save_dialog)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(false)
                dialog.setCanceledOnTouchOutside(true)
                
                if(TextUtils.isEmpty(fileName)){
                    fileName= sharedPref.getString("user_name","Your Name").toString()
                }


//            val vto: ViewTreeObserver = theView.viewTreeObserver
//            vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//                override fun onGlobalLayout() {
//                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//                        theView.viewTreeObserver.removeGlobalOnLayoutListener(this)
//                    } else {
//                        theView.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                    }
//                    val width: Int = theView.measuredWidth
//                    val height: Int = theView.measuredHeight
//                }
//            })


                //////////////
                dialog.save_as_pdf.setOnClickListener {
                    dialog.dismiss()

                    val bitmap = Bitmap.createBitmap(
                        theView.measuredWidth,
                        theView.measuredHeight,
                        Bitmap.Config.ARGB_8888
                    )
                    val bitmap2 = Bitmap.createBitmap(
                        the_back_view.measuredWidth,
                        the_back_view.measuredHeight,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    val canvas2 = Canvas(bitmap2)
                    theView.draw(canvas)
                    the_back_view.draw(canvas2)
                    Bitmap.createScaledBitmap(
                        bitmap,
                        theView.measuredWidth,
                        theView.measuredHeight,
                        true
                    )
                    Bitmap.createScaledBitmap(
                        bitmap2,
                        the_back_view.measuredWidth,
                        the_back_view.measuredHeight,
                        true
                    )
                    val pdfDocument = PdfDocument()
                    val pageInfo =
                        PdfDocument.PageInfo.Builder(theView.measuredWidth, theView.measuredHeight, 1)
                            .create()
                    val pageInfo2 =
                        PdfDocument.PageInfo.Builder(
                            the_back_view.measuredWidth,
                            the_back_view.measuredHeight,
                            2
                        )
                            .create()
                    val page = pdfDocument.startPage(pageInfo)
                    page.canvas.drawBitmap(bitmap, 0F, 0F, null)
                    pdfDocument.finishPage(page)
                    val page2 = pdfDocument.startPage(pageInfo2)
                    page2.canvas.drawBitmap(bitmap2, 0F, 0F, null)
                    pdfDocument.finishPage(page2)

                    val filePath = File(getDir(), "${fileName}_${Calendar.getInstance().timeInMillis}_card.pdf")
                    Log.d("pathVal", filePath.toString())
                    pdfDocument.writeTo(FileOutputStream(filePath))
                    Toast.makeText(getApplicationContext(),"File saved at ${getDir()}",Toast.LENGTH_SHORT).show()
                    pdfDocument.close()

                    val intent = Intent(Intent.ACTION_VIEW)

                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY)

                    intent.setDataAndType(
                        FileProvider.getUriForFile(
                            Objects.requireNonNull(getApplicationContext()),
                            BuildConfig.APPLICATION_ID + ".provider", filePath
                        ), "application/pdf"
                    )
                    try {
                        startActivity(
                            Intent.createChooser(
                                intent,
                                "Open File"
                            )
                        )
                    } catch (unused: ActivityNotFoundException) {
                        Toast.makeText(
                            applicationContext,
                            "No app to read File",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                dialog.save_as_image.setOnClickListener {
                    dialog.dismiss()
                    theView.isDrawingCacheEnabled = true
                    var bitmapFront = Bitmap.createBitmap(theView.drawingCache)
                    theView.isDrawingCacheEnabled = false

                    the_back_view.isDrawingCacheEnabled = true
                    var bitmapBack = Bitmap.createBitmap(the_back_view.drawingCache)
                    the_back_view.isDrawingCacheEnabled = false

                    val filePath2Front = File(getDir(), "${fileName}_${Calendar.getInstance().timeInMillis}_front.jpeg")
                    val streamFront = FileOutputStream(filePath2Front)
                    bitmapFront.compress(Bitmap.CompressFormat.JPEG, 100, streamFront)
                    streamFront.close()

                    val filePath2Back = File(getDir(), "${fileName}_${Calendar.getInstance().timeInMillis}_back.jpeg")
                    val streamBack = FileOutputStream(filePath2Back)
                    bitmapBack.compress(Bitmap.CompressFormat.JPEG, 100, streamBack)
                    streamBack.close()

                    Toast.makeText(getApplicationContext(),"Files are saved at ${getDir()}",Toast.LENGTH_SHORT).show()



                    bottom_sheet_image_viewer.animate().translationX(0f).duration = 500
                    bottom_sheet_text.animate().translationX(-1300f)
                    bottom_sheet_color.animate().translationX(-1300f)
                    bottom_sheet_font.animate().translationX(-1300f)
                    bottom_sheet_style.animate().translationX(-1300f)
                    bottom_sheet_style.visibility = View.GONE
                    bottom_sheet_color.visibility = View.GONE
                    bottom_sheet_text.visibility = View.GONE
                    bottom_sheet_font.visibility = View.GONE
                    bottom_sheet_image_viewer.visibility = View.VISIBLE
                    BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_EXPANDED


                    image_viewer_front.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)

                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY)

                        intent.setDataAndType(
                            FileProvider.getUriForFile(
                                Objects.requireNonNull(getApplicationContext()),
                                BuildConfig.APPLICATION_ID + ".provider", filePath2Front
                            ), "image/*"
                        )
                        try {
                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    "Open File"
                                )
                            )
                        } catch (unused: ActivityNotFoundException) {
                            Toast.makeText(
                                applicationContext,
                                "No app to read File",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    image_viewer_back.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)

                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY)

                        intent.setDataAndType(
                            FileProvider.getUriForFile(
                                Objects.requireNonNull(getApplicationContext()),
                                BuildConfig.APPLICATION_ID + ".provider", filePath2Back
                            ), "image/*"
                        )
                        try {
                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    "Open File"
                                )
                            )
                        } catch (unused: ActivityNotFoundException) {
                            Toast.makeText(
                                applicationContext,
                                "No app to read File",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }




                }
                dialog.show()
            }
            else{
                requestStoragePermission()
            }
        }

        edit_view.setOnClickListener {
            widget_main_menu.animate().translationY(items_list.height*-1f)
            if (flag == "tv") {
                bottom_sheet_text.animate().translationX(0f).duration = 500
                bottom_sheet_color.animate().translationX(-1300f)
                bottom_sheet_font.animate().translationX(-1300f)
                bottom_sheet_style.animate().translationX(-1300f)
                bottom_sheet_image_viewer.animate().translationX(-1300f)
                bottom_sheet_image_viewer.visibility = View.GONE
                bottom_sheet_style.visibility = View.GONE

                onTextChangeFlag = false
                input.text.clear()
                onTextChangeFlag = true
                bottom_sheet_color.visibility = View.GONE
                bottom_sheet_text.visibility = View.VISIBLE
                bottom_sheet_font.visibility = View.GONE
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_EXPANDED
                input.doOnTextChanged { text, start, before, count ->
                    if (onTextChangeFlag) {
                        activeTextView?.setText(text.toString())
                        fileName=text.toString()
                    }

                }

            } else if (flag == "img") {
                if(checkStoragePermission()){
                    val gallery: Intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    startActivityForResult(gallery, 1)
                }
                else{
                    requestStoragePermission()
                }
                
            }
        }
        btn_color.setOnClickListener {
            widget_main_menu.animate().translationY(items_list.height*-1f)
            bottom_sheet_text.animate().translationX(-500f)
            bottom_sheet_font.animate().translationX(-1300f)
            bottom_sheet_color.animate().translationX(0f).duration = 500
            bottom_sheet_style.animate().translationX(-1300f)
            bottom_sheet_image_viewer.animate().translationX(-1300f)
            bottom_sheet_image_viewer.visibility = View.GONE
            bottom_sheet_style.visibility = View.GONE
            bottom_sheet_color.visibility = View.VISIBLE
            bottom_sheet_text.visibility = View.GONE
            bottom_sheet_font.visibility = View.GONE
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_EXPANDED

            clr_black.setOnClickListener {
                activeTextView?.setTextColor(Color.BLACK)
            }

            clr_drk_gray.setOnClickListener {
                activeTextView?.setTextColor(Color.DKGRAY)
            }

            clr_gray.setOnClickListener {
                activeTextView?.setTextColor(Color.GRAY)
            }

            clr_light_gray.setOnClickListener {
                activeTextView?.setTextColor(Color.LTGRAY)
            }

            clr_white.setOnClickListener {
                activeTextView?.setTextColor(Color.WHITE)
            }

            clr_red.setOnClickListener {
                activeTextView?.setTextColor(Color.RED)
            }

            clr_green.setOnClickListener {
                activeTextView?.setTextColor(Color.GREEN)
            }

            clr_blue.setOnClickListener {
                activeTextView?.setTextColor(Color.BLUE)
            }

            clr_cyan.setOnClickListener {

                activeTextView?.setTextColor(Color.parseColor("#25DAD6"))
            }

            clr_yellow.setOnClickListener {
                activeTextView?.setTextColor(Color.YELLOW)
            }

            clr_magenta.setOnClickListener {
                activeTextView?.setTextColor(Color.MAGENTA)
            }
            clr_gold.setOnClickListener {
                activeTextView?.setTextColor(Color.parseColor("#E99D2F"))
            }
            clr_light_blue.setOnClickListener {
                activeTextView?.setTextColor(Color.parseColor("#5881AE"))
            }
            clr_light_red.setOnClickListener {
                activeTextView?.setTextColor(Color.parseColor("#F8C4C5"))
            }
        }


        btn_font.setOnClickListener {
            widget_main_menu.animate().translationY(items_list.height*-1f)
            bottom_sheet_text.animate().translationX(-500f)
            bottom_sheet_font.animate().translationX(0f).duration = 500
            bottom_sheet_color.animate().translationX(-1300f)
            bottom_sheet_style.animate().translationX(-1300f)
            bottom_sheet_image_viewer.animate().translationX(-1300f)
            bottom_sheet_image_viewer.visibility = View.GONE
            bottom_sheet_style.visibility = View.GONE
            bottom_sheet_color.visibility = View.GONE
            bottom_sheet_text.visibility = View.GONE
            bottom_sheet_font.visibility = View.VISIBLE
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_EXPANDED

            fnt_tnr.setOnClickListener {
                activeTextView?.typeface =
                    Typeface.createFromAsset(assets, "fonts/time_new_roman.ttf")


            }
            fnt_nasalization.setOnClickListener {
                activeTextView?.typeface =
                    Typeface.createFromAsset(assets, "fonts/nasalization.ttf")

            }
            fnt_foco.setOnClickListener {
                activeTextView?.typeface = Typeface.createFromAsset(assets, "fonts/foco.ttf")

            }
            fnt_helv.setOnClickListener {
                activeTextView?.typeface = Typeface.createFromAsset(assets, "fonts/helvetica.ttf")

            }
            fnt_futura.setOnClickListener {
                activeTextView?.typeface = Typeface.createFromAsset(assets, "fonts/futur.ttf")

            }
            fnt_omens.setOnClickListener {
                activeTextView?.typeface = Typeface.createFromAsset(assets, "fonts/omens.ttf")

            }
            fnt_nova.setOnClickListener {
                activeTextView?.typeface = Typeface.createFromAsset(assets, "fonts/nova.ttf")

            }
            fnt_montserrat.setOnClickListener {
                activeTextView?.typeface = Typeface.createFromAsset(assets, "fonts/montserrat.ttf")

            }

        }

        btn_style.setOnClickListener {
            widget_main_menu.animate().translationY(items_list.height*-1f)
            bottom_sheet_text.animate().translationX(-500f)
            bottom_sheet_font.animate().translationX(-1300f)
            bottom_sheet_color.animate().translationX(-1300f)
            bottom_sheet_style.animate().translationX(0f).duration = 500
            bottom_sheet_image_viewer.animate().translationX(-1300f)
            bottom_sheet_image_viewer.visibility = View.GONE
            bottom_sheet_style.visibility = View.VISIBLE
            bottom_sheet_color.visibility = View.GONE
            bottom_sheet_text.visibility = View.GONE
            bottom_sheet_font.visibility = View.GONE
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_EXPANDED

            style_bold.setOnClickListener {
                activeTextView?.typeface = Typeface.create(activeTextView?.typeface, Typeface.BOLD)

            }
            style_italic.setOnClickListener {
                activeTextView?.typeface =
                    Typeface.create(activeTextView?.typeface, Typeface.ITALIC)

            }
            style_bold_italic.setOnClickListener {
                activeTextView?.typeface =
                    Typeface.create(activeTextView?.typeface, Typeface.BOLD_ITALIC)

            }
            style_normal.setOnClickListener {
                activeTextView?.typeface =
                    Typeface.create(activeTextView?.typeface, Typeface.NORMAL)

            }

        }

        btn_rotate_right.setOnTouchListener { v, event ->
            widget_main_menu.animate().translationY(items_list.height*-1f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            Log.d("LogKey", event?.action.toString())
            if (flag == "img") {
                var v = activeImage?.parent as View
                v.rotation = (v.rotation + 1)
            }



            return@setOnTouchListener true

        }
        btn_rotate_left.setOnTouchListener { v, event ->
            widget_main_menu.animate().translationY(items_list.height*-1f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            Log.d("LogKey", event?.action.toString())
            if (flag == "img") {
                var v = activeImage?.parent as View
                v.rotation = (v.rotation - 1)
            }


            return@setOnTouchListener true

        }

        bottom_sheet_close.setOnClickListener {
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }


    private fun manageTextViewSize(view: TextView, size: Int) {
        view.textSize = size.toFloat()


    }

    private fun manageImageSize(view: ImageView, size: Int) {
        val layoutParams = FrameLayout.LayoutParams(size * 5, size * 5)
        view.layoutParams = layoutParams
        imageSizeFlag = true
//        view.minimumHeight = size * 20
//        view.minimumWidth = size * 20

    }


    fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, 11)
    }

    fun checkStoragePermission(): Boolean {
        var a= ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        var b= ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        return a&&b
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == 1) {
                var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)

                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false)
                    activeImage?.setImageBitmap(bitmap)

                } else {
                    Toast.makeText(applicationContext, "Something wrong!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }


}