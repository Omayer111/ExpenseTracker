package com.example.expensetracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.Model.Category;
import com.example.expensetracker.R;
import com.example.expensetracker.databinding.SampleCategoryLayoutBinding;

import java.util.ArrayList;

/**
 * CategoryAdapter is a RecyclerView.Adapter that displays a list of Category objects.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category> categories;

    /**
     * Interface definition for a callback to be invoked when a category is clicked.
     */
    public interface CategoryClickListener {
        /**
         * Called when a category has been clicked.
         *
         * @param category The clicked category.
         */
        void onCategoryClicked(Category category);
    }

    CategoryClickListener categoryClickListener;

    /**
     * Constructs a new CategoryAdapter.
     *
     * @param context The context in which the adapter is running.
     * @param categories The list of Category objects to be displayed.
     * @param categoryClickListener The listener for category click events.
     */
    public CategoryAdapter(Context context, ArrayList<Category> categories, CategoryClickListener categoryClickListener) {
        this.context = context;
        this.categories = categories;
        this.categoryClickListener = categoryClickListener;
    }

    /**
     * Called when RecyclerView needs a new {@link CategoryViewHolder} of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new CategoryViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_category_layout, parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.binding.categoryText.setText(category.getCategoryName());
        holder.binding.categoryIcon.setImageResource(category.getCategoryImage());
        holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(category.getCategoryColor()));
        holder.itemView.setOnClickListener(c -> {
            categoryClickListener.onCategoryClicked(category);
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     * ViewHolder class for Category items.
     */
    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        SampleCategoryLayoutBinding binding;

        /**
         * Constructs a new CategoryViewHolder.
         *
         * @param itemView The view of the item.
         */
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleCategoryLayoutBinding.bind(itemView);
        }
    }
}
