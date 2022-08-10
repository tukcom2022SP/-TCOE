package com.tukorea.tutayo

import android.content.Context
import android.os.Bundle
import android.util.Half
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.taxi_fragment_add.*
import java.time.LocalDateTime
import java.time.temporal.ChronoField
import java.util.*
import kotlin.collections.HashMap


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

    private var db = Firebase.firestore
    private var firestore : FirebaseFirestore? = null

    val JEONGWANG = 0
    val OIDO = 1
    val ANY_GENDER = 0
    val SAME_GENDER = 1

    private lateinit var taxiActivity: TaxiActivity

    //DB에 저장될 새 글 데이터


    val date: LocalDateTime = LocalDateTime.now()   //현재 시간
    var station: Int = JEONGWANG    //출발 역
    var entrance: Int = 1           //출발 출구
    var genderRest = ANY_GENDER     //성별 제한

    //현재 시간을 기본 출발 시간으로 설정정
    var departureHr = date.get(ChronoField.HOUR_OF_DAY)
    var departureMin = date.get(ChronoField.MINUTE_OF_DAY) - 60 * date.get(ChronoField.HOUR_OF_DAY)

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
        clearAll()

        var userId: Long? = arguments?.getLong("user_id")          //작성자 id
        var gender: String? = arguments?.getString("user_gender")  //작성자 성별
        Log.i("TAG","kakao userId: ${userId}, gender: ${gender} login")

        //파이어베이스
        firestore = FirebaseFirestore.getInstance()

        //역 선택 라디오버튼 리스너
        position.setOnCheckedChangeListener { radioGroup, checkedId ->
            if(rb_jeongwang.isChecked) location_spinner.visibility = View.INVISIBLE
            else location_spinner.visibility = View.VISIBLE

            when(checkedId){
                R.id.rb_jeongwang -> {
                    station = JEONGWANG
                    entrance = 2 //정왕역 출구번호 2번 고정
                }
                R.id.rb_oido -> {
                    station = OIDO
                }
                else -> Log.i("TAG","newTaxi: position set error")
            }
            Log.i("TAG","position: ${position}")
        }

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

        //출구 선택 스피너 리스너
        location_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                    entrance = pos + 1 //출구 번호 저장
                }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        departure_timePicker.setOnTimeChangedListener { timePicker, hr, min ->
            departureHr = hr
            departureMin = min
        }

        //출발 시간 변경 버튼 클릭시 동작
        set_departure_time_btn.setOnClickListener {

            if(set_departure_time_btn.text.toString() == "변경") { //버튼이 변경 버튼일 경우
                set_departure_time_btn.setText("설정")
                departure_timePicker.visibility = View.VISIBLE
            }
            else { //버튼이 설정 버튼일 경우
                Log.i("TAG","timePicker set: ${departureHr}시 ${departureMin}분 설정")
                set_departure_time_btn.setText("변경")
                departure_timePicker.visibility = View.GONE

                setDepartureTime(departureHr, departureMin)
            }
        }

        //성별 제한 선택 라디오버튼 리스너
        gender_restriction.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.rb_any_gender -> genderRest = ANY_GENDER
                R.id.rb_same_gender -> genderRest = SAME_GENDER
                else -> Log.i("TAG", "newTaxi: gender restriction set error")
            }
            Log.i("TAG","genderRest: ${genderRest}")
        }

        //최대 탑승 인원 빼기
        minusBtn.setOnClickListener {
            var nowMax = maxNum_EditTxt.text.toString().toInt()
            if(nowMax > 1) nowMax -= 1
            else Toast.makeText(taxiActivity, "최대 인원은 한 명 미만으로 설정할 수 없습니다",Toast.LENGTH_SHORT).show()
            maxNum_EditTxt.setText(nowMax.toString())
        }

        //최대 탑승 인원 더하기
        plusBtn.setOnClickListener {
            var nowMax = maxNum_EditTxt.text.toString().toInt()
            if(nowMax < 5) nowMax += 1
            else Toast.makeText(taxiActivity, "최대 인원은 5명을 초과하여 설정할 수 없습니다",Toast.LENGTH_SHORT).show()
            maxNum_EditTxt.setText(nowMax.toString())
        }

        //초기화 버튼 클릭시 작성한 내용 리셋
        resetBtn.setOnClickListener {
            clearAll()
        }

        //작성 취소하기
        cancelBtn.setOnClickListener {
            (activity as TaxiActivity).toJFragment()
        }

        //게시글 작성 후 제출 - 파이어스토어에 게시글 정보 추가
        submitBtn.setOnClickListener {

            if(position.checkedRadioButtonId == -1 || gender_restriction.checkedRadioButtonId == -1) { //라디오버튼 선택하지 않으면 제출 불가
                Toast.makeText(taxiActivity, "위치와 성별 제한을 모두 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else { //모두 작성 완료한 경우 제출 가능

                //해시맵 형태로 데이터베이스에 add
               val share = hashMapOf(
                   "kakaoUserId" to userId,                     //작성자 id
                   "gender" to gender,                          //작성자 성별
                   "uploadTime" to System.currentTimeMillis().toString().toLong(),  //업로드 시간
                   "position" to station,                       //출발 역
                   "entranceNum" to entrance,                   //출구 번호
                   "restriction" to genderRest,                 //성별 제한
                   "departure_hour" to departureHr,             //출발 시간
                   "departure_minute" to departureMin,          //출발 분
                   "maxNum" to maxNum_EditTxt.text.toString().toInt(),      //최대 탑승 인원
                   "memo" to newtaxi_memo.text.toString(),                  //간단 메모
                   "shareList" to emptyList<String>(),                      //합승 명단이 저장될 리스트
                   "shareReqList" to emptyList<String>()                    //합승 요청 명단
               )

                //DB에 저장장
               when(station) {
                    JEONGWANG -> { //정왕역 선택시 정왕역 컬렉션에 저장
                        addShareData("jwTaxiShare", share)
                        (activity as TaxiActivity).toJFragment() //게시글 페이지로 이동
                    }
                    OIDO -> { //오이도역 선택시 오이도역 컬렉션에 저장
                        addShareData("oidoTaxiShare", share)
                        (activity as TaxiActivity).toOFragment() //게시글 페이지로 이동
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        taxiActivity = context as TaxiActivity
    }

    //출발 시간 설정
    private fun setDepartureTime(hour: Int, min: Int) {

        //24시단위를 12시단위로 변환
        if(hour > 12) departure_hour.setText("${hour - 12}")
        else if(hour > 0) departure_hour.setText("${hour}")
        else departure_hour.setText("12")
        departure_minute.setText("${min}")

        //오전 오후 설정
        if(departureHr >= 0 && departureHr <= 12) am_or_pm.setText("오전")
        else am_or_pm.setText("오후")
    }

    //작성 내용 초기화
    private fun clearAll() {
        position.clearCheck()
        gender_restriction.clearCheck()
        setDepartureTime(date.get(ChronoField.HOUR_OF_DAY),
            date.get(ChronoField.MINUTE_OF_DAY) - 60 * date.get(ChronoField.HOUR_OF_DAY))
        newtaxi_memo.setText("")
        location_spinner.visibility = View.INVISIBLE
    }

    //DB에 데이터 추가
    private fun addShareData(collectionPath: String, share: HashMap<String, Any?>) {
        //DB에 문서 추가 -
        db.collection(collectionPath)
            .add(share)
            .addOnSuccessListener { documentReference ->
                // 제출 성공 시
                Log.d("FIREBASE", "DocumentSnapshot added. ID: ${documentReference}, CollectionPath: ${collectionPath}")
                Toast.makeText(taxiActivity, "새 글이 작성되었습니다", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                //제출 실패 시
                Log.w("FIREBASE", "Error adding document", e)
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