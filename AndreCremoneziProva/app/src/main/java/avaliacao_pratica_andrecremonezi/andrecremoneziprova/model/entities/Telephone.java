package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Telephone implements Parcelable{
    private Long    id;
    private String  number;
    private Contact contact;


    public Telephone() {
        super();
    }

    public Long getIdcontact() {
        return contact.getId();
    }

    public void setIdcontact(Long idcontact) {
        this.contact.setId(idcontact);
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telephone telephone = (Telephone) o;

        if (!id.equals(telephone.id)) return false;
        if (!number.equals(telephone.number)) return false;
        return contact.equals(telephone.contact);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }

    protected Telephone(Parcel in) {
        this.id = (Long) in.readValue(Integer.class.getClassLoader());
        this.number = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
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
        dest.writeValue(this.id);
        dest.writeString(this.number);
        dest.writeParcelable(this.contact, flags);
    }

}
