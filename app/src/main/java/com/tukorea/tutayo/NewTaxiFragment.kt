package com.tukorea.tutayo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

            firestore = FirebaseFirestore.getInstance()

            //작성하던 글 초기화
            resetBtn.setOnClickListener {
                position.clearCheck()
                Log.d("tmp", "clicked radioBtn id: ${position.checkedRadioButtonId}")
                sex_restriction.clearCheck()
                departure_hour.setText("00")
                departure_minute.setText("00")
                am_or_pm.setText("am")
                newtaxi_memo.setText("")
            }

            //새 글 작성 버튼 클릭 이벤트
            submitBtn.setOnClickListener {

                //메모를 제외하고 작성하지 않은 부분이 존재하면 제출 불가
                //position.checkedRadioButtonId = null
            }


        }


//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.spinner_items,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            location_spinner.adapter = adapter
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.taxi_fragment_new, container, false)
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