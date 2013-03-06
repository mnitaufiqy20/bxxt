// Place your Spring DSL code here
beans = {
    //JBPM引擎初始化
    springHelper(org.jbpm.pvm.internal.processengine.SpringHelper) {
        jbpmCfg = "jbpm.cfg.xml"
    }

    processEngine(springHelper:"createProcessEngine")
}
