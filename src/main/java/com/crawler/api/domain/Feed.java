package com.crawler.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Feed implements Serializable{
	
	private static final long serialVersionUID = 6821940994598476549L;
	
	private List<Item> items; 
	
	public Feed() {
		this.items = new ArrayList<Item>();
	}
	
	public Feed(ArrayList<Item> listItems) {
		this.items = listItems;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> Items) {
		this.items = Items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feed other = (Feed) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
}
