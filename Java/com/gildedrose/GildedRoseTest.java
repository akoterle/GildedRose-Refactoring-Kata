package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    private GildedRose app;

    @Test
    public void AtTheEndOfEachDayBothSellInAndQualityValuesAreLowered() {
        final Item expected = new Item("foo", 9, 19);
        givenItems(new Item[]{new Item("foo", 10, 20)});
        atTheEndOfDay();
        itemQualityIsUpdatedAsExpected(expected);
    }

    @Test
    public void OnceTheSellByDateHasPassedQualityDegradesTwiceAsFast() {
        final Item expected = new Item("foo", -1, 8);
        givenItems(new Item[]{new Item("foo", 0, 10)});
        atTheEndOfDay();
        itemQualityIsUpdatedAsExpected(expected);
    }

    @Test
    public void TheQualityOfAnItemIsNeverNegative() {
        final Item expected = new Item("foo", 9, 0);
        givenItems(new Item[]{new Item("foo", 10, 0)});
        atTheEndOfDay();
        itemQualityIsUpdatedAsExpected(expected);
    }

    @Test
    public void __Aged_Brie__ActuallyIncreasesInQualityTheOlderItGets() {
        final Item expected = new Item("Aged Brie", 9, 11);
        givenItems(new Item[]{new Item("Aged Brie", 10, 10)});
        atTheEndOfDay();
        itemQualityIsUpdatedAsExpected(expected);
    }

    @Test
    public void TheQualityOfAnItemIsNeverMoreThan50() {
        final Item expected = new Item("Aged Brie", 9, 50);
        givenItems(new Item[]{new Item("Aged Brie", 10, 50)});
        atTheEndOfDay();
        itemQualityIsUpdatedAsExpected(expected);
    }

    @Test
    public void _Sulfuras_BeingALegendaryItemNeverHasToBeSoldOrDecreasesInQuality() {
        final Item expected = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        givenItems(new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)});
        atTheEndOfDay();
        itemQualityIsUpdatedAsExpected(expected);
    }

    @Test
    public void __Backstage_passes__QualityIncreasesBy2WhenThereAre10DaysOrLess() {

        final Item[] expected = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 12),
                new Item("Backstage passes to a TAFKAL80ETC concert", 8, 12)
        };
        Item[] items = {
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 10)
        };
        for (int i = 0; i < items.length; i++) {
            givenItems(new Item[]{items[i]});
            atTheEndOfDay();
            itemQualityIsUpdatedAsExpected(expected[i]);
        }
    }

    @Test
    public void __Backstage_passes__QualityIncreasesBy3WhenThereAre5DaysOrLess() {
        final Item[] expected = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 13),
                new Item("Backstage passes to a TAFKAL80ETC concert", 3, 13)
        };
        Item[] items = {
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10)
        };
        for (int i = 0; i < items.length; i++) {
            givenItems(new Item[]{items[i]});
            atTheEndOfDay();
            itemQualityIsUpdatedAsExpected(expected[i]);
        }
    }

    @Test
    public void __Backstage_passes__QualityDropsTo0AfterTheConcert() {
        final Item[] expected = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", -2, 0)
        };
        Item[] items = {
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10)
        };

        for (int i = 0; i < items.length; i++) {
            givenItems(new Item[]{items[i]});
            atTheEndOfDay();
            itemQualityIsUpdatedAsExpected(expected[i]);
        }
    }

    @Test
    public void ConjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        final Item[] expected = new Item[]{
                new Item("Conjured Mana Cake", 2, 4),
                new Item("Conjured Mana Cake", -1, 2),
                new Item("Conjured Mana Cake", -1, 0),
        };
        Item[] items = {
                new Item("Conjured Mana Cake", 3, 6),
                new Item("Conjured Mana Cake", 0, 6),
                new Item("Conjured Mana Cake", 0, 2),
        };

        for (int i = 0; i < items.length; i++) {
            givenItems(new Item[]{items[i]});
            atTheEndOfDay();
            itemQualityIsUpdatedAsExpected(expected[i]);
        }
    }

    @Test
    public void TestFixture() {
        final Item[] expected = new Item[]{
                new Item("+5 Dexterity Vest", 9, 19),
                new Item("Aged Brie", 1, 1),
                new Item("Elixir of the Mongoose", 4, 6),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50),
                new Item("Conjured Mana Cake", 2, 4)
        };
        Item[] items = {
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

        for (int i = 0; i < items.length; i++) {
            givenItems(new Item[]{items[i]});
            atTheEndOfDay();
            itemQualityIsUpdatedAsExpected(expected[i]);
        }
    }


    private void givenItems(Item[] items) {
        app = new GildedRose(items);
    }

    private void atTheEndOfDay() {
        app.updateQuality();
    }

    private void itemQualityIsUpdatedAsExpected(Item expected) {
        assertEquals(expected.sellIn, app.items[0].sellIn);
        assertEquals(expected.quality, app.items[0].quality);
    }

}
