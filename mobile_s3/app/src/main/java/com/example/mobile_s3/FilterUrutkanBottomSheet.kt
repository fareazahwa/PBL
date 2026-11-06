package com.example.mobile_s3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterUrutkanBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sheet_filter_urutkan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnResetAll = view.findViewById<TextView>(R.id.btnResetAll)
        val btnApplyFilter = view.findViewById<TextView>(R.id.btnApplyFilter)
        val switchCatalog = view.findViewById<Switch>(R.id.switchCatalog)

        btnResetAll?.setOnClickListener {
            switchCatalog?.isChecked = false
            Toast.makeText(context, "Semua filter telah diatur ulang", Toast.LENGTH_SHORT).show()
        }

        btnApplyFilter?.setOnClickListener {
            Toast.makeText(context, "Filter berhasil diterapkan", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    companion object {
        const val TAG = "FilterUrutkanBottomSheet"
        fun newInstance(): FilterUrutkanBottomSheet = FilterUrutkanBottomSheet()
    }
}

