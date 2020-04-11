package com.dsl.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: dsl
 * @date: 2020/4/1
 */
public interface ArticleService {
	/**
	 * 新增文章
	 */
	JSONObject addArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	JSONObject listArticle(JSONObject jsonObject);

	/**
	 * 更新文章
	 */
	JSONObject updateArticle(JSONObject jsonObject);
}
