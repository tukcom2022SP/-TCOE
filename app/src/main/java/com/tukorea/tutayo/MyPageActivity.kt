package com.tukorea.tutayo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.mypage_activity.*
import kotlinx.android.synthetic.main.taxi_activity.*
import kotlinx.android.synthetic.main.taxi_fragment_jeongwang.*
import java.security.AccessController.getContext
import java.text.FieldPosition

class MyPageActivity : AppCompatActivity() {

    lateinit var taxiActivity: TaxiActivity
    private var firestore: FirebaseFirestore? = null
    lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)
            image = findViewById(R.id.img)


        UserApiClient.instance.me { user, error ->
            id.text = "${user?.id}"
            name.text = "${user?.kakaoAccount?.profile?.nickname}"
            email.text = "${user?.kakaoAccount?.email}"
            gender.text = "${user?.kakaoAccount?.gender}"
            if(gender.text=="FEMALE"){
                image.setImageResource(R.drawable.aunt)
            }
            /*else if(gender.text =="MALE"){
                image.setImageResource(R.drawable.uncle)

            }*/
        }


        firestore = FirebaseFirestore.getInstance()
        var dlg = myDialog(this)
        var myRecyclerAdapter = myRecyclerViewAdapter(this, dlg)

        mygeul.adapter = myRecyclerAdapter
        mygeul.layoutManager = LinearLayoutManager(this)

    }

    inner class myDialog(context: Context){
        var dialog = Dialog(context)

        fun showDialog(){
            dialog.show()
        }

        fun setData(context: Context, reqItem: TaxiData){

        }
    }

    inner class myRecyclerViewAdapter(context: Context, dlg : myDialog): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        private var mytaxidata : ArrayList<TaxiData> = arrayListOf()
        private var context : Context = context
        private var data : TaxiData? = null
        private var dialog = dlg

        val intent = getIntent()!!
        var userId = intent.getLongExtra("user_id", 0)

        init{
            firestore?.collection("jwTaxiShare")?.addSnapshotListener { querySnapShot, firebaseFireStoreException ->
                mytaxidata.clear() //내 글 리스트를 비워줌

                if(querySnapShot != null){
                    for(snapshot in querySnapShot.documents){
                        var item = snapshot.toObject(TaxiData::class.java)
                        if(item!!.kakaoUserId == userId){
                            mytaxidata.add(item!!)
                        }

                    }
                }
                notifyDataSetChanged()
                Log.i("TAG", "mypage userID: ${userId}")

            }


            firestore?.collection("oidoTaxiShare")?.addSnapshotListener { querySnapShot, firebaseFireStoreException ->
                mytaxidata.clear() //내 글 리스트를 비워줌

                if(querySnapShot != null){
                    for(snapshot in querySnapShot.documents){
                        var item = snapshot.toObject(TaxiData::class.java)
                        item!!.docId = snapshot.id
                        if(item!!.kakaoUserId == userId) {
                            mytaxidata.add(item!!)
                        }
                    }
                }
                notifyDataSetChanged()
            }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        }

        override fun onCreateViewHolder(parent : ViewGroup, viewType : Int):RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.taxi_share_item, parent, false)
            return ViewHolder(view)


        }

        //onCreateViewHolder에서 만든 뷰와 실제 데이터를 연결한다.
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView

            viewHolder.setOnClickListener(){
                data = mytaxidata[position]
                dialog.setData(context, mytaxidata[position])


            }

        }

        override fun getItemCount(): Int {
            return mytaxidata.size
        }
    }








}


