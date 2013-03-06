package com.chd.bx.security

class Role {

	String authority
    String description
    static hasMany = [user:User]

	static mapping = {
        table 'S_ROLE'
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
        description blank: true
	}
}
