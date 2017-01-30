package com.example.rajdeep.explicitintents;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajdeep on 1/23/2017.
 */

public class Person implements Parcelable {
    String name;
    String add;
    double age;

    protected Person(Parcel in) {
        name = in.readString();
        add = in.readString();
        age = in.readDouble();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Person(String name, String add, double age) {
        this.name = name;
        this.add = add;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(add);
        dest.writeDouble(age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", add='" + add + '\'' +
                ", age=" + age +
                '}';
    }
}
