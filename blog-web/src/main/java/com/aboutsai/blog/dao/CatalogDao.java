package com.aboutsai.blog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.aboutsai.blog.entity.Catalog;

public interface CatalogDao {
	public Catalog getById(@Param("id") String id);

	public List<Catalog> query(Catalog paramCatalog);
}
