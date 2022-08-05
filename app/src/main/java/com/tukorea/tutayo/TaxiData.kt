package com.tukorea.tutayo

import java.sql.Timestamp

class TaxiData(
    var docId: String = "",         //문서 id
    var kakaoUserId: String = "",   //작성자 id
    var uploadTime: Timestamp,      //업로드시간
    var sex: Int,                   //작성자 성별
    var restriction: Int,           //성별 규제 - 동성만 0, 상관없음 1
    var entranceNum: Int,           //출구번호
    var maxNum: Int,                //최대인원
    var shareMember: List<String>,  //합승 명단
    var requestUser: List<String>   //합승 요청 명단
)
