package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable{
    private Long    id;
    private String  emailAddress;
    private Contact contact;

    public Email(){

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getIdcontact() {
        return contact.getId();
    }

    public void setIdcontact(Long idcontact) {
        this.contact.setId(idcontact);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        if (!id.equals(email.id)) return false;
        if (!emailAddress.equals(email.emailAddress)) return false;
        return contact.equals(email.contact);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Integer.class.getClassLoader());
        emailAddress = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());

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
        dest.writeValue(this.id);
        dest.writeParcelable(contact, flags);
        dest.writeString(emailAddress);
    }

}
