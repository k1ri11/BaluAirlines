package com.company.baluairlines.buy_ticket_feature.presentation.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]
        return DatePickerDialog(
            requireActivity(),
            this,
            year,
            month,
            day
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(
            requireContext(),
            "Date-DD/MM/YYYY: " + dayOfMonth + "-" + (month + 1) + "-" + year,
            Toast.LENGTH_SHORT
        ).show()
    }
}


//class Pickers : AppCompatActivity() {
//
//    var dialogTheme: Int? = null
//    var fullscreenTheme: Int? = null
//    var picker: MaterialDatePicker<*>? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_pickers)
//        fullscreenTheme = resolveOrThrow(this, R.attr.materialCalendarFullscreenTheme)
//        dialogTheme = resolveOrThrow(this, R.attr.materialCalendarTheme)
//    }
//
//
//
//    //Full Screen Date Range Picker
//    fun openFullDateRangePicker(view: View) {
//        val inputMode = MaterialDatePicker.INPUT_MODE_CALENDAR
//        val builder: MaterialDatePicker.Builder<Pair<Long, Long>> = MaterialDatePicker.Builder.dateRangePicker()
//        builder.setInputMode(inputMode)
//        builder.setTheme(fullscreenTheme!!)
//        picker = builder.build()
//        picker?.show(supportFragmentManager, picker.toString())
//        addListener(picker!!)
//    }
//
//
//    private fun addListener(picker: MaterialDatePicker<*>) {
//        picker.addOnPositiveButtonClickListener {
//            Toast(this, picker.headerText.toString(), Toast.LENGTH_SHORT)
//        }
//        picker.addOnNegativeButtonClickListener {
//            AppUtils.showToast(this, picker.headerText.toString(), Toast.LENGTH_SHORT)
//        }
//        picker.addOnCancelListener {
//            AppUtils.showToast(this, picker.headerText.toString(), Toast.LENGTH_SHORT)
//        }
//    }
//
//    private fun resolveOrThrow(context: Context, @AttrRes attributeResId: Int): Int {
//        val typedValue = TypedValue()
//        if (context.theme.resolveAttribute(attributeResId, typedValue, true)) {
//            return typedValue.data
//        }
//        throw IllegalArgumentException(context.resources.getResourceName(attributeResId))
//    }
//
//}