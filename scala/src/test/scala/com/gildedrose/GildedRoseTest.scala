package com.gildedrose

import org.scalatest._

class GildedRoseTest  extends FlatSpec with Matchers {
      it should "At the end of each day our system lowers both values for every item" in {
        var items = Array[Item](new Item("foo", 0, 0))
        val app = new GildedRose(items)
        app.updateQuality()
        (app.items(0).name) should equal ("foo")
        (app.items(0).sellIn) should equal (-1)
        (app.items(0).quality) should equal (0)
      }
}