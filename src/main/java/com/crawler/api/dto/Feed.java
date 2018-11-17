package com.crawler.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Feed implements Serializable{
	
	private static final long serialVersionUID = 6821940994598476549L;
	
	private List<Item> Items; 
	
	public Feed() {
		this.Items = new ArrayList<Item>();
	}
	
	public Feed(ArrayList<Item> listItems) {
		this.Items = listItems;
	}
	
	public List<Item> getItems() {
		return Items;
	}
	
	public void setItems(List<Item> Items) {
		this.Items = Items;
	}
	
	public void addItem(Item item) {
		this.Items.add(item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Items == null) ? 0 : Items.hashCode());
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
		if (Items == null) {
			if (other.Items != null)
				return false;
		} else if (!Items.equals(other.Items))
			return false;
		return true;
	}
}
