package com.tukorea.tutayo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.taxi_fragment_new.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaxiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaxiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var JFragment : Fragment
    private lateinit var OFragment : Fragment
    private lateinit var transaction : FragmentTransaction
    private lateinit var viewPagerFragment : ViewPagerFragment
    private var firestore : FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.taxi_fragment_new, container, false)
    }

    override fun onStart() {
        super.onStart()

        firestore = FirebaseFirestore.getInstance()

        //출구 선택 스피너
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            location_spinner.adapter = adapter
        }

        //출구 번호 스피너는 오디도역 선택시에만 보이도록 함
        position.setOnCheckedChangeListener { compoundButton, b ->
            if(rb_jeongwang.isChecked) location_spinner.visibility = View.INVISIBLE
            else location_spinner.visibility = View.VISIBLE
        }

        //초기화 버튼 클릭시 작성한 내용 리셋
        resetBtn.setOnClickListener {
            position.clearCheck()
            sex_restriction.clearCheck()
            departure_hour.setText("00")
            departure_minute.setText("00")
            am_or_pm.setText("am")
            newtaxi_memo.setText("")
            location_spinner.visibility = View.INVISIBLE
        }

        //작성 취소하기
        cancelBtn.setOnClickListener {
            (activity as TaxiActivity).toJFragment()
        }






    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewTaxiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewTaxiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}