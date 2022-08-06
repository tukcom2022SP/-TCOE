package com.tukorea.tutayo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.taxi_fragment_add.*


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
    private var db = Firebase.firestore
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
        return inflater.inflate(R.layout.taxi_fragment_add, container, false)
    }

    override fun onStart() {
        super.onStart()

        Log.i("TAG", "checked radio button id : ${position.checkedRadioButtonId}")

        //taxi 액티비티에서 받아온 사용자 정보
        var userId = arguments?.getLong("user_id")
        var gender = arguments?.getString("user_gender")
        Log.i("TAG","kakao userId: ${userId}, gender: ${gender}")

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

        //출구 번호 스피너는 오이도역 선택시에만 보이도록 함
        position.setOnCheckedChangeListener { compoundButton, b ->
            if(rb_jeongwang.isChecked) location_spinner.visibility = View.INVISIBLE
            else location_spinner.visibility = View.VISIBLE
        }

        minusBtn.setOnClickListener {
            var nowMax = maxNum_EditTxt.text.toString().toInt()
            if(nowMax > 1) nowMax -= 1
            maxNum_EditTxt.setText(nowMax.toString())
        }

        plusBtn.setOnClickListener {
            var nowMax = maxNum_EditTxt.text.toString().toInt()
            if(nowMax < 5) nowMax += 1
            maxNum_EditTxt.setText(nowMax.toString())
        }

        //초기화 버튼 클릭시 작성한 내용 리셋
        resetBtn.setOnClickListener {
            position.clearCheck()
            gender_restriction.clearCheck()
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

        //게시글 작성 후 제출 - 파이어스토어에 게시글 정보 추가
        submitBtn.setOnClickListener {
            if(false) { //메모를 제외한 모든 칸을 채우지 않으면 제출 불가
                //제출 불가능 알람 다이얼로그 띄우기
            }
            else { //모두 작성 완료한 경우 제출 가능

                //해시맵 형태로 데이터베이스에 add
               val share = hashMapOf(
                   "kakaoUserId" to userId,         //작성자 id
                   "gender" to gender,              //작성자 성별
                   "uploadTime" to Timestamp.now(), //업로드 시간
                   "position" to 0,                //출발 역
                   "entranceNum" to 0,              //출구 번호
                   "restriction" to 0,              //성별 제한
                   "departure_hour" to departure_hour.text.toString(),      //출발 시간
                   "departure_minute" to departure_minute.text.toString(),  //출발 분
                   "am_or_pm" to am_or_pm.text.toString(),                  //오전 오후
                   "maxNum" to 0,                           //최대 탑승 인원
                   "memo" to newtaxi_memo.text.toString(),  //간단 메모
                   "shareList" to emptyList<String>(),      //합승 명단이 저장될 리스트
                   "shareReqList" to emptyList<String>()    //합승 요청 명단
               )

                db.collection("taxiShare")
                    .add(share)
                    .addOnSuccessListener { documentReference ->
                        // 제출 성공 시
                        Log.d("FIREBASE", "DocumentSnapshot added. ID: ${documentReference}")
                    }
                    .addOnFailureListener { e ->
                        //제출 실패 시
                        Log.w("FIREBASE", "Error adding document", e)
                    }
                (activity as TaxiActivity).toJFragment() //게시글 페이지 이동

            }
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