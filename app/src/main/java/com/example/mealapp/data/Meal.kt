package com.example.mealapp.data
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "meals_db", indices = [Index(value = ["Meal"], unique = true)])
data class Meal(
    @PrimaryKey
    val id: String,
    val Meal: String,
    val DrinkAlternate: String?,
    val Category: String,
    val Area: String,
    val Instuctions: String,
    val MealThumb: String,
    val Tags: String?,
    val Youtube: String,
    val Ingredient1: String?,
    val Ingredient2: String?,
    val Ingredient3: String?,
    val Ingredient4: String?,
    val Ingredient5: String?,
    val Ingredient6: String?,
    val Ingredient7: String?,
    val Ingredient8: String?,
    val Ingredient9: String?,
    val Ingredient10: String?,
    val Measure1: String?,
    val Measure2: String?,
    val Measure3: String?,
    val Measure4: String?,
    val Measure5: String?,
    val Measure6: String?,
    val Measure7: String?,
    val Measure8: String?,
    val Measure9: String?,
    val Measure10: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(Meal)
        parcel.writeString(DrinkAlternate)
        parcel.writeString(Category)
        parcel.writeString(Area)
        parcel.writeString(Instuctions)
        parcel.writeString(MealThumb)
        parcel.writeString(Tags)
        parcel.writeString(Youtube)
        parcel.writeString(Ingredient1)
        parcel.writeString(Ingredient2)
        parcel.writeString(Ingredient3)
        parcel.writeString(Ingredient4)
        parcel.writeString(Ingredient5)
        parcel.writeString(Ingredient6)
        parcel.writeString(Ingredient7)
        parcel.writeString(Ingredient8)
        parcel.writeString(Ingredient9)
        parcel.writeString(Ingredient10)
        parcel.writeString(Measure1)
        parcel.writeString(Measure2)
        parcel.writeString(Measure3)
        parcel.writeString(Measure4)
        parcel.writeString(Measure5)
        parcel.writeString(Measure6)
        parcel.writeString(Measure7)
        parcel.writeString(Measure8)
        parcel.writeString(Measure9)
        parcel.writeString(Measure10)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Meal> {
        override fun createFromParcel(parcel: Parcel): Meal {
            return Meal(parcel)
        }

        override fun newArray(size: Int): Array<Meal?> {
            return arrayOfNulls(size)
        }
    }
}


// reference : https://youtu.be/NS7yYdW3Lho
//             https://youtu.be/-dm4cYlKiAA