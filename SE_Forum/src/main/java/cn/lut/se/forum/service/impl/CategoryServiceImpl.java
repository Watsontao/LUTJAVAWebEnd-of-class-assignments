package cn.lut.se.forum.service.impl;

import cn.lut.se.forum.dao.CategoryDao;
import cn.lut.se.forum.domain.Category;
import cn.lut.se.forum.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
   private CategoryDao categoryDao = new CategoryDao();

    @Override
    public List<Category> list() {
       return categoryDao.list();
    }
}
