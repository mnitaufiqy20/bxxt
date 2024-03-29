import com.chd.bx.security.Menu
import com.chd.bx.security.MenuCategory
import com.chd.bx.security.Role
import com.chd.bx.security.User

class BootStrap {

    def init = { servletContext ->
        //初始化用户数据
        def adminRole = new Role(id:1,authority: '系统管理员',description:'报销系统超级管理员').save(flush: true)
        def adminRole1 = new Role(id: 2,authority: '报销单－申请人－公司领导',description:'BXSQ',roleCode:'S0001').save(flush: true)
        def adminRole2 = new Role(id: 3,authority: '报销单－申请人－公司分管领导',description:'BXSQ',roleCode:'S0002').save(flush: true)
        def adminRole3 = new Role(id: 4,authority: '报销单－申请人－公司分管部门领导',description:'BXSQ',roleCode:'S0003').save(flush: true)
        def adminRole4 = new Role(id: 5,authority: '报销单－申请人－部门领导',description:'BXSQ',roleCode:'S0004').save(flush: true)
        def adminRole5 = new Role(id: 6,authority: '报销单－申请人－员工',description:'BXSQ',roleCode:'S0005').save(flush: true)
        def adminRole6 = new Role(id: 7,authority: '报销单－审批人－公司领导',description:'BXSP',roleCode:'S0006').save(flush: true)
        def adminRole7 = new Role(id: 8,authority: '报销单－审批人－公司分管领导',description:'BXSP',roleCode:'S0007').save(flush: true)
        def adminRole8 = new Role(id: 9,authority: '报销单－审批人－公司分管部门领导',description:'BXSP',roleCode:'S0008').save(flush: true)
        def adminRole9 = new Role(id: 10,authority: '报销单－审批人－部门领导',description:'BXSP',roleCode:'S0009').save(flush: true)
        def adminRole10 = new Role(id: 11,authority: '报销单－过账会计－财务会计',description:'BXKJ',roleCode:'S0010').save(flush: true)
        def adminRole11 = new Role(id: 12,authority: '报销单－付款出纳－财务出纳',description:'BXCN',roleCode:'S0011').save(flush: true)
        def adminRole12 = new Role(id: 13,authority: '借款单－申请人－公司领导',description:'JKSQ',roleCode:'S0012').save(flush: true)
        def adminRole13 = new Role(id: 14,authority: '借款单－申请人－公司分管领导',description:'JKSQ',roleCode:'S0013').save(flush: true)
        def adminRole14 = new Role(id: 15,authority: '借款单－申请人－公司分管部门领导',description:'JKSQ',roleCode:'S0014').save(flush: true)
        def adminRole15 = new Role(id: 16,authority: '借款单－申请人－部门领导',description:'JKSQ',roleCode:'S0015').save(flush: true)
        def adminRole16 = new Role(id: 17,authority: '借款单－申请人－员工',description:'JKSQ',roleCode:'S0016').save(flush: true)
        def adminRole17 = new Role(id: 18,authority: '借款单－审批人－公司领导',description:'JKSP',roleCode:'S0017').save(flush: true)
        def adminRole18 = new Role(id: 19,authority: '借款单－审批人－公司分管领导',description:'JKSP',roleCode:'S0018').save(flush: true)
        def adminRole19 = new Role(id: 20,authority: '借款单－审批人－公司分管部门领导',description:'JKSP',roleCode:'S0019').save(flush: true)
        def adminRole20 = new Role(id: 21,authority: '借款单－审批人－部门领导',description:'JKSP',roleCode:'S0020').save(flush: true)
        def adminRole21 = new Role(id: 22,authority: '借款单－过账会计－财务会计',description:'JKKJ',roleCode:'S0021').save(flush: true)
        def adminRole22 = new Role(id: 23,authority: '借款单－付款出纳－财务出纳',description:'JKCN',roleCode:'S0022').save(flush: true)
        def testUser = new User(id:1,username: 'zhangsan1',
                password: '1',
                name: '张三1',
                telephone: '13011111991',
                idNumber: '123451289012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '41110343',
                empPosition: '总监',
                userId: '0',
                role:adminRole,
                enabled: true)
        testUser.save(flush: true)
        def testUser1 = new User(id:2,username: 'zhangsan',
                password: '1',
                name: '张三',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '1',
                role:adminRole1,
                enabled: true)
        testUser1.save(flush: true)
        def testUser2 = new User(id:3,username: 'lisi',
                password: '1',
                name: '李四',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '2',
                role:adminRole2,
                enabled: true)
        testUser2.save(flush: true)
        def testUser3 = new User(id:4 ,username: 'wangwu',
                password: '1',
                name: '王五',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '3',
                role:adminRole3,
                enabled: true)
        testUser3.save(flush: true)
        def testUser4 = new User(id:5 ,username: 'zhaoliu',
                password: '1',
                name: '赵六',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '4',
                role:adminRole4,
                enabled: true)
        testUser4.save(flush: true)
        def testUser5 = new User(id:6 ,username: 'aijia',
                password: '1',
                name: '艾嘉',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '5',
                role:adminRole5,
                enabled: true)
        testUser5.save(flush: true)
        def testUser6 = new User(id:7 ,username: 'chenchao',
                password: '1',
                name: '陈超',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '6',
                role:adminRole6,
                enabled: true)
        testUser6.save(flush: true)

        def testUser7 = new User(id:8,username: 'zhouying',
                password: '1',
                name: '周颖',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '7',
                role:adminRole7,
                enabled: true)
        testUser7.save(flush: true)
        def testUser8 = new User(id:9,username: 'zengli',
                password: '1',
                name: '曾黎',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '8',
                role:adminRole8,
                enabled: true)
        testUser8.save(flush: true)
        def testUser9 = new User(id:10 ,username: 'zhangqi',
                password: '1',
                name: '张琪',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '9',
                role:adminRole9,
                enabled: true)
        testUser9.save(flush: true)
        def testUser10 = new User(id:11 ,username: 'wuliu',
                password: '1',
                name: '吴柳',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '10',
                role:adminRole10,
                enabled: true)
        testUser10.save(flush: true)
        def testUser11 = new User(id:12 ,username: 'xilang',
                password: '1',
                name: '稀朗',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '11',
                role:adminRole11,
                enabled: true)
        testUser11.save(flush: true)
        def testUser12 = new User(id:13 ,username: 'yangwei',
                password: '1',
                name: '杨维',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '12',
                role:adminRole12,
                enabled: true)
        testUser12.save(flush: true)

        def testUser13 = new User(id:14,username: 'mengyuan',
                password: '1',
                name: '梦缘',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '13',
                role:adminRole13,
                enabled: true)
        testUser13.save(flush: true)
        def testUser14 = new User(id:15,username: 'yangchao',
                password: '1',
                name: '杨超',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '14',
                role:adminRole14,
                enabled: true)
        testUser14.save(flush: true)
        def testUser15 = new User(id:16 ,username: 'shuwen',
                password: '1',
                name: '舒文',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '15',
                role:adminRole15,
                enabled: true)
        testUser15.save(flush: true)
        def testUser16 = new User(id:17 ,username: 'maoli',
                password: '1',
                name: '毛丽',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '16',
                role:adminRole16,
                enabled: true)
        testUser16.save(flush: true)
        def testUser17 = new User(id:18 ,username: 'ximing',
                password: '1',
                name: '习明',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '17',
                role:adminRole17,
                enabled: true)
        testUser17.save(flush: true)
        def testUser18 = new User(id:19 ,username: 'shenting',
                password: '1',
                name: '沈婷',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '18',
                role:adminRole18,
                enabled: true)
        testUser18.save(flush: true)

        def testUser19 = new User(id:10,username: 'gaoyang',
                password: '1',
                name: '高阳',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '19',
                role:adminRole19,
                enabled: true)
        testUser19.save(flush: true)
        def testUser20 = new User(id:21,username: 'tanghuiyun',
                password: '1',
                name: '唐慧云',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '20',
                role:adminRole20,
                enabled: true)
        testUser20.save(flush: true)
        def testUser21 = new User(id:22 ,username: 'yanfan',
                password: '1',
                name: '严凡',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '21',
                role:adminRole21,
                enabled: true)
        testUser21.save(flush: true)
        def testUser22 = new User(id:23 ,username: 'zhaolinna',
                password: '1',
                name: '赵琳娜',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '22',
                role:adminRole22,
                enabled: true)
        testUser22.save(flush: true)
        def testUser23 = new User(id:24 ,username: 'wuxi',
                password: '1',
                name: '吴熙',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '23',
                role:adminRole16,
                enabled: true)
        testUser23.save(flush: true)
        def testUser24 = new User(id:25 ,username: 'huanglin',
                password: '1',
                name: '黄琳',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '1100',
                departmentNo: '财务部',
                empNo: '43910343',
                empPosition: '总监',
                userId: '24',
                role:adminRole5,
                enabled: true)
        testUser24.save(flush: true)
//        UserRole.create testUser, adminRole, true

        //初始化菜单数据
        def mc1 = new MenuCategory(categoryName:'系统配置',sortIndex:1).save(flush: true)
//        new Menu(menuName:'菜单管理',sortIndex:1,actionUrl:'../menu/index',menuCategory:mc1).save(flush: true)
        new Menu(menuName:'角色管理',sortIndex:2,actionUrl:'../roleManagement/index',menuCategory:mc1).save(flush: true)
//        new Menu(menuName:'用户管理',sortIndex:3,actionUrl:'../user/index',menuCategory:mc1).save(flush: true)
        new Menu(menuName:'系统权限管理',sortIndex:4,actionUrl:'../rightsManagement/index',menuCategory:mc1).save(flush: true)
        new Menu(menuName:'用户角色管理',sortIndex:5,actionUrl:'../userRole/index',menuCategory:mc1).save(flush: true)

        def mc2 =new MenuCategory(categoryName:'主数据',sortIndex:2).save(flush: true)
        new Menu(menuName:'组织架构',sortIndex:1,actionUrl:'../orgStructure/index',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'业务信息',sortIndex:2,actionUrl:'../processes/initProcess',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'报销标准',sortIndex:3,actionUrl:'../budgetReportReceipts/bxStandard',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'预算费用',sortIndex:4,actionUrl:'../budgetReportReceipts/budgetDetail',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'信息提醒',sortIndex:5,actionUrl:'../processes/initProcess',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'日志管理',sortIndex:6,actionUrl:'../processes/initProcess',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'待办列表',sortIndex:7,actionUrl:'../processes/initProcess',menuCategory:mc2).save(flush: true)
        new Menu(menuName:'员工信息',sortIndex:8,actionUrl:'../empInformation/empInformation',menuCategory:mc2).save(flush: true)

        def mc3 =new MenuCategory(categoryName:'流程管理',sortIndex:3).save(flush: true)
        new Menu(menuName:'流程配置',sortIndex:1,actionUrl:'../flowConfig/flowConfig',menuCategory:mc3).save(flush: true)
//        new Menu(menuName:'流程查看',sortIndex:1,actionUrl:'../flowLookUp/flowLookUp',menuCategory:mc3).save(flush: true)
        new Menu(menuName:'流程发布',sortIndex:1,actionUrl:'../flowSheetInfuse/flowSheetInfuse',menuCategory:mc3).save(flush: true)
//        new Menu(menuName:'费用报销核算流程',sortIndex:2,actionUrl:'#',menuCategory:mc3).save(flush: true)
//        new Menu(menuName:'员工借款核算流程',sortIndex:3,actionUrl:'#',menuCategory:mc3).save(flush: true)

        def mc4 =new MenuCategory(categoryName:'报销单',sortIndex:4).save(flush: true)
        new Menu(menuName:'借款申请单',sortIndex:1,actionUrl:'../loanAppReceipts/loanAppReceiptsQuery',menuCategory:mc4).save(flush: true)
        new Menu(menuName:'预算申报单',sortIndex:2,actionUrl:'../budgetReportReceipts/budgetReportReceipts',menuCategory:mc4).save(flush: true)
        new Menu(menuName:'费用报销单',sortIndex:3,actionUrl:'../bxReceipt/index',menuCategory:mc4).save(flush: true)
        new Menu(menuName:'预算调整单',sortIndex:4,actionUrl:'../budgetAdjustReceipts/budgetAdjustReceiptsQuery',menuCategory:mc4).save(flush: true)

        def mc5 =new MenuCategory(categoryName:'集成接口',sortIndex:5).save(flush: true)
        new Menu(menuName:'SAP系统',sortIndex:1,actionUrl:'#',menuCategory:mc5).save(flush: true)
        new Menu(menuName:'人事系统',sortIndex:2,actionUrl:'#',menuCategory:mc5).save(flush: true)
        new Menu(menuName:'成本中心导入',sortIndex:3,actionUrl:'../costCenterImport/index2',menuCategory:mc5).save(flush: true)
        new Menu(menuName:'会计科目导入',sortIndex:4,actionUrl:'../accSubjectImport/index2',menuCategory:mc5).save(flush: true)

    }
    def destroy = {
    }
}
