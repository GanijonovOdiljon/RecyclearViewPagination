package com.example.recyclaearviewpagination.model

class Data {
    var avatar: String? = null
    var email: String? = null
    var first_name: String? = null
    var id: Int? = null
    var last_name: String? = null

    constructor()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (avatar != other.avatar) return false
        if (email != other.email) return false
        if (first_name != other.first_name) return false
        if (id != other.id) return false
        if (last_name != other.last_name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = avatar?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (first_name?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (last_name?.hashCode() ?: 0)
        return result
    }

}

