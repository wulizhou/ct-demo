package com.wulizhou.pets.service.impl;

import com.wulizhou.pets.model.entity.Article;
import com.wulizhou.pets.service.facade.IArticleService;
import com.wulizhou.pets.system.common.BaseMapper;
import com.wulizhou.pets.system.common.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ArticleService extends BaseService<Article> implements IArticleService {


    @Override
    public BaseMapper<Article> getMapper() {
        return null;
    }
}
