package com.tukorea.tutayo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.taxi_fragment_jeongwang.*
import kotlinx.android.synthetic.main.taxi_share_dialog.*
import kotlinx.android.synthetic.main.taxi_share_item.view.*
import java.util.Arrays.stream
import java.util.stream.Collectors

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JeongwangFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JeongwangFragment : Fragment() { //기본 탭
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var taxiActivity : TaxiActivity
    private val db = Firebase.firestore
    private var firestore : FirebaseFirestore? = null

    var userId = null
    var gender = null


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
        return inflater.inflate(R.layout.taxi_fragment_jeongwang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()
        jw_recycler.adapter = JwRecyclerViewAdapter()
        jw_recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        taxiActivity = context as TaxiActivity
    }

    override fun onStart() {
        super.onStart()



    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JeongwangFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JeongwangFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    inner class JwRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var jwTaxiData : ArrayList<TaxiData> = arrayListOf()
        private var context : Context = requireContext()

        //DB에 저장된 문서를 불러와 TaxiData로 변환한 뒤 jwTaxiData라는 리스트에 담음
        init {

            firestore?.collection("jwTaxiShare")?.addSnapshotListener {
                    querySnapshot, firebaseFirestoreException ->
                jwTaxiData.clear()

                if(querySnapshot != null) {
                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(TaxiData::class.java)
                        item!!.docId = snapshot.id
                        jwTaxiData.add(item!!)
                    }
                    notifyDataSetChanged() //업데이트
                }
                else Log.i("TAG","querySnapshot : null")
                var sortedJwTaxiData = jwTaxiData.sortedBy { it.uploadTime }

                jwTaxiData = ArrayList(sortedJwTaxiData.reversed())
            }
        }


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

        //xml파일 inflate해 ViewHolder 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.taxi_share_item, parent, false)
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView

            var hour = jwTaxiData[position].departure_hour.toString().toInt()

            if(hour >= 12) viewHolder.item_ampm.text = "오후"
            else viewHolder.item_ampm.text = "오전"

            //출발 시간 12시간 단위로 변환하여 설정
            if(hour > 12) viewHolder.item_departure_hour.text = (hour - 12).toString()
            else if(hour == 0) viewHolder.item_departure_hour.text = "12"
            else viewHolder.item_departure_hour.text = hour.toString()

            viewHolder.item_departure_minute.text = jwTaxiData[position].departure_minute.toString()

            when(jwTaxiData[position].position) {
                0 -> viewHolder.item_position.text = "정왕"
                1 -> viewHolder.item_position.text = "오이도"
            }
            viewHolder.item_entrance.text = jwTaxiData[position].entranceNum.toString()
            viewHolder.item_current_num.text = jwTaxiData[position].shareMember.size.toString()
            viewHolder.item_max_num.text = jwTaxiData[position].maxNum.toString()

            if(jwTaxiData[position].restriction == 0) {  //성별 제한이 없는 경우
            }
            else if (jwTaxiData[position].restriction == 1 && jwTaxiData[position].gender == "MALE") { // 동성만 제한하고 작성자가 남자인 경우
                viewHolder.item_female.visibility = View.GONE

            }
            else { // 동성만 제한하고 작성자가 여자인 경우
                viewHolder.item_male.visibility = View.GONE
            }

            viewHolder.setOnClickListener() {
                var dlg = taxiShareDialog(context,jwTaxiData[position])
                dlg.showDialog()
            }
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        override fun getItemCount(): Int {
            return jwTaxiData.size
        }
    }

    inner class taxiShareDialog(context: Context, taxiItem: TaxiData) {
        private var dialog = Dialog(context)


        init {
            dialog.setContentView(R.layout.taxi_share_dialog)

            //12시 단위로 변환
            if(taxiItem.departure_hour > 12) {
                dialog.detail_ampm.text = "오후"
                dialog.detail_departure_hour.text = (taxiItem.departure_hour - 12).toString()

            }
            else if(taxiItem.departure_hour == 0) { //자정
                dialog.detail_ampm.text = "오전"
                dialog.detail_departure_hour.text = "12"
            }
            else {
                dialog.detail_ampm.text = "오전"
                dialog.detail_departure_hour.text = taxiItem.departure_hour.toString()
            }

            dialog.detail_departure_minute.text = taxiItem.departure_minute.toString()

            if(taxiItem.position == 0) dialog.detail_position.text = "정왕"
            else dialog.detail_position.text = "오이도"

            dialog.detail_entrance.text = taxiItem.entranceNum.toString()

            if(taxiItem.restriction == 0) {  //성별 제한이 없는 경우
            }
            else if (taxiItem.restriction == 1 && taxiItem.gender == "MALE") { // 동성만 제한하고 작성자가 남자인 경우
                dialog.detail_female.visibility = View.GONE

            }
            else { // 동성만 제한하고 작성자가 여자인 경우
                dialog.detail_male.visibility = View.GONE
            }

            dialog.detail_memo.text = taxiItem.memo
            dialog.detail_currentNum.text = taxiItem.shareMember.size.toString()
            dialog.detail_maxNum.text = taxiItem.maxNum.toString()

            /*
            *
            * 합승 확정 리스트 작성하기
            *
            * */

            UserApiClient.instance.me { user, error ->

                //사용자 정보
                var userId = user?.id
                var gender = user?.kakaoAccount?.gender.toString()

                Log.i("TAG", "JW Frgment - user info: ${userId}, ${gender}")

            }

            if(taxiItem.kakaoUserId == userId) { //내가 작성한 글이면 삭제 버튼 보이고 요청 버튼 가림
                dialog.detail_reqBtn.visibility = View.GONE
                dialog.detail_deleteBtn.visibility = View.VISIBLE
            }
            else { //내가 작성한 글이 아니면 삭제 버튼 안보이고 요청 버튼 보임
                dialog.detail_deleteBtn.visibility = View.GONE
                dialog.detail_reqBtn.visibility = View.VISIBLE
            }

            //요청 버튼
            dialog.detail_reqBtn.setOnClickListener {
                Log.i("TAG","요청 버튼 클릭")
            }

            //삭제 버튼
            dialog.detail_deleteBtn.setOnClickListener {
                var dlg = AlertDialog.Builder(context)

                if(taxiItem.shareMember.isNotEmpty()) { //합승이 확정된 사람이 한 명이라도 있으면
                    dlg.setMessage("삭제 하시겠습니까?\n예정된 합승자가 있습니다.")
                }
                else {
                    dlg.setMessage("삭제 하시겠습니까?\n예정된 합승자가 없습니다.")
                }

                dlg.setNegativeButton("취소", null)
                dlg.setPositiveButton("삭제") { dlg, which ->
                    db.collection("jwTaxiShare").document(taxiItem.docId).delete()
                    Toast.makeText(getContext(),"게시글이 삭제되었습니다",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                dlg.show()
            }

            //닫기 버튼
            dialog.detail_cancelBtn.setOnClickListener {
                dialog.dismiss()
            }
        }

        fun showDialog() {
            dialog.show()
        }
    }

}