package cn.lut.se.forum.controller;

import cn.lut.se.forum.domain.Category;
import cn.lut.se.forum.service.CategoryService;
import cn.lut.se.forum.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name="categoryservlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
     CategoryService categoryService = new CategoryServiceImpl();

     /**
      * 返回全部分类
      * http://localhost:8080/SE_Forum/category?method=list
      *
      */

     public void list(HttpServletRequest request, HttpServletResponse response){
          List<Category> list =  categoryService.list();

          System.out.println(list.toString());

          request.setAttribute("caregoryList",list);

     }
}
