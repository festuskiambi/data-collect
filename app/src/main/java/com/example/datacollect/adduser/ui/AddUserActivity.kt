package com.example.datacollect.adduser.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.datacollect.R
import com.example.datacollect.adduser.viewmodel.AddUserViewModel
import com.example.datacollect.adduser.viewmodel.AddUserViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.scanner_layout.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddUserActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: AddUserViewModelFactory
    private lateinit var viewModel: AddUserViewModel

    private val scannerView by lazy { scanner_view }
    private lateinit var codeScanner: CodeScanner

    val REQUEST_TAKE_PHOTO = 100

    private var currentBuildingPhotoPath = ""
    private var currentProductInfo = ""
    private var currentLatitude = 0.0
    private var currentLongitude = 0.0
    private var firstName = ""
    private var lastName = ""
    private var idNumber: Int? = 0

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddUserViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        setUpButtons()
        setupQrCodeScanner()
        setQRCodeScannerCallbacks()
        getLocation()
    }


    private fun setUpButtons() {
        btn_product_code.setOnClickListener {
            scan_layout.visibility = View.VISIBLE
            form_layout.visibility = View.INVISIBLE
            codeScanner.startPreview()
        }

        btn_bulding_image.setOnClickListener {
            dispatchTakePictureIntent()
        }

        btn_submit.setOnClickListener {
            getUserInputs()
        }
    }

    private fun getUserInputs() {
        firstName = et_first_name.text.toString()
        lastName = et_last_name.text.toString()
        idNumber = et_id_number.text.toString().toIntOrNull()

        if (firstName.isEmpty()
            || currentBuildingPhotoPath.isEmpty()
            || currentProductInfo.isEmpty()
            || idNumber == null
        ) {
            showErrorState()
        } else {

            viewModel.handleEvent(
                AddUserEvent.OnSave(
                    firstName,
                    lastName,
                    idNumber!!,
                    currentLatitude,
                    currentLongitude,
                    currentBuildingPhotoPath,
                    currentProductInfo
                )
            )
        }
    }

    private fun showErrorState() {
        Toast.makeText(this, "Fill in Blank fields", Toast.LENGTH_SHORT).show()

    }

    private fun setupQrCodeScanner() {
        codeScanner = CodeScanner(this, scannerView)

        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false
    }

    private fun setQRCodeScannerCallbacks() {
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                scan_layout.visibility = View.INVISIBLE
                form_layout.visibility = View.VISIBLE
                currentProductInfo = it.text
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                scan_layout.visibility = View.INVISIBLE
                form_layout.visibility = View.VISIBLE
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                location?.also {
                    currentLatitude = location.latitude
                    currentLongitude = location.longitude
                }
            }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    ex.printStackTrace()
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "$packageName.provider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentBuildingPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            imageView.setImageURI(Uri.parse(currentBuildingPhotoPath))
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}
