package com.aboutsai.blog.common.datadict;

public class DataDictionary {
	public static enum Status {
		REJECT, NO, YES, WAIT;

		public int getValue() {
			return ordinal() - 1;
		}
	}

	public static enum Module {
		index, about, article, guestbook, catalog;
	}
}
