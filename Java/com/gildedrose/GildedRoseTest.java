package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void AtTheEndOfEachDayBothSellInAndQualityValuesAreLowered() {
        Item[] items = new Item[]{new Item("foo", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
    }

    @Test
    public void OnceTheSellByDateHasPassedQualityDegradesTwiceAsFast() {
        Item[] items = new Item[]{new Item("foo", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    public void TheQualityOfAnItemIsNeverNegative() {
        Item[] items = new Item[]{new Item("foo", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void __Aged_Brie__ActuallyIncreasesInQualityTheOlderItGets() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    public void TheQualityOfAnItemIsNeverMoreThan50() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void _Sulfuras_BeingALegendaryItemNeverHasToBeSoldOrDecreasesInQuality() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void __Backstage_passes__QualityIncreasesBy2WhenThereAre10DaysOrLess() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
        assertEquals(8, app.items[1].sellIn);
        assertEquals(12, app.items[1].quality);
    }

    @Test
    public void __Backstage_passes__QualityIncreasesBy3WhenThereAre5DaysOrLess() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(13, app.items[0].quality);
        assertEquals(3, app.items[1].sellIn);
        assertEquals(13, app.items[1].quality);
    }

    @Test
    public void __Backstage_passes__QualityDropsTo0AfterTheConcert() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[1].sellIn);
        assertEquals(0, app.items[1].quality);
    }

    @Test
    public void ConjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        Item[] items = new Item[]{
                new Item("Conjured Mana Cake", 3, 6),
                new Item("Conjured Mana Cake", 0, 6),
                new Item("Conjured Mana Cake", 0, 2),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(2, app.items[1].quality);
        assertEquals(-1, app.items[2].sellIn);
        assertEquals(0, app.items[2].quality);

    }

    @Test
    public void TestFixture() {

        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
        assertEquals(1, app.items[1].sellIn);
        assertEquals(1, app.items[1].quality);
        assertEquals(4, app.items[2].sellIn);
        assertEquals(6, app.items[2].quality);
        assertEquals(0, app.items[3].sellIn);
        assertEquals(80, app.items[3].quality);
        assertEquals(-1, app.items[4].sellIn);
        assertEquals(80, app.items[4].quality);
        assertEquals(14, app.items[5].sellIn);
        assertEquals(21, app.items[5].quality);
        assertEquals(9, app.items[6].sellIn);
        assertEquals(50, app.items[6].quality);
        assertEquals(4, app.items[7].sellIn);
        assertEquals(50, app.items[7].quality);

    }

}
