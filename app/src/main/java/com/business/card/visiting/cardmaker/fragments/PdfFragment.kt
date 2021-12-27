package com.business.card.visiting.cardmaker.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.business.card.visiting.cardmaker.BuildConfig
import com.business.card.visiting.cardmaker.R
import com.business.card.visiting.cardmaker.my_creation.DownloadedInterface
import com.business.card.visiting.cardmaker.my_creation.MyCreationAdapter
import kotlinx.android.synthetic.main.fragment_pdf.view.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class PdfFragment : Fragment() {
    private lateinit var adapter: MyCreationAdapter
    private var datL = ArrayList<File>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_pdf, container, false)
        v.my_creation_pdf_recyclerview.layoutManager = LinearLayoutManager(context)
//////////////////////////////////

            datL.clear()
            for (i in getDownloadedFileList()){
                if (i.toString().toUpperCase().contains("PDF")){
                    datL.add(i)
                }

            }

            if (datL.size > 0) v.empsec.visibility = View.GONE else v.empsec.visibility =
                View.VISIBLE

            adapter = MyCreationAdapter(datL,false,requireContext(), object : DownloadedInterface {
                override fun onClick(model: File) {

                    val intent = Intent(Intent.ACTION_VIEW)

                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY)

                    intent.setDataAndType(
                        Objects.requireNonNull(context)?.let {
                            FileProvider.getUriForFile(
                                it,
                                BuildConfig.APPLICATION_ID + ".provider", model
                            )
                        }, "application/pdf"
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
                            context,
                            "No app to read File",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }, object : DownloadedInterface {
                override fun onClick(model: File) {
                    val intent = Intent("android.intent.action.SEND")
                    intent.type = "application/pdf"
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


                    intent.putExtra(
                        "android.intent.extra.STREAM", Objects.requireNonNull(context)?.let {
                            FileProvider.getUriForFile(
                                it,
                                BuildConfig.APPLICATION_ID + ".provider", model
                            )
                        }
                    )
                    try {
                        startActivity(
                            Intent.createChooser(
                                intent,
                                "Share File "
                            )
                        )
                    } catch (unused: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No app to read File",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    ///////////////////////////////////
//                    Toast.makeText(this@DownloadedResumesActivity,model.toString(),Toast.LENGTH_SHORT).show()
//                    ////////
//                    val sharingIntent = Intent(Intent.ACTION_SEND)
//                    sharingIntent.type = "*/*"
//                    sharingIntent.putExtra(Intent.EXTRA_STREAM, model)
//                    startActivity(Intent.createChooser(sharingIntent, "Share using"))
                }

            })
            v.my_creation_pdf_recyclerview.adapter = adapter





        //////////////////////


        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PdfFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    fun getDownloadedFileList(): Array<File> {

        val newDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val file = File(newDir, "Business Card Maker")
        if (!file.exists()) file.mkdirs()
        val fileList= file.listFiles()
        return fileList

    }
}