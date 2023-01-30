package com.tiktok.service;


import com.tiktok.Result;
import com.tiktok.domain.Menu;

public interface MenuService {

    Result getMenuById(Long menuId);
    Result insertMenu(Menu menu);
    Result updateMenu(Menu menu) throws Exception;
    Result deleteMenuById(Long menuId);
    Result getSubmenu(Long menuId);
    Result getPeerMenu(Long menuId);
    Result getParentMenu(Long menuId);
    Result getMenuByCurrentUser();
}
