package bxxt

class MyJob {
    static triggers = {
        simple name: 'mySimpleTrigger', startDelay: 30000, repeatInterval: 3600000
    }

    def group = "MyGroup"

    def execute(){
        print "Job run!"
    }
}
