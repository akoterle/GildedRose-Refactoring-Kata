package com.gildedrose;

public class QualityUpdater extends ItemUpdater {

    private final ItemUpdater sellInUpdater;

    public QualityUpdater() {
        sellInUpdater = new SellInUpdater();
        sellInUpdater.setSuccessor(this);
    }

    public void updateItemQuality(Item item) {
        sellInUpdater.update(item);
    }

    @Override
    protected void update(Item item) {

        if (isALegendaryItem(item))
            return;

        if (isAnIncreaseInQualityItem(item)) {
            increaseItemQuality(item);
            return;
        }

        degradeItemQuality(item);
    }

    private void degradeItemQuality(Item item) {

        int qualityDegradingFactor = (item.sellIn < 0) ? 2 : 1;
        if (isAConjuredItem(item))
            qualityDegradingFactor = qualityDegradingFactor * 2;
        item.quality = item.quality - (1 * qualityDegradingFactor);

        if (item.quality < 0)
            item.quality = 0;
    }

    private void increaseItemQuality(Item item) {

        int qualityIncreaseFactor = 1;
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 1) {
                item.quality = 0;
                return;
            }
            if (item.sellIn < 11)
                qualityIncreaseFactor = 2;
            if (item.sellIn < 6)
                qualityIncreaseFactor = 3;
        }
        item.quality = item.quality + (1 * qualityIncreaseFactor);
        if (item.quality > 50) item.quality = 50;
    }

}
