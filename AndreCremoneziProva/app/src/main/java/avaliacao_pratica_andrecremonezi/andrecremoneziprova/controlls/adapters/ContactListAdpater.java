package avaliacao_pratica_andrecremonezi.andrecremoneziprova.controlls.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.R;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;

public class ContactListAdpater extends BaseAdapter {

    private List<Contact> contactList;
    private Activity context;

    public ContactListAdpater(Activity context, List<Contact> taskList) {
        this.contactList = taskList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        View contactListItemView = context.getLayoutInflater().inflate(R.layout.list_item_contact, parent, false);

        TextView textViewName = (TextView) contactListItemView.findViewById(R.id.textViewName);
        textViewName.setText(contact.getName());

        TextView textViewCity = (TextView) contactListItemView.findViewById(R.id.textViewCity);
        textViewCity.setText(contact.getAddress().getCity());

        return contactListItemView;
    }

    public void setDataValues(List<Contact> values) {
        contactList.clear();
        contactList.addAll(values);
    }
}
