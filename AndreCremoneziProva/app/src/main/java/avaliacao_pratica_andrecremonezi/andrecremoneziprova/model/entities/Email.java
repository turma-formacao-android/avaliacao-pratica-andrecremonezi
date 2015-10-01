package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Email implements Parcelable{
    private Contact contact;
    private Long    id;
    private String  emailAddress;


    public Email() {
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        if (!contact.equals(email.contact)) return false;
        if (!id.equals(email.id)) return false;
        return emailAddress.equals(email.emailAddress);

    }

    @Override
    public int hashCode() {
        int result = contact.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + emailAddress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "contact=" + contact +
                ", id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    protected Email(Parcel in) {
        contact = in.readParcelable(Contact.class.getClassLoader());
        emailAddress = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(contact, flags);
        dest.writeString(emailAddress);
    }
}
