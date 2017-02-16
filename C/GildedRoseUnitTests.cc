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
