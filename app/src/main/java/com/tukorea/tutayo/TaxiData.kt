package com.tukorea.tutayo

import com.google.type.Date
import java.sql.Timestamp
import java.time.LocalDateTime

class TaxiData(
    var docId: String = "",         //문서 id
    var kakaoUserId: Long = 0,      //작성자 id
    var gender: String = "",        //작성자 성별
    var restriction: Int = 0, //성별 제한 - 상관 없음 0, 동성만 1
    var position: Int = 0,           //역 번호
    var entranceNum: Int = 0,       //출구번호
    var departure_hour: Int = 0,          //출발 시간
    var departure_minute: Int = 0,         //출발 분
    var maxNum: Int = 0,            //최대인원
    var memo: String = "",          //메모
    var shareMember: List<String> = emptyList(),  //합승 명단
    var requestUser: List<String> = emptyList(),  //합승 요청 명단
    var uploadTime: Long = 0        //업로드시간
)