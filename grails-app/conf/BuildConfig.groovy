grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
//grails.server.port.http=8090
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.20'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.1"

        compile ':cache:1.0.0'
    }

}

grails.war.resources = { stagingDir, args ->
    delete file: "${stagingDir}/WEB-INF/lib/ojdbc6.jar"
    delete file: "${stagingDir}/WEB-INF/lib/asm-3.1.jar"
    delete file: "${stagingDir}/WEB-INF/lib/cglib-2.2.jar"
    //delete file: "${stagingDir}/WEB-INF/lib/slf4j-api-1.6.2.jar"
    copy(file: "lib-other/cglib-nodep-2.2.2.jar",
            tofile: "${stagingDir}/WEB-INF/lib/cglib-nodep-2.2.2.jar")

    delete file: "${stagingDir}/WEB-INF/classes/jbpm.hibernate.cfg.xml"
    copy(file: "env/${grails.util.Environment.current.name}/jbpm.hibernate.cfg.xml",
            tofile: "${stagingDir}/WEB-INF/classes/jbpm.hibernate.cfg.xml")

}

