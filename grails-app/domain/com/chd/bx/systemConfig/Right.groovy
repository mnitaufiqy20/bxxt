package com.chd.bx.systemConfig

class Right {
    String rightCode
    String rightName

    static constraints = {
        rightCode nullable: false,maxSize: 10
        rightName         nullable: false,maxSize: 20
    }
    static mapping = {
        table 'TS_RIGHT'
        id generator:'sequence', params:[sequence:'SEQ_TS_RIGHT_ID']
    }
}
