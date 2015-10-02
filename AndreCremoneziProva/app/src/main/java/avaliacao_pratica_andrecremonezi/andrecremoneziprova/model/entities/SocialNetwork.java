package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class SocialNetwork implements Parcelable{
    private Long    id;
    private String  link;
    private Contact contact;

    public SocialNetwork() {
        super();
    }

    protected SocialNetwork(Parcel in) {
        this.id = (Long) in.readValue(Integer.class.getClassLoader());
        link = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialNetwork that = (SocialNetwork) o;

        if (!id.equals(that.id)) return false;
        if (!link.equals(that.link)) return false;
        return contact.equals(that.contact);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + contact.hashCode();
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
        dest.writeValue(this.id);
        dest.writeString(link);
        dest.writeParcelable(this.contact, flags);
    }
}
