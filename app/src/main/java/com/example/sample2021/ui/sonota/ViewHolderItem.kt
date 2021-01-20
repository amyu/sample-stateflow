package com.example.sample2021.ui.sonota

import com.example.sample2021.domain.Identifier
import java.io.Serializable

abstract class ViewHolderItem {
    abstract val id: Identifier<out Serializable>

    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int

    // 対応するDomain IDがない場合に使用する
    class Id(value: String) : Identifier<String>(value)
}

