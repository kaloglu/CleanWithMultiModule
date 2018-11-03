package com.kaloglu.boilerplate.views.recyclerview.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.kaloglu.boilerplate.utility.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Base RecyclerViewAdapter that uses BaseViewHolder for click and long click support.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    @NonNull
    protected List<Object> items;

    @Nullable
    private OnItemClickListener itemClickListener;

    @Nullable
    private OnItemLongClickListener itemLongClickListener;

    /**
     * Constructor.
     */
    public BaseRecyclerViewAdapter() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param items The items to showDialog in this adapter.
     */
    public BaseRecyclerViewAdapter(@Nullable List items) {
        this.items = items != null ? items : new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final BaseViewHolder viewHolder = createNewViewHolder(parent, viewType);

        viewHolder.setItemClickListener(itemClickListener);
        viewHolder.setItemLongClickListener(itemLongClickListener);

        return viewHolder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        viewHolder.bindItem(getItem(position));
    }

    @Override
    public int getItemCount() {
        return CollectionUtil.size(items);
    }

    /**
     * Returns the items in the data set held by this adapter.
     *
     * @return The items in this adapter.
     */
    public List getItems() {
        return items;
    }

    /**
     * Sets new items to display in this adapter.
     *
     * @param items The items to display in this adapter.
     */
    public void setItems(List items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Returns the items that are filtered by class in the data set held by this adapter.
     *
     * @return The items by type.
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getItems(Class<T> itemClass) {
        List<T> filteredItems = new ArrayList<>();
        for (Object item : items) {

            if (itemClass.equals(item.getClass())) {
                filteredItems.add((T) item);
            }
        }
        return filteredItems;
    }

    /**
     * Returns the item at the specified position in this adapter.
     *
     * @param position position of the item to return
     * @return the item at the specified position in this adapter
     */
    @Nullable
    public Object getItem(int position) {

        if (CollectionUtil.isIndexWithinBounds(items, position)) {
            return items.get(position);
        }
        return null;
    }

    /**
     * Returns true if the items in this adapter is empty.
     *
     * @return true if the items in this adapter is empty.
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(items);
    }

    /**
     * Add new items to display in this adapter.
     *
     * @param items The items to display in this adapter.
     */
    public void addItems(List items) {
        final int previousSize = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(previousSize, items.size());
    }

    /**
     * Add a new item to display in this adapter.
     *
     * @param item The item to add.
     */
    public void addItem(Object item, int position) {
        this.items.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * Remove the item at position from this adapter.
     *
     * @param position The position at which the item to remove.
     */
    public void removeItem(int position) {
        this.items.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Clear all items in this adapter.
     */
    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    /**
     * Register a callback to be invoked when an item is clicked.
     *
     * @param itemClickListener callback
     */
    public void setItemClickListener(@Nullable OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * Register a callback to be invoked when an item is clicked and held.
     *
     * @param itemLongClickListener callback
     */
    public void setItemLongClickListener(@Nullable OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    /**
     * Called when a new ViewHolder of the given type is needed to represent the RecyclerView item.
     *
     * @param parent   parent layout
     * @param viewType the integer value identifying the type of the view
     * @return a new viewHolder instance
     */
    @NonNull
    protected abstract BaseViewHolder createNewViewHolder(ViewGroup parent, int viewType);
}
