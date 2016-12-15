package com.gildedrose;

class GildedRose {
    Item[] items;
    private final QualityUpdater qualityUpdater;

    public GildedRose(Item[] items) {
        this.items = items;
        qualityUpdater = new QualityUpdater();
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            qualityUpdater.updateItemQuality(items[i]);
        }
    }
}
