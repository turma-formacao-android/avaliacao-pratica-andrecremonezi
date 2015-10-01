package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class SocialNetwork implements Parcelable{
    private Contact contact;
    private Long    id;
    private String  name;
    private String  userName;

    protected SocialNetwork(Parcel in) {
        contact = in.readParcelable(Contact.class.getClassLoader());
        name = in.readString();
        userName = in.readString();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialNetwork that = (SocialNetwork) o;

        if (!contact.equals(that.contact)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return userName.equals(that.userName);

    }

    @Override
    public int hashCode() {
        int result = contact.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + userName.hashCode();
        return result;
    }

    public static final Creator<SocialNetwork> CREATOR = new Creator<SocialNetwork>() {
        @Override
        public SocialNetwork createFromParcel(Parcel in) {
            return new SocialNetwork(in);
        }

        @Override
        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(contact, flags);
        dest.writeString(name);
        dest.writeString(userName);
    }
}
