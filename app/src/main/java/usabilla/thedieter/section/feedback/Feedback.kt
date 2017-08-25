package usabilla.thedieter.section.feedback

import android.os.Parcel
import android.os.Parcelable

data class Feedback(val message: String, val mood: Int) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(message)
        writeInt(mood)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Feedback> = object : Parcelable.Creator<Feedback> {
            override fun createFromParcel(source: Parcel): Feedback = Feedback(source)
            override fun newArray(size: Int): Array<Feedback?> = arrayOfNulls(size)
        }
    }
}