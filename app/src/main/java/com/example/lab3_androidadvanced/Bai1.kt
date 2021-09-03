package com.example.lab3_androidadvanced

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.loader.content.CursorLoader
import com.example.lab3_androidadvanced.databinding.ActivityBai1Binding

class Bai1 : AppCompatActivity() {
    private lateinit var binding: ActivityBai1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBai1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        if (!isPermissionsGranted()) {
            requestReadContactPermissions()
        } else {
            binding.lvContacts.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, getContactList())
        }
    }

    private fun isPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadContactPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_CONTACTS
            )
        ) {
            AlertDialog.Builder(this).apply {
                setTitle("Requesting read contacts permission")
                setMessage("We need this permission for access contacts on your device")
                setPositiveButton("OK") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this@Bai1,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        Constant.READ_CONTACTS_CODE
                    )

                }
                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                Constant.READ_CONTACTS_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constant.READ_CONTACTS_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.lvContacts.adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, getContactList())
            } else {
                Toast.makeText(this, "We need this permission to display all of your contacts!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getContactList(): ArrayList<String> {
        val contactList = ArrayList<String>()
        val uri = Uri.parse("content://contacts/people")

        val loader = CursorLoader(this, uri, null, null, null, null)
        val cursor = loader.loadInBackground()

        cursor!!.moveToFirst()
        while (!cursor.isAfterLast) {
            var contactInfo: String
            val idColumn = ContactsContract.Contacts._ID
            val idIndex = cursor.getColumnIndex(idColumn)

            contactInfo = "${cursor.getString(idIndex)} - "

            val nameColumn = ContactsContract.Contacts.DISPLAY_NAME
            val nameIndex = cursor.getColumnIndex(nameColumn)

            contactInfo += cursor.getString(nameIndex)

            cursor.moveToNext()
            contactList.add(contactInfo)
        }

        cursor.close()
        return contactList
    }


}