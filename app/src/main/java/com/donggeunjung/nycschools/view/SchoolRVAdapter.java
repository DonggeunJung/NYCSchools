package com.donggeunjung.nycschools.view;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.donggeunjung.nycschools.R;
import com.donggeunjung.nycschools.databinding.SchoolItemBinding;
import com.donggeunjung.nycschools.model.SchoolSimple;
import com.donggeunjung.nycschools.viewmodel.DataViewModel;
import java.util.ArrayList;

/*
 * SchoolRVAdapter.java : RecyclerView adapter class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class SchoolRVAdapter extends RecyclerView.Adapter<SchoolRVAdapter.SchoolVH> {
    private DataViewModel mViewModel;
    Fragment mParent;
    RecyclerView.Adapter<SchoolVH> mAdapter;

    // Constructor
    public SchoolRVAdapter(DataViewModel viewModel, Fragment parent) {
        this.mViewModel = viewModel;
        this.mParent = parent;
        mAdapter = this;

        // make School simple data list Observer object
        final Observer<ArrayList<SchoolSimple>> schoolsObserver = new Observer<ArrayList<SchoolSimple>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<SchoolSimple> schools) {
                // When School simple data list is changed update RecyclerView
                mAdapter.notifyDataSetChanged();
            }
        };
        // Send Observer object to ViewModel
        viewModel.getListSchools().observe(this.mParent, schoolsObserver);
    }

    // Return list items count
    @Override
    public int getItemCount() {
        // Get the school simple data items count
        ArrayList<SchoolSimple> schools = mViewModel.getListSchools().getValue();
        // When the data object is not exist return 0
        if( schools == null )
            return 0;
        return schools.size();
    }

    // Make ViewHolder & View binding object
    @Override
    public SchoolVH onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Get the view binding object of custom list item layout
        LayoutInflater inflater = LayoutInflater.from(mParent.getContext());
        SchoolItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.school_item, viewGroup, false);
        // Set the Lifecycle Owner of View binding to fragment
        binding.setLifecycleOwner(mParent);
        // Set ViewModel object to binding object as a variable
        binding.setViewModel(mViewModel);
        binding.setListFragment((ListFragment)mParent);

        // Make ViewHolder object
        return new SchoolVH(binding);
    }

    // When ViewHolder is binded set data to binding object
    @Override
    public void onBindViewHolder(SchoolVH viewHolder, int position) {
        // Set item index number to binding object
        viewHolder.bind(position);
    }

    // Reuse views
    public static class SchoolVH extends RecyclerView.ViewHolder {
        public SchoolItemBinding binding;

        //public ViewHolder(View itemView) {
        public SchoolVH(SchoolItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int index) {
            binding.setIndex(index);
            binding.executePendingBindings();
        }
    }
}
