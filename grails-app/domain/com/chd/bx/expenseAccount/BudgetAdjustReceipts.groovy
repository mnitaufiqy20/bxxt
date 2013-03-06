package com.chd.bx.expenseAccount

class BudgetAdjustReceipts {
    String budgetAdjustReceiptsId                 // 单号

    static constraints = {
    }
    static mapping = {
        table 'TT_BUD_ADJ_RECE'
        id generator:'sequence', params:[sequence:'SEQ_TT_BUD_ADJ_RECE_ID']
    }
}
