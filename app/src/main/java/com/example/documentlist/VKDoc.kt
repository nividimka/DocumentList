package com.example.documentlist

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject
import java.lang.StringBuilder

data class VKDoc(
    val id: Long = 0,
    val ext: String = "",
    val title: String = "",
    val type: Int = 0,
    val tagArray: Array<String> = emptyArray(),
    val date: Long = 0,
    val size: Long = 0
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readArray(Array<Int>::class.java.classLoader) as Array<String>,
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(ext)
        parcel.writeString(title)
        parcel.writeInt(type)
        parcel.writeArray(tagArray)
        parcel.writeLong(date)
        parcel.writeLong(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKDoc> {
        override fun createFromParcel(parcel: Parcel): VKDoc {
            return VKDoc(parcel)
        }

        override fun newArray(size: Int): Array<VKDoc?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject) :VKDoc{
            val tagsJson = json.optJSONArray("tags")
            var tags = emptyArray<String>()
            tagsJson?.let {arr->
                 tags = Array(arr.length()) {
                     arr.getString(it)
                }
            }
            return VKDoc(
                id = json.optLong("id", 0),
                ext = json.optString("ext"),
                title = json.optString("title"),
                type = json.optInt("type",0),
                tagArray = tags,
                date = json.optLong("date",0),
                size = json.optLong("size",0)
            )
        }
    }

    fun getTagString(): String{
        return tagArray.joinToString(separator = ", ")
    }

    fun getInformationString(): String{
        val info = ArrayList<String>()
        info.add(ext.toUpperCase())
        info.add(size.toString())
        info.add(date.toString())
        return info.joinToString(separator = " · ")/*"7 янв · 199 КБ"*/
    }
}