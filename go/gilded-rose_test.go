package main

import (
	"testing"
	"fmt"
)

func (i Item) String() string {
	return fmt.Sprintf("%s: sellIn %d, quality %d", i.name, i.sellIn, i.quality)
}

func Test_GildedRose(t *testing.T) {
	main()
}

func Example_AtTheEndOfEachDayBothSellInAndQualityValuesAreLowered() {
	items = []Item{
		Item{"+5 Dexterity Vest", 10, 20},
	}
	GlidedRose();
	fmt.Println(items)

	// Output:
	// [+5 Dexterity Vest: sellIn 9, quality 19]
}

func Example__Aged_Brie__ActuallyIncreasesInQualityTheOlderItGets() {
	items = []Item{
		Item{"Aged Brie", 2, 0},
	}
	GlidedRose();
	fmt.Println(items)

	// Output:
	// [Aged Brie: sellIn 1, quality 1]
}

