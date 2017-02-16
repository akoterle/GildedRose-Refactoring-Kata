#include <CppUTest/TestHarness.h>
#include <CppUTest/CommandLineTestRunner.h>
#include <CppUTestExt/MockSupport.h>

extern "C" {
#include "GildedRose.h"
}

TEST_GROUP(TestGildedRoseGroup)
{
  void setup() {
  }
  void teardown() {
  }
};

TEST(TestGildedRoseGroup, at_the_end_of_each_day_the_system_lowers_both_quality_and_sellIn_values) {
    Item items[2];
    init_item(items, "+5 Dexterity Vest", 10, 20);
    update_quality(items, 1);
    STRCMP_EQUAL("+5 Dexterity Vest", items[0].name);
    LONGS_EQUAL(9, items[0].sellIn);
    LONGS_EQUAL(19, items[0].quality);
}

TEST(TestGildedRoseGroup,  Once_the_sell_by_date_has_passed_Quality_degrades_twice_as_fast) {
    Item items[2];
    init_item(items, "+5 Dexterity Vest", 0, 10);
    update_quality(items, 1);
    STRCMP_EQUAL("+5 Dexterity Vest", items[0].name);
    LONGS_EQUAL(-1, items[0].sellIn);
    LONGS_EQUAL(8, items[0].quality);
}

TEST(TestGildedRoseGroup, the_quality_of_an_item_is_never_negative) {
    Item items[2];
    init_item(items, "+5 Dexterity Vest", 0, 0);
    update_quality(items, 1);
    STRCMP_EQUAL("+5 Dexterity Vest", items[0].name);
    LONGS_EQUAL(0, items[0].quality);
}

TEST(TestGildedRoseGroup, __Aged_Brie__actually_increases_in_quality_the_older_it_gets) {
    Item items[2];
    init_item(items, "Aged Brie", 2, 0);
    update_quality(items, 1);
    STRCMP_EQUAL("Aged Brie", items[0].name);
    LONGS_EQUAL(1, items[0].quality);
}

TEST(TestGildedRoseGroup, __Sulfuras__being_a_legendary_item_never_has_to_be_sold_or_decreases_in_quality) {
    Item items[2];
    init_item(items, "Sulfuras, Hand of Ragnaros", 0, 80);
    update_quality(items, 1);
    STRCMP_EQUAL("Sulfuras, Hand of Ragnaros", items[0].name);
    LONGS_EQUAL(0, items[0].sellIn);
    LONGS_EQUAL(80, items[0].quality);
}

TEST(TestGildedRoseGroup, __Backstage_passes__like_aged_brie_increases_in_quality_as_its_sellIn_value_approaches_10_days_or_less) {
    Item items[2];
    init_item(items, "Backstage passes to a TAFKAL80ETC concert", 10, 20);
    update_quality(items, 1);
    STRCMP_EQUAL("Backstage passes to a TAFKAL80ETC concert", items[0].name);
    LONGS_EQUAL(9, items[0].sellIn);
    LONGS_EQUAL(22, items[0].quality);

    init_item(items, "Backstage passes to a TAFKAL80ETC concert", 9, 20);
    update_quality(items, 1);
    STRCMP_EQUAL("Backstage passes to a TAFKAL80ETC concert", items[0].name);
    LONGS_EQUAL(8, items[0].sellIn);
    LONGS_EQUAL(22, items[0].quality);
    
}

TEST(TestGildedRoseGroup, __Backstage_passes__like_aged_brie_increases_in_quality_as_its_sellIn_value_approaches_5_days_or_less) {
    Item items[2];
    init_item(items, "Backstage passes to a TAFKAL80ETC concert", 5, 20);
    update_quality(items, 1);
    STRCMP_EQUAL("Backstage passes to a TAFKAL80ETC concert", items[0].name);
    LONGS_EQUAL(4, items[0].sellIn);
    LONGS_EQUAL(23, items[0].quality);

    init_item(items, "Backstage passes to a TAFKAL80ETC concert", 4, 20);
    update_quality(items, 1);
    STRCMP_EQUAL("Backstage passes to a TAFKAL80ETC concert", items[0].name);
    LONGS_EQUAL(3, items[0].sellIn);
    LONGS_EQUAL(23, items[0].quality);
    
}

TEST(TestGildedRoseGroup, __Backstage_passes__quality_drops_to_0_after_the_concert) {
    Item items[2];
    init_item(items, "Backstage passes to a TAFKAL80ETC concert", 0, 20);
    update_quality(items, 1);
    STRCMP_EQUAL("Backstage passes to a TAFKAL80ETC concert", items[0].name);
    LONGS_EQUAL(-1, items[0].sellIn);
    LONGS_EQUAL(0, items[0].quality);
}

TEST(TestGildedRoseGroup, __Conjured__items_degrade_in_quality_twice_as_fast_as_normal_items) {
    Item items[2];
    init_item(items, "Conjured Mana Cake", 3, 6);
    update_quality(items, 1);
    STRCMP_EQUAL("Conjured Mana Cake", items[0].name);
    LONGS_EQUAL(2, items[0].sellIn);
    LONGS_EQUAL(4, items[0].quality);
}

void example()
{
    Item items[6];
    int last = 0;
    
    init_item(items + last++, "+5 Dexterity Vest", 10, 20);
    init_item(items + last++, "Aged Brie", 2, 0);
    init_item(items + last++, "Elixir of the Mongoose", 5, 7);
    init_item(items + last++, "Sulfuras, Hand of Ragnaros", 0, 80);
    init_item(items + last++, "Backstage passes to a TAFKAL80ETC concert", 15, 20);
    init_item(items + last++, "Conjured Mana Cake", 3, 6);
    update_quality(items, last);
}

int
main(int ac, char** av)
{
  return CommandLineTestRunner::RunAllTests(ac, av);
}
