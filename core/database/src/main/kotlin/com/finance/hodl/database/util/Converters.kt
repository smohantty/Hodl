package com.finance.hodl.database.util

import androidx.room.TypeConverter
import java.math.BigDecimal


internal class BigDecimalConverter {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.toBigDecimal()
    }
}