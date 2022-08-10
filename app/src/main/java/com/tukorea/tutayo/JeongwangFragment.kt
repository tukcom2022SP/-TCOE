package com.tukorea.tutayo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.taxi_fragment_jeongwang.*
import kotlinx.android.synthetic.main.taxi_share_item.view.*

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
        return inflater.inflate(R.layout.taxi_fragment_jeongwang, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()
        jw_recycler.adapter = JwRecyclerViewAdapter()
        jw_recycler.layoutManager = LinearLayoutManager(context)

    }

    override fun onStart() {
        super.onStart()





    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        taxiActivity = context as TaxiActivity
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
        private var context : Context? = getContext()

        //DB에 저장된 문서를 불러와 TaxiData로 변환한 뒤 jwTaxiData라는 리스트에 담음
        init {
            firestore?.collection("jwTaxiShare")?.addSnapshotListener {
                    querySnapshot, firebaseFirestoreException ->
                jwTaxiData.clear()

                if(querySnapshot != null) {
                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(TaxiData::class.java)
                        jwTaxiData.add(item!!)
                    }
                    notifyDataSetChanged() //업데이트
                }
                else Log.i("TAG","querySnapshot : null")

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

            viewHolder.item_ampm.text = "임시"
            viewHolder.item_departure_hour.text = jwTaxiData[position].departHr.toString()
            viewHolder.item_departure_minute.text = jwTaxiData[position].departMin.toString()
            viewHolder.item_max_num.text = jwTaxiData[position].maxNum.toString()
            viewHolder.item_current_num.text = jwTaxiData[position].shareMember.size.toString()
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

}