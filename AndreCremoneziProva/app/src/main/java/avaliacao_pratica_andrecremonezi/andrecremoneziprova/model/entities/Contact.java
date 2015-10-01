package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Contact implements Parcelable {
    private Long id;
    private String name;
    private List<Email> emails;
    private List<SocialNetwork> socialNetworks;
    private List<Telephone> telephones;

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("tipoDeLogradouro")
    private String type;

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

    public Contact(){
        super();
    }

    protected Contact(Parcel in) {
        name = in.readString();
        zipCode = in.readString();
        type = in.readString();
        street = in.readString();
        neighborhood = in.readString();
        city = in.readString();
        state = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(zipCode);
        dest.writeString(type);
        dest.writeString(street);
        dest.writeString(neighborhood);
        dest.writeString(city);
        dest.writeString(state);



        dest.writeList(emails);
        dest.writeList(socialNetworks);
        dest.writeList(telephones);
    }
}
