package usabilla.thedieter.main

import android.os.Parcel
import android.os.Parcelable

data class Feedback(val comment: String, val rating: Int, val date: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(comment)
        writeInt(rating)
        writeString(date)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Feedback> = object : Parcelable.Creator<Feedback> {
            override fun createFromParcel(source: Parcel): Feedback = Feedback(source)
            override fun newArray(size: Int): Array<Feedback?> = arrayOfNulls(size)
        }
    }
}