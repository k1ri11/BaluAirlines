package com.company.baluairlines.buy_ticket_feature.presentation.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentSearchTicketsBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import java.text.SimpleDateFormat
import java.util.*


class SearchTicketFragment : Fragment(), MenuProvider {

    private var _binding: FragmentSearchTicketsBinding? = null
    private val binding get() = _binding!!

    private var passengers = intArrayOf(1, 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchTicketsBinding.inflate(inflater, container, false)
        setupListeners()
        updatePassengersUICounter()
        updateDateField(dateLongToString(Calendar.getInstance().timeInMillis), "")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

    override fun onMenuItemSelected(menuItem: MenuItem) = true

    private fun setupListeners() {
        setSwapButtonListener()
        setupPassengerListener()
        setupDateListener()
        setupCloseIconListener()
        setupClassButtonListener()
        setupFindButtonListener()
    }

    private fun setupFindButtonListener() {
        binding.findButton.setOnClickListener {
            if (passengers.sum() <= 0){
                Toast.makeText(requireContext(), "Укажите большее количество пассажиров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val departureAirport = binding.departureEditText.text.toString()
            val arrivalAirport = binding.arrivalEditText.text.toString()
            val serviceClass = if(binding.businessButton.isChecked) "Business"  else "Economy"
            if (departureAirport.isEmpty() || arrivalAirport.isEmpty()) {
                Toast.makeText(requireContext(), "Введите аэропорт отправления и прибытия", Toast.LENGTH_SHORT).show()
            } else {
                val action = SearchTicketFragmentDirections.searchTicketFragmentToSearchResultFragment(
                    //todo допилить обратную дату
                        date = binding.departureDate.text.toString(),
                        departureAirport = departureAirport,
                        arrivalAirport = arrivalAirport,
                        serviceClass = serviceClass,
                        passengers = passengers.sum(),
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun setupPassengerListener() {
        binding.passenger.setOnClickListener {

            val bottomSheetDialog = PassengerBottomSheet()
            bottomSheetDialog.setPassengers(passengers)
            bottomSheetDialog.showNow(childFragmentManager, "bottomSheetDialog")
            bottomSheetDialog.dialog?.setOnDismissListener {
                passengers = bottomSheetDialog.getTotalPassengers()
                updatePassengersUICounter()
                //баг с появлением фрагментов при переходе на другой экран и возврате обратно
            }
        }
    }

    private fun updatePassengersUICounter() {
        binding.passenger.text =
            resources.getString(R.string.passenger_quantity_number, passengers.sum())
    }

    private fun setupDateListener() {
        binding.backDate.setOnClickListener {
            openFullDateRangePicker()
        }
    }

    /**
     * method for openning full date picker
     */
    private fun openFullDateRangePicker() {
        val builder = dateRangePicker()
        val datePicker = builder.build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val departureDate = selection.first
            val backDate = selection.second
            val departureDateString = departureDate?.let { dateLongToString(it) } ?: ""
            val backDateString = backDate?.let { dateLongToString(it) } ?: ""
            updateDateField(departureDateString, backDateString)
        }
        datePicker.addOnNegativeButtonClickListener {
            val departureDate = datePicker.selection?.first
            val backDate = datePicker.selection?.second
            val departureDateString = departureDate?.let { dateLongToString(it) } ?: ""
            val backDateString = backDate?.let { dateLongToString(it) } ?: ""
            updateDateField(departureDateString, backDateString)
        }
        datePicker.addOnCancelListener {
            val departureDate = datePicker.selection?.first
            val backDate = datePicker.selection?.second
            val departureDateString = departureDate?.let { dateLongToString(it) } ?: ""
            val backDateString = backDate?.let { dateLongToString(it) } ?: ""
            updateDateField(departureDateString, backDateString)
        }
        datePicker.show(childFragmentManager, datePicker.toString())
    }

    private fun updateDateField(departureDate: String, backDate: String) {
        if (departureDate.isNotEmpty()) {
            binding.departureDate.text = departureDate
        }
        if (backDate.isEmpty()) {
            binding.backDate.text = resources.getString(R.string.back_hint)
            binding.backCalendarIcon.setImageResource(R.drawable.calendar_icon)
        } else {
            binding.backDate.text = backDate
            binding.backCalendarIcon.setImageResource(R.drawable.close_icon)
        }
    }

    private fun dateLongToString(date: Long): String {
        val format = "dd.MM.yyyy"
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun setupCloseIconListener() {
        binding.backCalendarIcon.setOnClickListener {
            binding.backDate.text = resources.getString(R.string.back_hint)
            binding.backCalendarIcon.setImageResource(R.drawable.calendar_icon)
        }
    }

    private fun setSwapButtonListener() {
        binding.apply {
            swapButton.setOnClickListener {
                val tmp = departureEditText.text.toString()
                departureEditText.setText(arrivalEditText.text.toString())
                arrivalEditText.setText(tmp)
            }
        }
    }

    private fun setupClassButtonListener() {
        binding.economyButton.isChecked = true
        binding.economyButton.addOnCheckedChangeListener { button, isChecked ->
            toggleButton(button, isChecked)
        }
        binding.businessButton.addOnCheckedChangeListener { button, isChecked ->
            toggleButton(button, isChecked)
        }
    }

    private fun toggleButton(button: MaterialButton, isChecked: Boolean) {
        if (isChecked) {
            button.setBackgroundColor(requireContext().getColor(R.color.background_fill_dark))
        } else {
            button.setBackgroundColor(requireContext().getColor(R.color.background_primary_dark))
        }
    }

}