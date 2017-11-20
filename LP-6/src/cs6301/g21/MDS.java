package cs6301.g21;

import java.util.*;

public class MDS {

    HashMap<Long, TreeSet<Long>> descriptionItemid;
    TreeMap<Long, TreeSet<Long>> itemDecription;
    HashMap<Long, TreeSet<Long>> itemSupplier;
    TreeMap<Long, SupplierInfo> supplierInfo;
    TreeMap<Long, TreeMap<Integer, List<SupplierInfo>>> itemPriceSupplier;


    public MDS() {
        descriptionItemid = new HashMap<>(); //maps description words with item id
        itemDecription = new TreeMap<>(); //maps item id with description words
        itemSupplier = new HashMap<>(); //maps item id with supplier
        supplierInfo = new TreeMap<>(); //maps supplier ID with supplier info
        itemPriceSupplier = new TreeMap<>(); // maps item to a tree ordered on the price and holds info about supplier selling the item at that price
    }

    public static class Pair {
	long id;
	int price;
        public Pair(long id, int price) {
            this.id = id;
            this.price = price;
        }
    }


    /*
  descr class: tree, sum
   */
    public static class SupplierInfo{
        Long supplier;
        float reputation;
        HashMap<Long, Integer> list;

        public SupplierInfo(Long supp, float reputation, Pair [] pairs) {
            this.supplier = supp;
            this.reputation = reputation;
            list = new HashMap<>();
            for(Pair p: pairs){
                list.put(p.id, p.price);
            }
        }

        public SupplierInfo(Long supp){
            this.supplier = supp;
            list = new HashMap<>();
        }

        public float getReputation(){
            return reputation;
        }

        public void setReputation(float rep){
            this.reputation = rep;
        }

        public Integer getPair(long id){
            return list.get(id);
        }

        public void setPair(long id, int price){
            list.put(id, price);
        }

        public Long getSid(){
            return supplier;
        }
    }

    <K , V extends Comparable<K>> Comparable[] toSortedArray(HashMap<K, V> map){
        ValueSort sortByValue = new ValueSort(map);
        TreeMap<K, V> orderedByCount = new TreeMap<>(sortByValue);
        orderedByCount.putAll(map);
        return orderedByCount.keySet().toArray(new Long[map.size()]);
    }
    /* add a new item.  If an entry with the same id already exists,
       the new description is merged with the existing description of
       the item.  Returns true if the item is new, and false otherwise.
    */
    public boolean add(Long id, Long[ ] description) {
        boolean isnewItem = false;
        TreeSet<Long> t = itemDecription.get(id);
        if(t == null){
            t = new TreeSet<>();
            isnewItem = true;
        }
        for(Long d: description) {
            t.add(d);
            TreeSet<Long> dMap = descriptionItemid.get(d);
            if(dMap == null){
                dMap = new TreeSet<>();
            }
            dMap.add(id);
            descriptionItemid.put(d, dMap);
        }
        itemDecription.put(id, t);
        return isnewItem;

    }

    /* add a new supplier (Long) and their reputation (float in
       [0.0-5.0], single decimal place). If the supplier exists, their
       reputation is replaced by the new value.  Return true if the
       supplier is new, and false otherwise.
    */
    public boolean add(Long supplier, float reputation) {
	return true;
    }

    /* add products and their prices at which the supplier sells the
      product.  If there is an entry for the price of an id by the
      same supplier, then the price is replaced by the new price.
      Returns the number of new entries created.
    */
    public int add(Long supplier, Pair[ ] idPrice) {
        // add in supplier info and
        int created = 0;
        SupplierInfo supInfo = supplierInfo.get(supplier);
        if(supInfo == null){
            //supplier did not exist
            supInfo = new SupplierInfo(supplier);

        }
        //supp exists, go through its pairs, create pair and incr else replace price
        for(Pair p: idPrice) {
            Integer price = supInfo.getPair(p.id);

            if (price == null) {
                created++;
                price = p.price;
            }
            supInfo.setPair(p.id, p.price);
            supplierInfo.put(supplier, supInfo);

            //add it to item's tree for price , sup
            TreeMap priceSup = itemPriceSupplier.get(p.id);
            if (priceSup == null) {
                priceSup = new TreeMap<Integer, List<SupplierInfo>>();
            }
            //now I have tree, so add it acc to correcr price
            List<SupplierInfo> suppliers = (List) priceSup.get(p.price);
            if (price != p.price) {
                //remove from old tree if needed
                List<Long> oldSuppliers = (List) priceSup.get(price);
                oldSuppliers.remove(supplier);
                priceSup.put(price, oldSuppliers);
            }
            if(suppliers == null)
                suppliers = new ArrayList<>();
            suppliers.add(supInfo);
            priceSup.put(p.price, suppliers);
            itemPriceSupplier.put(p.id, priceSup);
        }
        supplierInfo.put(supplier, supInfo);
        return created;
    }

    /* return an array with the description of id.  Return null if
      there is no item with this id.
    */
    public Long[ ] description(Long id) {
        TreeSet<Long> descr = itemDecription.get(id);
        if(descr != null)
            return descr.toArray(new Long[descr.size()]);
        return null;
    }

    /* given an array of Longs, return an array of items whose
      description contains one or more elements of the array, sorted
      by the number of elements of the array that are in the item's
      description (non-increasing order).
    */
    public Long[ ] findItem(Long[ ] arr) {
        HashMap<Long, Long> counter = new HashMap<>();
        Long c = null;
        for(Long d: arr){
            TreeSet<Long> t = descriptionItemid.get(d);
            //get list of ids which have this descr word

            if(t!= null){
                for(Long i: t) {
                    //we have tree of ids with this "word"
                    //count and store somewhere
                    c = counter.get(i);
                    if (c == null)
                        c = new Long(0);
                    counter.put(i, c + 1);
                }
            }
        }
        return (Long[]) toSortedArray(counter);
//        ValueSort sortByValue = new ValueSort(counter);
//        TreeMap<Long, Long> orderedByCount = new TreeMap<>(sortByValue);
//        orderedByCount.putAll(counter);
//        return orderedByCount.keySet().toArray(new Long[counter.size()]);
    }

    /* given a Long n, return an array of items whose description
      contains n, which have one or more suppliers whose reputation
      meets or exceeds the given minimum reputation, that sell that
      item at a price that falls within the price range [minPrice,
      maxPrice] given.  Items should be sorted in order of their
      minimum price charged by a supplier for that item
      (non-decreasing order).
    */
    public Long[ ] findItem(Long n, int minPrice, int maxPrice, float minReputation) {
	return null;
    }

    /* given an id, return an array of suppliers who sell that item,
      ordered by the price at which they sell the item (non-decreasing order).
    */
    public Long[ ] findSupplier(Long id) {
        ArrayList<Long> result = new ArrayList<>();
	    TreeMap<Integer, List<SupplierInfo>> priceSup = itemPriceSupplier.get(id);
	    if(priceSup == null)
            return null;
	    else {
            for (Map.Entry<Integer, List<SupplierInfo>> pSlist : priceSup.entrySet()) {
                List<SupplierInfo> sups = pSlist.getValue();
                for (SupplierInfo s : sups) {
                    result.add(s.getSid());
                }

            }
            return (Long[]) result.toArray();
        }
    }

    /* given an id and a minimum reputation, return an array of
      suppliers who sell that item, whose reputation meets or exceeds
      the given reputation.  The array should be ordered by the price
      at which they sell the item (non-decreasing order).
    */
    public Long[ ] findSupplier(Long id, float minReputation) {
        ArrayList<Long> result = new ArrayList<>();
        TreeMap<Integer, List<SupplierInfo>> priceSup = itemPriceSupplier.get(id);
        if(priceSup == null)
            return null;
        else {
            for (Map.Entry<Integer, List<SupplierInfo>> pSlist : priceSup.entrySet()) {
                List<SupplierInfo> sups = pSlist.getValue();
                for (SupplierInfo s : sups) {
                    if(s.getReputation() > minReputation)
                        result.add(s.getSid());
                }

            }
            return (Long[]) result.toArray();
        }
    }

    /* find suppliers selling 5 or more products, who have the same
       identical profile as another supplier: same reputation, and,
       sell the same set of products, at identical prices.  This is a
       rare operation, so do not do additional work in the other
       operations so that this operation is fast.  Creative solutions
       that are elegant and efficient will be awarded excellence credit.
       Return array of suppliers satisfying above condition.  Make sure
       that each supplier appears only once in the returned array.
    */
    public Long[ ] identical() {
	return null;
    }

    /* given an array of ids, find the total price of those items, if
       those items were purchased at the lowest prices, but only from
       sellers meeting or exceeding the given minimum reputation.
       Each item can be purchased from a different seller.
    */
    public int invoice(Long[ ] arr, float minReputation) {
	return 0;
    }

    /* remove all items, all of whose suppliers have a reputation that
       is equal or lower than the given maximum reputation.  Returns
       an array with the items removed.
    */
    public Long[ ] purge(float maxReputation) {
	    //need to get bad suppliers, for that
        //use reputation tree
        //get suppliers who are bad suppliers
        //using supplier info
        //get items sing pairs []
        //for each item incr count of bad suppliers
        //if badSupCount = totalSupCount (from IPSup)
        //then purge this item
        //else dont
        return null;
    }

    /* remove item from storage.  Returns the sum of the Longs that
       are in the description of the item deleted (or 0, if such an id
       did not exist).
    */
    public Long remove(Long id) {
	return 0L;
    }

    /* remove from the given id's description those elements that are
       in the given array.  It is possible that some elements of the
       array are not part of the item's description.  Return the
       number of elements that were actually removed from the description.
    */
    public int remove(Long id, Long[ ] arr) {
	return 0;
    }

    /* remove the elements of the array from the description of all
       items.  Return the number of items that lost one or more terms
       from their descriptions.
    */
    public int removeAll(Long[ ] arr) {
	return 0;
    }
}
