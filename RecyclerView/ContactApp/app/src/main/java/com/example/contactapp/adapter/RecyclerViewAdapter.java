package com.example.contactapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.MainActivity;
import com.example.contactapp.R;
import com.example.contactapp.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter
{
    private Context _context;
    private List<Contact> _contactList;

    public RecyclerViewAdapter(Context _context, List<Contact> _contactList) {
        this._context = _context;
        this._contactList = _contactList;
    }

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * . Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary  calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contact_row, parent, false);


        return new ViewHolder(view);// return the view via ViewHolder.
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override  instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        // doing most of the work..
        // binding the views with the data.

        Contact contact = _contactList.get(position); // we have each contact object inside of our list.

        // bind
        if ( holder instanceof ViewHolder)
        {
            ((ViewHolder)holder).textViewName.setText(contact.get_name());
            ((ViewHolder)holder).textViewPhoneNumber.setText(contact.get_phoneNumer());

        }





    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount()
    {
        return _contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final String TAG = "ViewHolder";
        public TextView textViewName;
        public TextView textViewPhoneNumber;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);

            textViewName = itemView.findViewById(R.id.textview_name);
            textViewPhoneNumber = itemView.findViewById(R.id.textview_phone_number);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param view The view that was clicked.
         */
        @Override
        public void onClick(View view)
        {
            //Toast.makeText(view.getContext(), "Hi", Toast.LENGTH_LONG).show();

            int position = getAdapterPosition();

            Contact contact = _contactList.get(position);

            Log.d(TAG, contact.get_name());

        }
    }
}
