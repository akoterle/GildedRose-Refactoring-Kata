package com.gildedrose;

public class SellInUpdater extends ItemUpdater {

    @Override
    protected void update(Item item) {

        if ( item.name.equals("Sulfuras, Hand of Ragnaros") )
            return;

        item.sellIn = item.sellIn - 1;

        successor.update(item);
    }

}
