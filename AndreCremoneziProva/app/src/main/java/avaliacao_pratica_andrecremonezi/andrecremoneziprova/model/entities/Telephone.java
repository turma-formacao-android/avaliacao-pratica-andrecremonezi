package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Telephone implements Parcelable{
    private Contact contact;
    private Long    id;
    private String  number;

    public Telephone() {
        super();
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telephone telephone = (Telephone) o;

        if (!contact.equals(telephone.contact)) return false;
        if (!id.equals(telephone.id)) return false;
        return number.equals(telephone.number);

    }

    @Override
    public int hashCode() {
        int result = contact.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    protected Telephone(Parcel in) {
        contact = in.readParcelable(Contact.class.getClassLoader());
        number = in.readString();
    }

    public static final Creator<Telephone> CREATOR = new Creator<Telephone>() {
        @Override
        public Telephone createFromParcel(Parcel in) {
            return new Telephone(in);
        }

        @Override
        public Telephone[] newArray(int size) {
            return new Telephone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(contact, flags);
        dest.writeString(number);
    }
}
