package com.tukorea.tutayo

import java.sql.Timestamp

class TaxiData(
    uploadTime: Timestamp,   //업로드시간
    sex: Int,               //작성자 성별
    restriction: Int,       //성별 규제 - 동성만 0, 상관없음 1
    entranceNum: Int,       //출구번호
    maxNum: Int,            //최대인원
    currentNum: Int         //현재인원 - 디폴트 0
)
