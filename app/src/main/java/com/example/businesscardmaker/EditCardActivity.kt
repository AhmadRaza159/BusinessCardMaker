package com.example.businesscardmaker

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import java.io.File
import java.io.FileOutputStream
import android.view.ViewTreeObserver.OnGlobalLayoutListener

import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscardmaker.widget_utils.DragableView
import com.example.businesscardmaker.widget_utils.VerticalSeekBar
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
import java.util.*
import kotlin.collections.ArrayList
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import android.view.inputmethod.EditorInfo

import android.view.inputmethod.InputConnection


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
    var onTextChangeFlag = false
    var imageSizeFlag = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        var code = intent.getIntExtra("code", 1)
        Log.d("codde", code.toString())
        if (code == 1) {
            setContentView(R.layout.template_1)
            c_company_name.visibility=View.GONE
        } else if (code == 2) {
            setContentView(R.layout.template_2)
            c_company_name.visibility=View.GONE
        } else if (code == 3) {
            setContentView(R.layout.template_3)
            c_company_name.visibility=View.GONE
        } else if (code == 4) {
            setContentView(R.layout.template_4)
            c_company_name.visibility=View.GONE
        } else if (code == 5) {
            setContentView(R.layout.template_5)
            c_company_name.visibility=View.GONE
        } else if (code == 6) {
            setContentView(R.layout.template_6)
        } else if (code == 7) {
            setContentView(R.layout.template_7)
        } else if (code == 8) {
            setContentView(R.layout.template_8)
            c_company_name.visibility=View.GONE
        } else if (code == 9) {
            setContentView(R.layout.template_9)
        }
        else if (code == 10) {
            setContentView(R.layout.template_10)
            c_company_name.visibility=View.GONE
            c_image.visibility=View.GONE
        }
        else if (code == 11) {
            setContentView(R.layout.template_11)
            c_company_name.visibility=View.GONE
        }
        else if (code == 12) {
            setContentView(R.layout.template_12)
            c_company_name.visibility=View.GONE
        }

        supportActionBar?.hide()

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
        widgetList.add(c_name!!)
        widgetList.add(c_des!!)
        widgetList.add(c_phone!!)
        widgetList.add(c_mail!!)
        widgetList.add(c_web!!)
        widgetList.add(c_addr!!)

        if (c_image != null) {
            widgetList.add(c_image)
        }
        if (c_company_name != null) {
            widgetList.add(c_company_name)
        }
        items_list.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)
        adapter = ItemsListAdapter(applicationContext, widgetList, object : ItemsListInterface {
            override fun onClick(model: DragableView, img: ImageView) {
                if (model.visibility == View.VISIBLE) {
                    model.visibility = View.GONE
                    img.setImageDrawable(resources.getDrawable(R.drawable.menu_invisble))
                } else if (model.visibility == View.GONE) {
                    model.visibility = View.VISIBLE
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

        cardSurface.setOnClickListener {
            flag = ""
            activeTextView = null
            side_menu.animate().translationX(400F)
            seekbar_drawer.animate().translationX(-400f)
            widget_main_menu.animate().translationY(-250f)
            widget_list_opener.animate().rotation(0f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            c_name.background = null
            c_des.background = null
            c_phone.background = null
            c_mail.background = null
            c_web.background = null
            c_addr.background = null
            c_image.background = null
            c_company_name?.background = null
            mSeekBar.setProgress(0, true)
        }

        widget_list_opener_main.setOnClickListener {
            if (widget_main_menu.translationY == 0f) {

                widget_list_opener.animate().rotation(0f)
                widget_main_menu.animate().translationY(-250f)
            } else {
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
                side_menu.animate().translationX(400F)
                seekbar_drawer.animate().translationX(-400f)
                widget_list_opener.animate().rotation(180f)
                widget_main_menu.animate().translationY(0f)
            }


        }


        mName.viewInterface = object : DragableView.ViewInterface {
            override fun setText(view: TextView?) {
                activeImage = null
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)

                btn_color.visibility = View.VISIBLE
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
                widget_main_menu.animate().translationY(-250f)
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED

                btn_color.visibility = View.GONE
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
                c_image.background = resources.getDrawable(R.drawable.selected_item_background)
                c_company_name?.background = null
                bool = true
            }
        }
        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                widget_main_menu.animate().translationY(-250f)

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
            val file = File(newDir, "Card Maker")
            if (!file.exists()) file.mkdirs()
            return file.path
        }
//        btn = findViewById(R.id.btn_save)
//        btn.setOnClickListener {
//            val vto: ViewTreeObserver = theView.viewTreeObserver
//            vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
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
//            theView.layout(0, 0, theView.measuredWidth, theView.measuredHeight)
//            val bitmap = Bitmap.createBitmap(
//                theView.measuredWidth,
//                theView.measuredHeight,
//                Bitmap.Config.ARGB_8888
//            )
//            val canvas = Canvas(bitmap)
//            theView.draw(canvas)
//            Bitmap.createScaledBitmap(bitmap, theView.measuredWidth, theView.measuredHeight, true)
//            val pdfDocument = PdfDocument()
//            val pageInfo =
//                PdfDocument.PageInfo.Builder(theView.measuredWidth, theView.measuredHeight, 1)
//                    .create()
//            val page = pdfDocument.startPage(pageInfo)
//            page.canvas.drawBitmap(bitmap, 0F, 0F, null)
//            pdfDocument.finishPage(page)
//            val filePath = File(getDir(), "${Calendar.getInstance().timeInMillis}.pdf")
//            pdfDocument.writeTo(FileOutputStream(filePath))
//            Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()
//            pdfDocument.close()
//        }
        edit_view.setOnClickListener {
            widget_main_menu.animate().translationY(-250f)
            if (flag == "tv") {
                bottom_sheet_text.animate().translationX(0f).duration = 500
                bottom_sheet_color.animate().translationX(-1300f)
                onTextChangeFlag = false
                input.text.clear()
                onTextChangeFlag = true
                bottom_sheet_color.visibility = View.GONE
                bottom_sheet_text.visibility = View.VISIBLE
                BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_EXPANDED
                input.doOnTextChanged { text, start, before, count ->
                    if (onTextChangeFlag) {
                        activeTextView?.setText(text.toString())
                    }

                }

            } else if (flag == "img") {
                val gallery: Intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, 1)
            }
        }
        btn_color.setOnClickListener {
            widget_main_menu.animate().translationY(-250f)
            bottom_sheet_text.animate().translationX(-500f)
            bottom_sheet_color.animate().translationX(0f).duration = 500
            bottom_sheet_color.visibility = View.VISIBLE
            bottom_sheet_text.visibility = View.GONE
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

        btn_rotate.setOnTouchListener { v, event ->
            widget_main_menu.animate().translationY(-250f)
            BottomSheetBehavior.from(sheeeet).state = BottomSheetBehavior.STATE_COLLAPSED
            Log.d("LogKey", event?.action.toString())
            if (flag == "img") {
                var v = activeImage?.parent as View
                v.rotation = (v.rotation + 1)
            } else {
                var v = activeTextView?.parent as View
                v.rotation = (v.rotation + 1)
            }

//            if (event?.action == MotionEvent.ACTION_MOVE) {
//
//            }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == 1) {
                var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)

                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false)
                    image_logo.setImageBitmap(bitmap)
                } else {
                    Toast.makeText(applicationContext, "Something wrong!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }


}