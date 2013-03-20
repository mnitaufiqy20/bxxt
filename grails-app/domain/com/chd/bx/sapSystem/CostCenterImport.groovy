package com.chd.bx.sapSystem

class CostCenterImport {
    String companyCode;//公司代码
    String costCenterNo;//成本中心编码
    String costCenterDes;//成本中心描述
    static constraints = {
        companyCode nullable: false, maxSize: 4
        costCenterNo nullable: false, maxSize: 10
        costCenterDes nullable: false, maxSize: 35
    }
    static mapping = {
        table 'TI_COST_CENTER_IMPORT'
        id generator:'sequence', params:[sequence:'SEQ_TI_COST_CENTER_IMPORT_ID']
    }
}
