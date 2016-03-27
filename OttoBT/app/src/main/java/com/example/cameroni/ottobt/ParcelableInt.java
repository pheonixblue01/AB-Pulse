package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableInt implements Parcelable {
    public int mData;

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeInt(mData);
    }

    public static final Parcelable.Creator<ParcelableInt> CREATOR
            = new Parcelable.Creator<ParcelableInt>() {
        public ParcelableInt createFromParcel(Parcel in) {
            return new ParcelableInt(in);
        }

        public ParcelableInt[] newArray(int size) {
            return new ParcelableInt[size];
        }
    };

    private ParcelableInt(Parcel in) {
        mData = in.readInt();
    }
}
