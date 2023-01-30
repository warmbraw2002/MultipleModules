package com.tiktok.service.impl;

import com.tiktok.ErrorEnum;
import com.tiktok.Result;
import com.tiktok.domain.Menu;
import com.tiktok.mapper.MenuMapper;
import com.tiktok.service.MenuService;
import com.tiktok.utils.ClassExamine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Result getMenuById(Long menuId) {
        Menu menu = menuMapper.findMenuById(menuId);
        if (menu == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(menu);
    }

    @Override
    public Result insertMenu(Menu menu) {
        return Result.of(menuMapper.insertMenu(menu));
    }

    @Override
    public Result updateMenu(Menu menu) throws Exception {
        Menu oldMenu = menuMapper.findMenuById(menu.getMenuId());
        if (oldMenu == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        ClassExamine.objectOverlap(menu, oldMenu);
        return Result.of(menuMapper.updateMenu(oldMenu));
    }

    @Override
    public Result deleteMenuById(Long menuId) {
        Menu menu = menuMapper.findMenuById(menuId);
        if (menu == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(menuMapper.deleteMenuById(menuId));
    }

    @Override
    public Result getSubmenu(Long menuId) {
        Menu menu = menuMapper.findMenuById(menuId);
        if (menu == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(menuMapper.findSubmenu(menuId, currentUserName()));
    }

    @Override
    public Result getPeerMenu(Long menuId) {
        Menu menu = menuMapper.findMenuById(menuId);
        if (menu == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(menuMapper.findPeerMenu(menuId, currentUserName()));
    }

    @Override
    public Result getParentMenu(Long menuId) {
        Menu menu = menuMapper.findMenuById(menuId);
        if (menu == null) {
            return Result.error(ErrorEnum.NOT_FOUND);
        }
        return Result.of(menuMapper.findParentMenu(menuId, currentUserName()));
    }

    @Override
    public Result getMenuByCurrentUser() {
        return Result.of(menuMapper.findRootMenu(currentUserName()));
    }

    private String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}

