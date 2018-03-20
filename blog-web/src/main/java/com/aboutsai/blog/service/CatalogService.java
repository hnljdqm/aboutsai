package com.aboutsai.blog.service;

import java.util.List;

import com.aboutsai.blog.entity.Catalog;

public abstract interface CatalogService {
	public abstract Catalog getById(String paramString);

	public abstract List<Catalog> query(Catalog paramCatalog);
}
