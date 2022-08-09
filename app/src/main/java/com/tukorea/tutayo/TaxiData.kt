package com.tukorea.tutayo

import java.sql.Timestamp

class TaxiData(
    var docId: String = "",         //문서 id
    var kakaoUserId: Long = 0,      //작성자 id
    var uploadTime: String,      //업로드시간
    var sex: Int = 0,               //작성자 성별
    var restriction: Int = 0,       //성별 제한 - 상관 없음 0, 동성만 1
    var entranceNum: Int = 0,       //출구번호
    var maxNum: Int = 0,            //최대인원
  /*  var shareMember: List<String>,  //합승 명단
    var requestUser: List<String>   //합승 요청 명단*/
)
