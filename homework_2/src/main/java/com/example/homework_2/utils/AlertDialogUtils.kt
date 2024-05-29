package com.example.homework_2.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.homework_2.R

object AlertDialogUtils {
    private fun showCreateChannelDialog(layoutInflater: LayoutInflater, context: Context) {
        val dialogView = layoutInflater.inflate(R.layout.create_channel_alert_dialog, null)

        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialogTheme)
        builder.setTitle("Create Channel")
        builder.setView(dialogView)
        builder.setPositiveButton("Create") { dialog, _ ->
            // Здесь вы можете вызвать метод ViewModel для создания канала
            //viewModel.createChannel(channelName, channelDescription)
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}