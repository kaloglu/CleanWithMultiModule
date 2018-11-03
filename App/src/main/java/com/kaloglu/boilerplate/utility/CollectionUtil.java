package com.kaloglu.boilerplate.utility;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility class for collection operations.
 */
public final class CollectionUtil {

    /**
     * Private constructor for util class.
     */
    private CollectionUtil() {
    }

    /**
     * Returns the number of elements in this collection. If this collection
     * is empty or null, returns 0.
     *
     * @param collection to be checked
     * @return the number of elements in this collection
     */
    public static int size(Collection collection) {
        return isEmpty(collection) ? 0 : collection.size();
    }

    /**
     * Returns the number of keys in this mağ. If this map
     * is empty or null, returns 0.
     *
     * @param map to be checked
     * @return the number of key value pairs in this map
     */
    public static int size(Map map) {
        return isEmpty(map) ? 0 : map.size();
    }

    /**
     * Checks if given collection is empty.
     *
     * @param collection to be checked
     * @return true if the collection is empty
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * Checks if given map is empty.
     *
     * @param map to be checked
     * @return true if the map is empty
     */
    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    /**
     * Returns an element at the given [index] or `null` if the [index] is out of bounds of this list.
     *
     * @param list  to be searched
     * @param index the index of the element to return
     * @return the element at the given index in this list
     */
    public static <T> T getOrNull(List<T> list, int index) {
        return isIndexWithinBounds(list, index) ? list.get(index) : null;
    }

    /**
     * Checks if given index is in bounds or not.
     *
     * @param collection to be checked
     * @return true if the index is in bounds or not
     */
    public static boolean isIndexWithinBounds(Collection collection, int index) {
        return 0 <= index && index < size(collection);
    }
}
