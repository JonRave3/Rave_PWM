package rave.pwm.pwmanager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rave.pwm.pwmanager.AccountListFragment.OnListFragmentInteractionListener;
import rave.pwm.pwmanager.Accounts.AccountRecord;

import java.util.List;


public class AccountRecyclerViewAdapter extends RecyclerView.Adapter<AccountRecyclerViewAdapter.ViewHolder> {

    private final List<AccountRecord> mValues;
    private final OnListFragmentInteractionListener mListener;

    public AccountRecyclerViewAdapter(List<AccountRecord> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //set the values of the view
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mUserNameView.setText(mValues.get(position).getUserName());
        holder.mPasswordView.setText(mValues.get(position).getPassword());
        holder.mOptionsView.setText(mValues.get(position).getOptions());

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mView;
        public final TextView mNameView;
        public final TextView mUserNameView;
        public final TextView mPasswordView;
        public final TextView mOptionsView;

        public AccountRecord mItem;

        public ViewHolder(View view) {
            super(view);
            mView = (CardView) view;
            mNameView = mView.findViewById(R.id.account_item_name);
            mUserNameView = mView.findViewById(R.id.account_item_login_id);
            mPasswordView = mView.findViewById(R.id.account_item_password);
            mOptionsView = mView.findViewById(R.id.account_item_options);
        }
    }
}
