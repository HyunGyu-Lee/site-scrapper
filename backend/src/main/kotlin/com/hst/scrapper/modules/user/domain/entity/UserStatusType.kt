package com.hst.scrapper.modules.user.domain.entity

import javax.persistence.AttributeConverter

/**
 * @author dlgusrb0808@gmail.com
 */
enum class UserStatusType(val statusCode: Int, val comment: String) {
    EMAIL_NOT_VERIFIED(1, ""),
    INACTIVE(2, ""),
    ACTIVE(3, "")
    ;

    companion object {
        val HOLDER = values().associateBy { it.statusCode }
        val default = EMAIL_NOT_VERIFIED
    }

    class Converter: AttributeConverter<UserStatusType, Int> {
        override fun convertToDatabaseColumn(attribute: UserStatusType?): Int {
             return attribute?.statusCode ?: default.statusCode
        }

        override fun convertToEntityAttribute(dbData: Int?): UserStatusType {
            return HOLDER.getOrDefault(dbData, default)
        }
    }
}