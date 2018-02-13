package cs2340.edu.gatech.lamp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JermiahRussell on 2/13/2018.
 */

public class User implements Parcelable {
    private static int num_users;
    private String user_name;
    private int user_id;
    private int user_hash;  //password hash

    public User() {
        this("First Last", num_users,num_users);
    }
    public User(String name, int hash,int id) {
        user_name = name;
        user_hash = hash;
        user_hash = id;
        num_users++;
    }

    protected User(Parcel in) {
        this(in.readString(),in.readString().hashCode(), num_users);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(user_name);
        parcel.writeInt(user_id);
    }
}
