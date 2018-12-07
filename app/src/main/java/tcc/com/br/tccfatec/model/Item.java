package tcc.com.br.tccfatec.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item implements Parcelable{

    private String name;
    private String type;
    private String photo;
    private String description;
    private String address;
    private String city;
    private String author;
    private String state;


    public Item() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Item(String name, String type, String photo, String description, String address) {
        this.name = name;
        this.type = type;
        this.photo = photo;

        this.description = description;
        this.address = address;
    }

    public Item(String name, String type, String photo, String description, String address, String city, String author, String state) {
        this.name = name;
        this.type = type;
        this.photo = photo;
        this.description = description;
        this.address = address;
        this.city = city;
        this.author = author;
        this.state = state;
    }

    protected Item(Parcel in) {
        name = in.readString();
        type = in.readString();
        photo = in.readString();
        description = in.readString();
        address = in.readString();
        city = in.readString();
        author = in.readString();
        state = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(photo);
        parcel.writeString(description);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(author);
        parcel.writeString(state);
    }
}
