package com.gildedrose;

abstract class ItemUpdater {

    protected ItemUpdater successor;

    abstract protected void update(Item item);

    public void setSuccessor(ItemUpdater successor) {
        this.successor = successor;
    }

    protected boolean isAConjuredItem(Item item) {
        return item.name.equals("Conjured Mana Cake");
    }

    protected boolean isALegendaryItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    protected boolean isAnIncreaseInQualityItem(Item item) {
        return (item.name.equals("Aged Brie")
                || item.name.equals("Backstage passes to a TAFKAL80ETC concert"));

    }

}
