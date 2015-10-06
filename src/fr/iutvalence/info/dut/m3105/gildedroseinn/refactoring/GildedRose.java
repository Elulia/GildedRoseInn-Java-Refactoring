package fr.iutvalence.info.dut.m3105.gildedroseinn.refactoring;

import java.util.ArrayList;
import java.util.List;

public class GildedRose
{

	private static final int SELLIN_THRESHOLD_FOR_BACKSTAGE_QUALITY_INCREASING_THREE_TIMES_AS_FAST = 5;
	private static final int SELLIN_THRESHOLD_FOR_BACKSTAGE_QUALITY_INCREASING_TWICE_AS_FAST = 10;
	private static final int MAX_QUALITY = 50;
	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", SELLIN_THRESHOLD_FOR_BACKSTAGE_QUALITY_INCREASING_TWICE_AS_FAST, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", SELLIN_THRESHOLD_FOR_BACKSTAGE_QUALITY_INCREASING_THREE_TIMES_AS_FAST, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQualityAndSellInForAllItems();
	}

	public static void updateQualityAndSellInForAllItems()
	{
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			
			updateQualityAndSellInForItem(item);
		}
	}

	public static void updateQualityAndSellInForItem(Item item) {
		
		updateSellInForItem(item);
		
		updateQualityForItem(item);
	}

	public static void updateQualityForItem(Item item) {
		if (("Aged Brie".equals(item.getName())) || "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
			if (item.getQuality() < MAX_QUALITY)
			{
				incrementQualityByOneForItem(item);

				if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName()))
				{
					if (item.getSellIn() < SELLIN_THRESHOLD_FOR_BACKSTAGE_QUALITY_INCREASING_TWICE_AS_FAST)
					{
						increaseQualityForItem(item);
					}

					if (item.getSellIn() < SELLIN_THRESHOLD_FOR_BACKSTAGE_QUALITY_INCREASING_THREE_TIMES_AS_FAST)
					{
						increaseQualityForItem(item);
					}
				}
			}
		} else {
			if (!"Sulfuras, Hand of Ragnaros".equals(item.getName()))
			{
				decreaseQualityForItem(item);
			}
		}

		if (item.getSellIn() < 0)
		{
			if ("Aged Brie".equals(item.getName())) {
				increaseQualityForItem(item);
			} 
			else 
			{
				if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName()))
				{
					item.setQuality(0);
				} 
				else {
					if (!"Sulfuras, Hand of Ragnaros".equals(item.getName()))
					{		
						decreaseQualityForItem(item);
					}
				}
			}
		}
	}

	//decrease quality only if quality is positive
	public static void decreaseQualityForItem(Item item) {
		if (item.getQuality() > 0)
		{
			decrementQualityByOneForItem(item);
		}
	}

	public static void decrementQualityByOneForItem(Item item) {
		item.setQuality(item.getQuality() - 1);
	}

	// increase quality only if the max quality isn't reach
	public static void increaseQualityForItem(Item item) {
		if (item.getQuality() < MAX_QUALITY)
		{
			incrementQualityByOneForItem(item);
		}
	}

	public static void incrementQualityByOneForItem(Item item) {
		item.setQuality(item.getQuality() + 1);
	}

	public static void updateSellInForItem(Item item) {
		if (!"Sulfuras, Hand of Ragnaros".equals(item.getName()))
		{
			item.setSellIn(item.getSellIn() - 1);
		}
	}

}