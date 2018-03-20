package com.aboutsai.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aboutsai.blog.dao.CatalogDao;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {
	@Resource
	private CatalogDao catalogDao;

	public Catalog getById(String id) {
		return this.catalogDao.getById(id);
	}

	public List<Catalog> query(Catalog catalog) {
		return this.catalogDao.query(catalog);
	}
}
