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
    private Password pass_hash;

    public User() {
        this("homeless", "qwerty", num_users);
    }
    public User(String name, String password,int id) {
        user_name = name;
        pass_hash = new Password(password);
        user_id = id;
        num_users++;
    }

    protected User(Parcel in) {
        this(in.readString(), in.readString(), num_users);
    }

    public String getUser_name() {
        return user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public Password getPass_hash() {
        return pass_hash;
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
