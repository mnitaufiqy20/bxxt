dataSource {
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            pooled = true
            dbCreate = "update"
//    driverClassName = "com.mysql.jdbc.Driver"
//    username = "root"
//    password = "ibm"
//    url = "jdbc:mysql://192.168.1.191:3306/ers"
            driverClassName = "oracle.jdbc.driver.OracleDriver"
//            username = "er"
//            password = "er"
//            url = "jdbc:oracle:thin:@10.76.66.56:1521:DBDEV"
//            username = "peihao"
//            password = "peihao"
//            url = "jdbc:oracle:thin:@192.168.1.190:1521:ORCL"
            username = "jing"
            password = "jing"
            url = "jdbc:oracle:thin:@localhost:1521:ORCL"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            jndiName = "jndi_er"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            jndiName = "jndi_er"
        }
    }
}
