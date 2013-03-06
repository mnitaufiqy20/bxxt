import com.chd.bx.security.Menu
import com.chd.bx.security.MenuCategory
import com.chd.bx.security.Role
import com.chd.bx.security.User

class BootStrap {

    def init = { servletContext ->
        //初始化用户数据
        def adminRole = new Role(authority: '系统管理员',description:'报销系统超级管理员').save(flush: true)
        def testUser = new User(username: 'zhangsan',
                password: '1',
                name: '张三',
                telephone: '13011111111',
                idNumber: '123456789012345678',
                companyNo: '集团总部',
                departmentNo: '信息部',
                empNo: '001',
                empPosition: '专责',
                role:adminRole,
                enabled: true)
        testUser.save(flush: true)
//        UserRole.create testUser, adminRole, true

        //初始化菜单数据
        def mc1 = new MenuCategory(categoryName:'系统配置',sortIndex:1).save(flush: true)
        new Menu(menuName:'菜单管理',sortIndex:1,actionUrl:'../menu/index',menuCategory:mc1).save(flush: true)
        new Menu(menuName:'角色管理',sortIndex:2,actionUrl:'../role/index',menuCategory:mc1).save(flush: true)
        new Menu(menuName:'用户管理',sortIndex:3,actionUrl:'../user/index',menuCategory:mc1).save(flush: true)

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
        new Menu(menuName:'流程配置',sortIndex:1,actionUrl:'#',menuCategory:mc3).save(flush: true)
        new Menu(menuName:'费用报销核算流程',sortIndex:2,actionUrl:'#',menuCategory:mc3).save(flush: true)
        new Menu(menuName:'员工借款核算流程',sortIndex:3,actionUrl:'#',menuCategory:mc3).save(flush: true)

        def mc4 =new MenuCategory(categoryName:'报销单',sortIndex:4).save(flush: true)
        new Menu(menuName:'借款申请单',sortIndex:1,actionUrl:'../loanAppReceipts/loanAppReceiptsQuery',menuCategory:mc4).save(flush: true)
        new Menu(menuName:'预算申报单',sortIndex:2,actionUrl:'../budgetReportReceipts/budgetReportReceipts',menuCategory:mc4).save(flush: true)
        new Menu(menuName:'费用报销单',sortIndex:3,actionUrl:'../bxReceipt/index',menuCategory:mc4).save(flush: true)
        new Menu(menuName:'预算调整单',sortIndex:4,actionUrl:'../budgetAdjustReceipts/budgetAdjustReceiptsQuery',menuCategory:mc4).save(flush: true)

        def mc5 =new MenuCategory(categoryName:'集成接口',sortIndex:5).save(flush: true)
        new Menu(menuName:'SAP系统',sortIndex:1,actionUrl:'#',menuCategory:mc5).save(flush: true)
        new Menu(menuName:'人事系统',sortIndex:2,actionUrl:'#',menuCategory:mc5).save(flush: true)

    }
    def destroy = {
    }
}
